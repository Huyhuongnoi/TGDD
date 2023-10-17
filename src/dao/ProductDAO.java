package dao;

import java.util.ArrayList;

public interface ProductDAO<Product> {
    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String id);

    ArrayList<Product> selectProduct();

    Product findById(String id);
}
