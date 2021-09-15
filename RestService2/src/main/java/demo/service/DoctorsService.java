package demo.service;

import demo.entity.Doctors;
import demo.repo.DoctorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorsService {
  @Autowired
  DoctorsRepo doctorsRepo;

  
  public List<Doctors> findAll() {
    return doctorsRepo.findAll();
  }
  
  public Doctors findById( int id ) {
    return doctorsRepo.findById( id ).orElse( null );
  }
  
  public Doctors save( Doctors doctors ) {
    return doctorsRepo.save( doctors );
  }
  
  public void delete( int id ) {
    doctorsRepo.deleteById( id );
  }
  
  public int deleteDoctor( int id ) {
    return doctorsRepo.deleteDoctor( id );
  }
}