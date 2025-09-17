package uniandes.dse.examen1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.dse.examen1.entities.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

}
