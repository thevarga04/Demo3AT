package demo.repo;

import demo.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepo extends JpaRepository<Patients, Integer> {
}