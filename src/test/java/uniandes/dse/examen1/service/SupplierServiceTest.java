package uniandes.dse.examen1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.*;


import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uniandes.dse.examen1.services.SupplierService;
import uniandes.dse.examen1.entities.SupplierEntity;
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;

@DataJpaTest
@Transactional
@Import(SupplierService.class)
public class SupplierServiceTest {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory supplier = new PodamFactoryImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateSupplier() {
        // TODO
        SupplierEntity newEntity = supplier.manufacturePojo(SupplierEntity.class);
        String code = newEntity.getSupplierCode();

        try {
            SupplierEntity storedEntity = supplierService.createSupplier(newEntity);
            SupplierEntity retrieved = entityManager.find(SupplierEntity.class, storedEntity.getId());
            assertEquals(code, retrieved.getSupplierCode(), "The code is incorrect");
        } catch (RepeatedSupplierException e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }

    @Test
    void testCreateRepeatedSupplier() {
        // TODO
        SupplierEntity firstEntity = supplier.manufacturePojo(SupplierEntity.class);
        String code = firstEntity.getSupplierCode();

        SupplierEntity repeatedEntity = new SupplierEntity();
        repeatedEntity.setSupplierCode(code);

        try {
            supplierService.createSupplier(firstEntity);
            supplierService.createSupplier(repeatedEntity);
            fail("An exception must be thrown");
        } catch (Exception e) {
        }
    }
}

