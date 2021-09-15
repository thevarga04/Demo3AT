package demo.api;

import demo.entity.Doctors;
import demo.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping( "/api" )
public class RootRest {
  @Autowired
  DoctorsService doctorsService;
  
  
  @GetMapping({ "/", "/home", "/root" })
  public ResponseEntity<HttpStatus> home() {
    return ResponseEntity.ok( null );
  }
  
  
  
  @GetMapping( "/getDoctor" )
  public ResponseEntity<List<Doctors>> getDoctor() {
    return ResponseEntity.ok( doctorsService.findAll() );
  }
  
  @GetMapping( "/allDoctor" )
  public List<Doctors> allDoctor() {
    return doctorsService.findAll();
  }
  
  
  /**
   * JSON, not a FormData
   */
  @PostMapping( value = "/saveDoctor" )
  public ResponseEntity<String> saveDoctor( @RequestBody Doctors dto ) {
    Doctors doctors = doctorsService.save( dto );
    
    if ( doctors != null && doctors.getId() > 0 )
      return ResponseEntity.ok( "" );
    else
      return ResponseEntity.status( HttpStatus.NOT_IMPLEMENTED ).body( "" );
  }
  
  
  @GetMapping( "/deleteDoctor/{id}" )
  public ResponseEntity<Integer> deleteDoctor( @PathVariable("id") int id ) {
    int deleted = doctorsService.deleteDoctor( id );
    
    if ( deleted == 0 )
      return new ResponseEntity<>( deleted, HttpStatus.NOT_IMPLEMENTED );
  
    return new ResponseEntity<>( deleted, HttpStatus.OK );
  }
}