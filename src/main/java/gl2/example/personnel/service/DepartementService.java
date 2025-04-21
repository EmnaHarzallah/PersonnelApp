package gl2.example.personnel.service;

import gl2.example.personnel.model.Departement;
import gl2.example.personnel.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementById(int id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement with ID " + id + " not found."));
    }

    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public void deleteDepartement(int id) {
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Departement with ID " + id + " not found.");
        }
    }

    public Departement updateDepartement(Departement departement) {
        Optional<Departement> existing = departementRepository.findById(departement.getId());
        if (existing.isPresent()) {
            return departementRepository.save(departement);
        } else {
            throw new RuntimeException("Departement with ID " + departement.getId() + " not found.");
        }
    }
}
