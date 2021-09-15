package demo.repo;

import demo.entity.Illnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessesRepo extends JpaRepository<Illnesses, Integer> {
  /**
   * @param id PRIMARY KEY
   * @return number of deleted entries (default implementations are all void)
   */
  @Modifying
  @Query( value = "DELETE FROM illnesses WHERE id = :id"
    , nativeQuery = true )
  int deleteIllness( int id );
  
  
  
}