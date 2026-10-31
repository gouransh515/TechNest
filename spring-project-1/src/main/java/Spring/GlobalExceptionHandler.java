package Spring;

import Spring.ProductService.DuplicateProductException;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import Spring.ProductService.InvalidProductException;
import Spring.ProductService.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<String> handleInvalidProduct(InvalidProductException ex) {
    	
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(
            MethodArgumentNotValidException ex) {
    	 FieldError error = ex.getBindingResult().getFieldError();
    	  if (error == null) {
    	        return ResponseEntity.badRequest()
    	                .body("Product validation failed");
    	    }
    	    String message = error.getField() + ": "
    	            + error.getDefaultMessage();
        return ResponseEntity.badRequest()
                .body(message);
    }
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<String> handleDuplicateKey(DuplicateProductException ex){
    	return ResponseEntity.status(409).body(ex.getMessage());
    }
}