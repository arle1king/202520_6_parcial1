package uniandes.dse.examen1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class FactoryEntity {

    @PodamExclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name for the factory. It should be unique.
     */
    private String name;

    /**
     * The location of the factory (e.g., "123 Main St, Springfield, USA").
     */
    private String location;

    /**
     * A list of the contracts that the factory has had with suppliers.
     */
    // TODO

    /**
     * A list of all the suppliers that the factory has ever had. No supplier should
     * appear more than once in this list.
     */
    // TODO
}
