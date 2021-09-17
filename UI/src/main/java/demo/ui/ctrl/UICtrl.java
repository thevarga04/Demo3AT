package demo.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UICtrl {
  
  @GetMapping( { "/", "/home" } )
  public String home() {
    return "home";
  }
  
  
  
  @GetMapping( "/newIllness" )
  public String newIllness() {
    return "newIllness";
  }
  
  @GetMapping( "/newDoctor" )
  public String newDoctor() {
    return "newDoctor";
  }
  
  
  @GetMapping( "/newPatient" )
  public String newPatient() {
    return "newPatient";
  }
}