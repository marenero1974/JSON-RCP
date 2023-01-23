package it.soft.jsonrpc.api;

import java.util.List;
import it.soft.jsonrpc.model.InputParams;

public interface MyService {
    List<String> GiveMeAdvice(InputParams input);
}
