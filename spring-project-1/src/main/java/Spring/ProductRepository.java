package Spring;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
	   public List<Product> getProducts() {

	        String sql = "SELECT * FROM products ";

	        return jdbcTemplate.query(
	            sql,
	            (rs, rowNum) -> new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getDouble("price")
	            )
	            
	        );
	    }
	   public Product getProduct(int id) {

	        String sql = "SELECT * FROM products WHERE id = ?";

	        return jdbcTemplate.queryForObject(
	            sql,
	            (rs, rowNum) -> new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getDouble("price")
	            ),
	            id
	        );}
	   public void addProduct(Product product) {
		    String sql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";

		    jdbcTemplate.update(
		        sql,
		        product.getId(),
		        product.getName(),
		        product.getPrice()
		    );}
		public int   updateProduct(Product product, int id) {
			String sql="UPDATE products SET price=? WHERE id=?";
			return jdbcTemplate.update(
					   sql,
				       
				        
				        product.getPrice(),
				        id);
		}
		

private final JdbcTemplate jdbcTemplate;
public ProductRepository(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate=jdbcTemplate;
}
public int deleteProduct(int id) {
	String sql="DELETE FROM products where id=?";
	return jdbcTemplate.update(sql,id);

}
}
