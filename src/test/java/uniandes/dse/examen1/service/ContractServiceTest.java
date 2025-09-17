package uniandes.dse.examen1.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uniandes.dse.examen1.entities.SupplierEntity;
import uniandes.dse.examen1.entities.FactoryEntity;
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;
import uniandes.dse.examen1.exceptions.InvalidContractException;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.services.SupplierService;
import uniandes.dse.examen1.services.FactoryService;
import uniandes.dse.examen1.services.ContractService;

@DataJpaTest
@Transactional
@Import({ ContractService.class, SupplierService.class, FactoryService.class })
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private FactoryService factoryService;

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    private PodamFactory factory = new PodamFactoryImpl();

    private String name;
    private String supplierCode;
    private String unlimitedSupplierCode;

    @BeforeEach
    void setUp() throws RepeatedSupplierException, RepeatedFactoryException {
        SupplierEntity newSupplier = factory.manufacturePojo(SupplierEntity.class);
        newSupplier = supplierService.createSupplier(newSupplier);
        supplierCode = newSupplier.getSupplierCode();

        SupplierEntity unlimitedSupplier = factory.manufacturePojo(SupplierEntity.class);
        unlimitedSupplier.setCapacity(-1);
        unlimitedSupplier = supplierService.createSupplier(unlimitedSupplier);
        unlimitedSupplierCode = unlimitedSupplier.getSupplierCode();

        FactoryEntity newFactory = factory.manufacturePojo(FactoryEntity.class);
        newFactory = factoryService.createFactory(newFactory);
        name = newFactory.getName();
    }

    /**
     * Tests the normal creation of a contract for a factory with an existing
     * supplier
     * 
     */
    @Test
    void testCreateContract() {
        // TODO
    }

    /**
     * Tests the normal creation of multiple contract for a factory with the same
     * existing supplier
     */
    @Test
    void testCreateMultipleContract() {
        // TODO
    }

    /**
     * Tests the creation of many contracts for a factory with the same
     * existing supplier with unlimited capacity
     */
    @Test
    void testCreateContractWithUnlimitedCapacity() {
        try {
            int numberOfContracts = 50;
            for (int i = 0; i < numberOfContracts; i++) {
                contractService.createContract(name, unlimitedSupplierCode, 1000.0);
            }
        } catch (InvalidContractException e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }

    /**
     * Tests the creation of a contract for a factory but the supplier code is wrong
     */
    @Test
    void testCreateContractMissingSupplier() {
        // TODO
    }

    /**
     * Tests the creation of a contract for a factory with an existing
     * supplier, but the factory name is wrong
     */
    @Test
    void testCreateContractMissingFactory() {
        // TODO
    }

    /**
     * Tests the creation of a contract for a factory with an existing
     * supplier, but the contract value is wrong
     */
    @Test
    void testCreateContractWrongValue() {
        // TODO
    }

    /**
     * Tests the creation of a contract for a factory with an existing
     * supplier, but the supplier has too many contracts (capacity exceeded)
     */
    @Test
    void testCreateContractCapacityExceeded() {
        // TODO
    }

}
