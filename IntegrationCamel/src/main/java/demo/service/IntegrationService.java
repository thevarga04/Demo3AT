package demo.service;

import demo.eai.FileTransfer;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Transactional
public class IntegrationService {
  FileTransfer fileTransfer;
  CamelContext context;
  
  
  public IntegrationService( FileTransfer fileTransfer, CamelContext context ) {
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