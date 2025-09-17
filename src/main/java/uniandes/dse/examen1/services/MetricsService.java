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
        // TODO Calculate the total value of all contracts associated with the given
        // factory
        return 0.0;
    }

    public Double calculateSupplierSatisfactionAverage(String supplierCode) {
        // TODO Calculate the average satisfaction of all contracts associated with the
        // given supplier
        return 0.0;
    }

    public Double calculateContractAverageValue(String supplierCode) {
        // TODO Calculate the average value of all contracts associated with the given
        // supplier
        return 0.0;
    }

}
