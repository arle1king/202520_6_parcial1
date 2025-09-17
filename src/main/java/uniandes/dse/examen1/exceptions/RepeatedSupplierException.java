package uniandes.dse.examen1.exceptions;

public class RepeatedSupplierException extends Exception {

    private String supplierCode;

    public RepeatedSupplierException(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public String getMessage() {
        return "The supplier " + supplierCode + " is repeated.";
    }

}
