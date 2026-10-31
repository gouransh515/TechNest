package Spring;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
@RestController
public class ProductController {
 private final ProductService service;
 ProductController(ProductService service){
	 this.service=service;
 }
 
 @GetMapping("/product")
 public Product getProduct(@RequestParam int id) {
     return service.getProduct(id);
 }
 @GetMapping("/products")
 public List<Product> getProducts() {
     return service.getProducts();
 }
 @GetMapping("/test-transaction")
 public void testTransaction() {
     service.addTwoProducts();
 }
@PutMapping("/product")
     public void updateProduct(@RequestParam int id,@RequestBody Product p){
	service.updateProduct(p,id)
;	
}
@DeleteMapping("/product")
public void deleteProduct(@RequestParam int id){
	service.deleteProduct(id);
}
 @PostMapping("/product")
 public void addProduct(@Valid@RequestBody Product product) {
     service.addProduct(product);
 }}

