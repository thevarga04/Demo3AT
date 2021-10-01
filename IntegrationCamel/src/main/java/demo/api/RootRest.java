package demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * or camel.springboot.main-run-controller = true
 */
@RestController
@RequestMapping( "/api" )
public class RootRest {
}