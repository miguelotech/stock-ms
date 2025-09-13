package com.nttdata.dockerized.postgresql.exception;

public class InsufficientStockException extends RuntimeException {
    
    private final Integer productId;
    private final Integer requestedQuantity;
    private final Integer availableQuantity;
    
    public InsufficientStockException(Integer productId, Integer requestedQuantity, Integer availableQuantity) {
        super(String.format("Insufficient stock for product %d. Requested: %d, Available: %d", 
                productId, requestedQuantity, availableQuantity));
        this.productId = productId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }
    
    public InsufficientStockException(String message) {
        super(message);
        this.productId = null;
        this.requestedQuantity = null;
        this.availableQuantity = null;
    }
    
    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
        this.productId = null;
        this.requestedQuantity = null;
        this.availableQuantity = null;
    }
    
    public Integer getProductId() {
        return productId;
    }
    
    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }
    
    public Integer getAvailableQuantity() {
        return availableQuantity;
    }
}
