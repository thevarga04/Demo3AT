package demo;

import demo.service.IntegrationService;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntegrationCamelApplication {
  public static void main( String[] args ) {
    SpringApplication.run( IntegrationCamelApplication.class, args );
  }
}
