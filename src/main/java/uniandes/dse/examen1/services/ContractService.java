package uniandes.dse.examen1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.ContractEntity;
import uniandes.dse.examen1.exceptions.InvalidContractException;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.repositories.ContractRepository;

@Slf4j
@Service
public class ContractService {

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ContractRepository contractRepository;

    public ContractEntity createContract(String factoryName, String supplierCode, Double contractValue)
            throws InvalidContractException {

        if(!factoryRepository.existsByName(factoryName)) {
            throw new InvalidContractException("Factory no existe: " + factoryName);
        }
        if(!supplierRepository.existsByCode(supplierCode)) {
            throw new InvalidContractException("Supplier no existe: " + supplierCode);
        }
        if(contractValue == null || contractValue <= 0) {
            throw new InvalidContractException("Contract no es valido: " + contractValue);
        }

        var factoryEntity = factoryRepository.findByName(factoryName).get();
        var supplierEntity = supplierRepository.findBySupplierCode(supplierCode).get();

        ContractEntity contract = new ContractEntity();
        contract.setFactory(factoryEntity);
        contract.setProvider(supplierEntity);
        contract.setContractValue(contractValue);
        contract.setActive(true);
        contract.setSatisfaction(0);

        return contractRepository.save(contract);
    }
}
