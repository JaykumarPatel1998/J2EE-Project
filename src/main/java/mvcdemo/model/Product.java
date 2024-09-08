package mvcdemo.model;

public class Product {
    private int product_id;
    private String product_name;
    private String model;
    private String brand;

    public Product(int product_id, String product_name, String model, String brand) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.model = model;
        this.brand = brand;
    }

    // Getters and setters for each attribute
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}

