package it.soft.jsonrpc;

import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import it.soft.jsonrpc.api.MyService;
import it.soft.jsonrpc.impl.MyServiceImpl;

@ServletComponentScan
@SpringBootApplication
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class);
    }

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

    @Bean(name = "/rpc/myservice")
    public JsonServiceExporter jsonServiceExporter() {
        JsonServiceExporter exporter = new JsonServiceExporter();
        exporter.setService(myService());
        exporter.setServiceInterface(MyService.class);
        return exporter;
    }
}
