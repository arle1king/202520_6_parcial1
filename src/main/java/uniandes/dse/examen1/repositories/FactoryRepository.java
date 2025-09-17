package uniandes.dse.examen1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.dse.examen1.entities.FactoryEntity;

@Repository
public interface FactoryRepository extends JpaRepository<FactoryEntity, Long> {
    Optional<FactoryEntity> findByName(String name);
    boolean existsByName(String name);
}
