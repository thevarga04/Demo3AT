package demo.repo;

import demo.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepo extends JpaRepository<Patients, Integer> {
  /**
   * @param id PRIMARY KEY
   * @return number of deleted entries (default implementations are all void)
   */
  @Modifying
  @Query( value = "DELETE FROM patients WHERE id = :id"
    , nativeQuery = true )
  int deletePatient( int id );
}