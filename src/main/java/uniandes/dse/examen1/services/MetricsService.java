package uniandes.dse.examen1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.repositories.ContractRepository;

@Slf4j
@Service
public class MetricsService {

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ContractRepository contractRepository;

    public Double calculateTotalContractValue(String factoryName) {
        log.info("Calculating total contract value for factory: {}", factoryName);
        
        // Validate factory exists
        if (!factoryRepository.existsByName(factoryName)) {
            log.warn("Factory not found: {}", factoryName);
            return 0.0;
        }

        Double totalValue = contractRepository.sumContractValueByFactoryName(factoryName);
        return totalValue != null ? totalValue : 0.0;
    }

    public Double calculateSupplierSatisfactionAverage(String supplierCode) {
        log.info("Calculating average satisfaction for supplier: {}", supplierCode);
        
        // Validate supplier exists
        if (!supplierRepository.existsByCode(supplierCode)) {
            log.warn("Supplier not found: {}", supplierCode);
            return 0.0;
        }
        
        Double avgSatisfaction = contractRepository.avgSatisfactionBySupplierCode(supplierCode);
        return avgSatisfaction != null ? avgSatisfaction : 0.0;
    }

    public Double calculateContractAverageValue(String supplierCode) {
        log.info("Calculating average contract value for supplier: {}", supplierCode);
        
        // Validate supplier exists
        if (!supplierRepository.existsByCode(supplierCode)) {
            log.warn("Supplier not found: {}", supplierCode);
            return 0.0;
        }
        
        Double avgValue = contractRepository.avgContractValueBySupplierCode(supplierCode);
        return avgValue != null ? avgValue : 0.0;
    }

}
