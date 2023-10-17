package model;

public class Product {
    private String id;
    private String productName;
    private float rate;
    private int quantity;

    public Product(String id, String productName, float rate, int quantity) {
        this.id = id;
        this.productName = productName;
        this.rate = rate;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", rate=" + rate +
                ", quantity=" + quantity +
                '}';
    }
}
