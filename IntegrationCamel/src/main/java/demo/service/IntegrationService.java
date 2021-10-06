package demo.service;

import demo.eai.FileTransfer;
import org.apache.camel.CamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Transactional
public class IntegrationService {
  private static final Logger LOGGER = LogManager.getLogger( IntegrationService.class );
  
  FileTransfer fileTransfer;
  CamelContext context;
  
  
  public IntegrationService( FileTransfer fileTransfer, CamelContext context ) {
    LOGGER.info( "CamelContext: {}", context.toString() );
    
    this.fileTransfer = fileTransfer;
    this.context = context;
  }
  
  
  /**
   * Start doing something! ;-)
   */
  @PostConstruct
  public void init() throws Exception {
    context.addRoutes( fileTransfer );
    context.start();
  }
  
  
  /**
   * Don't leave hanging locks post-mortem.
   */
  @PreDestroy
  public void onDestroy() {
    context.stop();
  }
}