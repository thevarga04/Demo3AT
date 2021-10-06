package demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * or camel.springboot.main-run-controller = true
 */
@RestController
@RequestMapping( "/api" )
public class RootRest {
  private static final Logger LOGGER = LogManager.getLogger( RootRest.class );
  
  
  @GetMapping
  public ResponseEntity<String> home() {
    LOGGER.info( "home" );
    return ResponseEntity.ok( "home" );
  }
}