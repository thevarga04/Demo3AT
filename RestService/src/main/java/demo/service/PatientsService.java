package demo.service;

import demo.entity.Doctors;
import demo.entity.Illnesses;
import demo.entity.Patients;
import demo.model.PatientDto;
import demo.repo.DoctorsRepo;
import demo.repo.IllnessesRepo;
import demo.repo.PatientsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientsService {
  private static final Logger LOGGER = LoggerFactory.getLogger( PatientsService.class );
  
  @Autowired
  PatientsRepo patientsRepo;
  @Autowired
  DoctorsRepo doctorsRepo;
  @Autowired
  IllnessesRepo illnessesRepo;
  
  
  
  public List<PatientDto> findAll() {
    List<Patients> patientsList = patientsRepo.findAll();
    List<PatientDto> dtoList = new ArrayList<>();
    for ( Patients p : patientsList ) {
      int doctor = 0;
      if ( p.getDoctors() != null )
        doctor = p.getDoctors().getId();
      
      List<Integer> illnessesList = p.getIllnesses().stream().map( Illnesses::getId ).collect( Collectors.toList() );
      dtoList.add( new PatientDto( p.getId(), p.getName(), p.getDetails(), doctor, illnessesList ) );
    }
    return dtoList;
  }
  
  public Patients save( PatientDto dto ) {
    LOGGER.info( dto.toString() );
    
    Doctors doctor = doctorsRepo.findById( dto.getDoctor() ).orElse( null );
    if ( doctor == null )
      return null;
    
    Set<Illnesses> illnesses = new HashSet<>();
    if ( dto.getIllnesses() != null && dto.getIllnesses().size() > 0 ) {
      illnesses = new HashSet<>( illnessesRepo.findAllById( dto.getIllnesses() ) );
    }
    
    Patients patient = new Patients( dto.getName(), dto.getDetails(), doctor, illnesses );
    return patientsRepo.save( patient );
  }
  
  public int deletePatient( int id ) {
    return patientsRepo.deletePatient( id );
  }
}