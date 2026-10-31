package Spring;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ProductService {
private final ProductRepository repository;
ProductService(ProductRepository repository){
	this.repository=repository;
	
}
public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }
    
}
public static class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String message) {
        super(message);
    }
}
public static class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
public List<Product> getProducts() {
	List<Product> product=repository.getProducts();
	
	for(  Product ll:product) {
	double price=ll.getPrice();
	if(price>50000) {
		price=price-price/10;
	}
	ll.setPrice(price);
	
	}
	return product;
	
	}
public Product getProduct(int id) {
	Product product;
	
	 try {
	        product = repository.getProduct(id);
	    } catch (EmptyResultDataAccessException ex) {
	        throw new ProductNotFoundException(
	            "Product with ID " + id + " was not found"
	        );
	    }
	double price=product.getPrice();
	if(price>50000) {
		price=price-price/10;
	}
	product.setPrice(price);
	return product;
	}
public void addProduct(Product p1){
	if(p1.getPrice()<0) {
		throw new InvalidProductException("the price is invalid");
	}
	try {
        repository.addProduct(p1);
    } catch (DuplicateKeyException ex) {
        throw new DuplicateProductException(
            "A product with ID " + p1.getId() + " already exists"
        );}
}
public void updateProduct(Product p, int id) {
	int rowsUpdated=repository.updateProduct(p,id);
	if(rowsUpdated==0) {
		throw new ProductNotFoundException(
	            "Product with ID " + id + " was not found");}
	}

@Transactional
public void addTwoProducts() {

    Product p1 = new Product(4, "Keyboard", 3000);
    Product p2 = new Product(1, "Another Laptop", 60000);

    repository.addProduct(p1);
    repository.addProduct(p2);
}

public void deleteProduct(int id) {
	int rowsDeleted=repository.deleteProduct(id);
	if(rowsDeleted==0) {
		throw new ProductNotFoundException(
	            "Product with ID " + id + " was not found"
	        );
	}
}
}
