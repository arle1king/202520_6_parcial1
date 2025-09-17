package uniandes.dse.examen1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uniandes.dse.examen1.entities.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

    // Find all contracts for a specific factory by name
    @Query("SELECT c FROM ContractEntity c WHERE c.factory.name = :factoryName")
    List<ContractEntity> findByFactoryName(@Param("factoryName") String factoryName);
    
    // Find all contracts for a specific supplier by code
    @Query("SELECT c FROM ContractEntity c WHERE c.provider.supplierCode = :supplierCode")
    List<ContractEntity> findByProviderSupplierCode(@Param("supplierCode") String supplierCode);
    
    // Calculate total contract value for a factory
    @Query("SELECT SUM(c.contractValue) FROM ContractEntity c WHERE c.factory.name = :factoryName")
    Double sumContractValueByFactoryName(@Param("factoryName") String factoryName);
    
    // Calculate average satisfaction for a supplier
    @Query("SELECT AVG(c.satisfaction) FROM ContractEntity c WHERE c.provider.supplierCode = :supplierCode AND c.satisfaction > 0")
    Double avgSatisfactionBySupplierCode(@Param("supplierCode") String supplierCode);
    
    // Calculate average contract value for a supplier
    @Query("SELECT AVG(c.contractValue) FROM ContractEntity c WHERE c.provider.supplierCode = :supplierCode")
    Double avgContractValueBySupplierCode(@Param("supplierCode") String supplierCode);
}
