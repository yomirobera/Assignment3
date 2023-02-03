package no.accelerate.assignment3WebAPIandSpring.exceptions.error;

/**
 * Custom error handling
 * Create a dummy model representing the structure
 * This is to avoid blank response and to avoid conflicts with implementing clients
 */
public class ApiErrorResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;
}
