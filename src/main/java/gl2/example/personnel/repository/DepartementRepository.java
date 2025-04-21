package gl2.example.personnel.repository;

import gl2.example.personnel.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    Optional<Departement> findByName(String name); // Example of custom query method
}

