package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient         // alias for more general: @EnableDiscoveryClient
@SpringBootApplication
public class RestService extends SpringBootServletInitializer {
  
  @Bean
  @LoadBalanced
  public WebClient.Builder webClient() {
    return WebClient.builder();
  }
  
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate( RestTemplateBuilder builder ) {
    return builder.build();
  }
  
  
  
  public static void main( String[] args ) {
    SpringApplication.run( RestService.class, args );
  }
}