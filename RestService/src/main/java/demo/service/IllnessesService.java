package demo.service;

import demo.entity.Illnesses;
import demo.repo.IllnessesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IllnessesService {
  @Autowired
  IllnessesRepo illnessesRepo;
  
  
  
  public List<Illnesses> findAll() {
    return illnessesRepo.findAll();
  }
  
  public Illnesses findById( int id ) {
    return illnessesRepo.findById( id ).orElse( null );
  }
  
  public Illnesses save( Illnesses illnesses ) {
    return illnessesRepo.save( illnesses );
  }
  
  public void delete( int id ) {
    illnessesRepo.deleteById( id );
  }
  
  public int deleteIllness( int id ) {
    return illnessesRepo.deleteIllness( id );
  }
}