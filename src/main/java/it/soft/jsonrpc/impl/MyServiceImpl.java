package it.soft.jsonrpc.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import it.soft.jsonrpc.api.MyService;
import it.soft.jsonrpc.model.AdvicesResult;
import it.soft.jsonrpc.model.InputParams;
import it.soft.jsonrpc.model.SlipAdvice;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class MyServiceImpl implements MyService {

    private LoadingCache<String, AdvicesResult> cache;

    @Override
    public List<String> GiveMeAdvice(InputParams input) {

        if(cache == null) {
            buildCache();
        }

        AdvicesResult advicesResult;
        if(cache.getIfPresent(input.getTopic()) ==  null) {
            // se nella cache non c'è la lista dei messaggi la ricalcolo e la rimetto nella cache
            System.out.println("Prendo l'elemento dal sito slip e lo metto nella cache");
            advicesResult = getSlipAdvices(input);
            cache.put(input.getTopic(), advicesResult);
        } else {
            // se nella cache c'è la ritorno
            System.out.println("Prendo il messaggio salvato nella cache");
            advicesResult = cache.getUnchecked(input.getTopic());
        }

        // riformatto i messaggi di uscita con il limite e mettendo solo il testo del mex
        List<SlipAdvice> slipList = advicesResult.getSlips();
        List<String> adviceList = slipList
            .stream()
            .limit(input.getAmount())
            .map(p -> p.getAdvice())
            .collect(Collectors.toList());

        return adviceList;
    }

    private AdvicesResult getSlipAdvices(InputParams input) {
        System.out.println("Prendo i messaggi slip dalla origine dati");
        HttpGet request = new HttpGet("https://api.adviceslip.com/advice/search/" + input.getTopic());
        String result = null;
        AdvicesResult advicesResult;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // json string
                result = EntityUtils.toString(entity);
            }

            ObjectMapper mapper = new ObjectMapper();
            advicesResult = mapper.readValue(result, AdvicesResult.class);

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return advicesResult;
    }

    private void buildCache() {
        System.out.println("Costruisco una nuova cache");
        CacheLoader<String, AdvicesResult> loader;
        loader = new CacheLoader<String, AdvicesResult>() {
            @Override
            public AdvicesResult load(String key) {
                return null;
            }
        };

        cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES) // scade dopo 5 minuti 300000
            .build(loader);
    }
}
