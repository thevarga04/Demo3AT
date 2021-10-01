package demo.eai;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Directory subscriber ("listener") to process files as defined
 * VETRO: Validation, Enrichment, Transformation, Routing, Operation
 *
 *
 * https://camel.apache.org/manual/latest/architecture.html
 *
 * https://camel.apache.org/components/3.11.x/index.html
 * https://camel.apache.org/components/3.11.x/eips/enterprise-integration-patterns.html
 *
 * https://github.com/apache/camel-examples/tree/main/examples
 *
 * Monitoring & Operations
 * https://grafana.com/grafana/dashboards/12614
 */
@Component
public class FileTransfer extends RouteBuilder {
  public String ROUTE_ID;
  public String READ_OPTIONS =
    "?initialDelay=5s" +
      "&readLock=changed" +
      "&readLockCheckInterval=2s" +
      "&readLockMinAge=10s";
  public String FILE_TRANSFER_FROM = "file:C:/opt/in/" + READ_OPTIONS;
  public String FILE_TRANSFER_TO1 = "file:C:/opt/out1/";
  public String FILE_TRANSFER_TO2 = "file:C:/opt/out2/";
  
  {
    ROUTE_ID = this.getClass().getSimpleName();
  }
  
  
  /**
   * https://camel.apache.org/components/3.11.x/file-component.html
   */
  @Override
  public void configure() throws Exception {
    from( FILE_TRANSFER_FROM )
      .routeId( ROUTE_ID )
      .log( "+++ [HEADERS]: ${headers}" )
      .choice()
        .when( header( "CamelFileLength" ).isLessThan( 100 ) )
        .convertBodyTo( String.class )
        .log( "* [BODY]: \n${body}" )
      .end()
      .multicast()
      .to( FILE_TRANSFER_TO1 )
      .to( FILE_TRANSFER_TO2 );
  }
}