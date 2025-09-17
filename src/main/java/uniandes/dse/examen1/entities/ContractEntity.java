package uniandes.dse.examen1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ContractEntity {

    @PodamExclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The monetary value of the contract. It must be a positive number.
     */
    private Double contractValue;

    /**
     * Indicates if the contract is active or not.
     */
    private Boolean active;

    /**
     * Indicates the satisfaction level of the client with the service provided by
     * the supplier. It must be a number between 0 and 5 (inclusive). 0 indicates
     * that it has not been rated yet.
     */
    private Integer satisfaction;

    // TODO

    @PodamExclude
    @ManyToOne
    private FactoryEntity factory;

    @PodamExclude
    @ManyToOne
    private SupplierEntity provider;
}
