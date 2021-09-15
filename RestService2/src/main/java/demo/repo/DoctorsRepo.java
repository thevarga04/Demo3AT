package demo.repo;

import demo.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepo extends JpaRepository<Doctors, Integer> {
  /**
   * @param id PRIMARY KEY
   * @return number of deleted entries (default implementations are all void)
   */
  @Modifying
  @Query( value = "DELETE FROM doctors WHERE id = :id"
    , nativeQuery = true )
  int deleteDoctor( int id );
}