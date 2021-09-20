package demo.api;

import demo.GeneralClass;
import demo.entity.Doctors;
import demo.entity.Illnesses;
import demo.entity.Patients;
import demo.model.PatientDto;
import demo.service.IllnessesService;
import demo.service.PatientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping( "/api" )
public class RootRest extends GeneralClass {
  private static final Logger LOGGER = LoggerFactory.getLogger( RootRest.class );
  
  @Autowired
  IllnessesService illnessesService;
  @Autowired
  PatientsService patientsService;
  @Autowired
  WebClient.Builder webClient;
  @Autowired
  RestTemplate restTemplate;
  @Autowired
  Environment env;
  
  
  String appName;
  AtomicInteger counter = new AtomicInteger( 0 );
  HttpHeaders headers;
  
  
  @PostConstruct
  public void init() {
    appName = env.getProperty( "spring.application.name" );
    headers = new HttpHeaders();
    headers.setContentType( MediaType.APPLICATION_JSON );
  }
  
  
  
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
  
  
  
  @GetMapping({ "/", "/home", "/root" })
  public ResponseEntity<HttpStatus> home() {
    return ResponseEntity.ok( null );
  }
  
  
  
  /**
   * Illnesses
   */
  @GetMapping( "/getIllness" )
  public ResponseEntity<List<Illnesses>> getIllness() {
    List<Illnesses> illnessesList = illnessesService.findAll();
    LOGGER.info( "getIllness: {}", illnessesList.size() );
    
    return ResponseEntity.ok( illnessesList );
  }
  
  @PostMapping( "/saveIllness" )
  public ResponseEntity<String> saveIllness( Illnesses dto ) {
    LOGGER.info( "saveIllness: {}", dto );
    Illnesses illnesses = illnessesService.save( dto );
    
    if ( illnesses != null && illnesses.getId() > 0 )
      return ResponseEntity.ok( "" );
    else
      return ResponseEntity.status( HttpStatus.NOT_IMPLEMENTED ).body( "" );
  }
  
  @GetMapping( "/deleteIllness/{id}" )
  public ResponseEntity<Integer> deleteIllness( @PathVariable("id") int id ) {
    LOGGER.info( "deleteIllness: {}", id );
    int deleted = illnessesService.deleteIllness( id );
    
    if ( deleted == 0 )
      return new ResponseEntity<>( deleted, HttpStatus.NOT_IMPLEMENTED );
  
    return new ResponseEntity<>( deleted, HttpStatus.OK );
  }
  
  
  
  /**
   * Patients
   */
  @GetMapping( "/getPatient" )
  public ResponseEntity<List<PatientDto>> getPatient() {
    List<PatientDto> patientList = patientsService.findAll();
    LOGGER.info( "getPatient: {}", patientList.size() );
    
    return ResponseEntity.ok( patientList );
  }
  
  @PostMapping( "/savePatient" )
  public ResponseEntity<String> savePatient( @RequestBody PatientDto dto ) {
    LOGGER.info( "savePatient: {}", dto );
    Patients patients = patientsService.save( dto );
  
    if ( patients != null && patients.getId() > 0 )
      return ResponseEntity.ok( "" );
    else
      return ResponseEntity.status( HttpStatus.NOT_IMPLEMENTED ).body( "" );
  }
  
  @GetMapping( "/deletePatient/{id}" )
  public ResponseEntity<Integer> deletePatient( @PathVariable("id") int id ) {
    LOGGER.info( "deletePatient: {}", id );
    int deleted = patientsService.deletePatient( id );
    
    if ( deleted == 0 )
      return new ResponseEntity<>( deleted, HttpStatus.NOT_IMPLEMENTED );
    
    return new ResponseEntity<>( deleted, HttpStatus.OK );
  }
  
  
  
  /**
   * Service discovery methods
   */
  @GetMapping( "/getDoctor" )
  public ResponseEntity<Doctors[]> getDoctor() {
    info( appName + " is about to get doctors ... " + counter.incrementAndGet() );
    
    return restTemplate.getForEntity( "http://rest2/api/getDoctor", Doctors[].class );
  }
  
  @PostMapping( "/saveDoctor" )
  public ResponseEntity<String> saveDoctor( @RequestBody Doctors dto ) {
    info( appName + " is about to save a doctor ... " + counter.incrementAndGet() );
    HttpEntity<Doctors> entity = new HttpEntity<>( dto, headers );
    
    return restTemplate.postForEntity( "http://rest2/api/saveDoctor", entity, String.class );
  }
  
  @GetMapping( "/deleteDoctor/{id}" )
  public ResponseEntity<Integer> deleteDoctor( @PathVariable("id") int id ) {
    info( appName + " is about to delete a doctor ... " + counter.incrementAndGet() );
  
    return restTemplate.getForEntity( "http://rest2/api/deleteDoctor/" + id, Integer.class );
  }
}