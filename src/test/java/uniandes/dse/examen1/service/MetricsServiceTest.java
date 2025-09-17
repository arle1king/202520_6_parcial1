package uniandes.dse.examen1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uniandes.dse.examen1.entities.ContractEntity;
import uniandes.dse.examen1.entities.SupplierEntity;
import uniandes.dse.examen1.entities.FactoryEntity;
import uniandes.dse.examen1.exceptions.InvalidContractException;
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.repositories.ContractRepository;
import uniandes.dse.examen1.services.SupplierService;
import uniandes.dse.examen1.services.ContractService;
import uniandes.dse.examen1.services.FactoryService;
import uniandes.dse.examen1.services.MetricsService;

@DataJpaTest
@Transactional
@Import({ ContractService.class, SupplierService.class, FactoryService.class, MetricsService.class })
public class MetricsServiceTest {

    @Autowired
    private ContractService contractService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private FactoryService factoryService;
    
    @Autowired
    private MetricsService metricsService;

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    SupplierRepository supplierRepository;
    
    @Autowired
    ContractRepository contractRepository;

    private PodamFactory factory = new PodamFactoryImpl();
    
    private String factoryName;
    private String supplier1Code;
    private String supplier2Code;

    @BeforeEach
    void setUp() throws RepeatedSupplierException, RepeatedFactoryException, InvalidContractException {
        // Create a factory
        FactoryEntity newFactory = factory.manufacturePojo(FactoryEntity.class);
        newFactory = factoryService.createFactory(newFactory);
        factoryName = newFactory.getName();

        // Create two suppliers
        SupplierEntity supplier1 = factory.manufacturePojo(SupplierEntity.class);
        supplier1 = supplierService.createSupplier(supplier1);
        supplier1Code = supplier1.getSupplierCode();

        SupplierEntity supplier2 = factory.manufacturePojo(SupplierEntity.class);
        supplier2 = supplierService.createSupplier(supplier2);
        supplier2Code = supplier2.getSupplierCode();

        // Create some contracts for testing
        // Factory contracts: 1000.0, 2000.0, 3000.0 with supplier1 (satisfaction: 3, 4, 5)
        ContractEntity contract1 = contractService.createContract(factoryName, supplier1Code, 1000.0);
        contract1.setSatisfaction(3);
        contractRepository.save(contract1);
        
        ContractEntity contract2 = contractService.createContract(factoryName, supplier1Code, 2000.0);
        contract2.setSatisfaction(4);
        contractRepository.save(contract2);
        
        ContractEntity contract3 = contractService.createContract(factoryName, supplier1Code, 3000.0);
        contract3.setSatisfaction(5);
        contractRepository.save(contract3);

        // Additional contract with supplier2: 5000.0 (satisfaction: 2)
        ContractEntity contract4 = contractService.createContract(factoryName, supplier2Code, 5000.0);
        contract4.setSatisfaction(2);
        contractRepository.save(contract4);
    }

    @Test
    void testCalculateTotalContractValue() {
        // Total contracts for the factory: 1000 + 2000 + 3000 + 5000 = 11000
        Double totalValue = metricsService.calculateTotalContractValue(factoryName);
        assertNotNull(totalValue);
        assertEquals(11000.0, totalValue, 0.01);
    }

    @Test
    void testCalculateTotalContractValueNonExistentFactory() {
        Double totalValue = metricsService.calculateTotalContractValue("NON_EXISTENT_FACTORY");
        assertNotNull(totalValue);
        assertEquals(0.0, totalValue, 0.01);
    }

    @Test
    void testCalculateSupplierSatisfactionAverage() {
        // Supplier1 satisfaction: (3 + 4 + 5) / 3 = 4.0
        Double avgSatisfaction = metricsService.calculateSupplierSatisfactionAverage(supplier1Code);
        assertNotNull(avgSatisfaction);
        assertEquals(4.0, avgSatisfaction, 0.01);
        
        // Supplier2 satisfaction: 2.0
        Double avgSatisfaction2 = metricsService.calculateSupplierSatisfactionAverage(supplier2Code);
        assertNotNull(avgSatisfaction2);
        assertEquals(2.0, avgSatisfaction2, 0.01);
    }

    @Test
    void testCalculateSupplierSatisfactionAverageNonExistentSupplier() {
        Double avgSatisfaction = metricsService.calculateSupplierSatisfactionAverage("NON_EXISTENT_SUPPLIER");
        assertNotNull(avgSatisfaction);
        assertEquals(0.0, avgSatisfaction, 0.01);
    }

    @Test
    void testCalculateContractAverageValue() {
        // Supplier1 contract values: (1000 + 2000 + 3000) / 3 = 2000.0
        Double avgValue = metricsService.calculateContractAverageValue(supplier1Code);
        assertNotNull(avgValue);
        assertEquals(2000.0, avgValue, 0.01);
        
        // Supplier2 contract values: 5000.0
        Double avgValue2 = metricsService.calculateContractAverageValue(supplier2Code);
        assertNotNull(avgValue2);
        assertEquals(5000.0, avgValue2, 0.01);
    }

    @Test
    void testCalculateContractAverageValueNonExistentSupplier() {
        Double avgValue = metricsService.calculateContractAverageValue("NON_EXISTENT_SUPPLIER");
        assertNotNull(avgValue);
        assertEquals(0.0, avgValue, 0.01);
    }
}
