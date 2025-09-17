package uniandes.dse.examen1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    @PodamExclude
    @OneToMany(mappedBy = "factory")
    private List<ContractEntity> contracts = new ArrayList<ContractEntity>();
    // TODO

    /**
     * A list of all the suppliers that the factory has ever had. No supplier should
     * appear more than once in this list.
     */

    // TODO
    @PodamExclude
    @ManyToMany
    @JoinTable(
        name = "ruta_estacion",
        joinColumns = @JoinColumn(name = "factory_name"),
        inverseJoinColumns = @JoinColumn(name = "supplier_code")
    )
    private List<SupplierEntity> providers = new ArrayList<>();
}
