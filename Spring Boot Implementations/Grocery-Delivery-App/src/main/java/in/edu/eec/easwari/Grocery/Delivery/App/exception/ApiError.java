package in.edu.eec.easwari.Grocery.Delivery.App.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiError {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public ApiError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
}