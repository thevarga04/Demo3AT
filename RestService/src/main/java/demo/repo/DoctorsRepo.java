package demo.repo;

import demo.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepo extends JpaRepository<Doctors, Integer> {
}