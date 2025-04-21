package gl2.example.personnel.repository;

import gl2.example.personnel.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
