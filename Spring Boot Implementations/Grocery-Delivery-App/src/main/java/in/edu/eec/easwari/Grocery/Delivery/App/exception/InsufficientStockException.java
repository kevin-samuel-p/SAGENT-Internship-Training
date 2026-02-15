package in.edu.eec.easwari.Grocery.Delivery.App.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}