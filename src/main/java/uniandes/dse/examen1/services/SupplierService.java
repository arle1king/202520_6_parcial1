package uniandes.dse.examen1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.SupplierEntity;
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;
import uniandes.dse.examen1.repositories.SupplierRepository;

@Slf4j
@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public SupplierEntity createSupplier(SupplierEntity newSupplier) throws RepeatedSupplierException {
        // TODO
        if(supplierRepository.existsByCode(newSupplier.getSupplierCode())) {
			throw new RepeatedSupplierException(newSupplier.getSupplierCode());
		}
		return supplierRepository.save(newSupplier);
    }
}
