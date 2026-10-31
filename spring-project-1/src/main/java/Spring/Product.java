package Spring;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
public class Product {



    private int id;
    @NotBlank
    private String name;
    @PositiveOrZero
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
   

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
   

    public double getPrice() {
        return price;
    }


	public void setPrice(double price2) {
		// TODO Auto-generated method stub
		this.price=price2;
	}
}

