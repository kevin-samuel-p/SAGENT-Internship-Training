package in.edu.eec.easwari.Grocery.Delivery.App.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}