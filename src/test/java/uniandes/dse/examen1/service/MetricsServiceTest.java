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
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.services.SupplierService;
import uniandes.dse.examen1.services.ContractService;
import uniandes.dse.examen1.services.FactoryService;

@DataJpaTest
@Transactional
@Import({ ContractService.class, SupplierService.class, FactoryService.class })
public class MetricsServiceTest {

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

    @BeforeEach
    void setUp() throws RepeatedSupplierException, RepeatedFactoryException {
    }

    @Test
    void testFailure() {
        // TODO
        fail("always fails ...");
    }
}
