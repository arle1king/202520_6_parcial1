package uniandes.dse.examen1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uniandes.dse.examen1.entities.FactoryEntity;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.services.FactoryService;

@DataJpaTest
@Transactional
@Import(FactoryService.class)
public class FactoryServiceTest {

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateFactory() {
        FactoryEntity newEntity = factory.manufacturePojo(FactoryEntity.class);
        String name = newEntity.getName();

        try {
            FactoryEntity storedEntity = factoryService.createFactory(newEntity);
            FactoryEntity retrieved = entityManager.find(FactoryEntity.class, storedEntity.getId());
            assertEquals(name, retrieved.getName(), "The name is incorrect");
        } catch (RepeatedFactoryException e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }

    @Test
    void testCreateRepeatedFactory() {
        FactoryEntity firstEntity = factory.manufacturePojo(FactoryEntity.class);
        String name = firstEntity.getName();

        FactoryEntity repeatedEntity = new FactoryEntity();
        repeatedEntity.setName(name);

        try {
            factoryService.createFactory(firstEntity);
            factoryService.createFactory(repeatedEntity);
            fail("An exception must be thrown");
        } catch (Exception e) {
        }
    }
}
