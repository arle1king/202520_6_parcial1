package uniandes.dse.examen1.exceptions;

public class RepeatedFactoryException extends Exception {

    private String name;

    public RepeatedFactoryException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "The factory name " + name + " is repeated.";
    }

}
