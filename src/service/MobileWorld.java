package service;

import dao.implement.ClientImpl;
import dao.implement.OrderDetailImpl;
import dao.implement.ProductImpl;
import model.Client;
import model.OrderDetail;
import model.Product;

import java.util.ArrayList;

public class MobileWorld {
    ProductImpl productImpl = new ProductImpl();
    ClientImpl clientImpl = new ClientImpl();
    OrderDetailImpl orderDetailImpl = new OrderDetailImpl();

    public ArrayList<Product> productList() {
        ArrayList<Product> listProduct = productImpl.selectProduct();
        return listProduct;
    }

    public void customerRegistration(String customerId, String customerName, int age, String sex) {
        Client client = clientImpl.findById(customerId);
        if (client != null) {
            System.out.println("Customer id already exists");
            return;
        }
        Client newClient = new Client(customerId, customerName, age, sex);
        clientImpl.insertClient(newClient);
    }

    public int buyMobilePhone(String customerId, String productId, int quantity) {
        Client client = clientImpl.findById(customerId);
        Product product = productImpl.findById(productId);
        if (client == null) {
            System.out.println("Customer id does not exist!\nDo you want to create a new one?(Y/N)");
            return -1;
        }
        if (product == null) {
            System.out.println("Product does not exist!");
            return 0;
        }
        OrderDetail orderDetail = new OrderDetail(client.getCustomerId(), product.getId(), quantity);
        int newQuantity = product.getQuantity() - quantity;
        product.setQuantity(newQuantity);
        productImpl.updateProduct(product);
        productImpl.deleteProduct(productId);
        orderDetailImpl.insertOrder(orderDetail);
        return 1;
    }

    public ArrayList<OrderDetail> purchaseHistory(String userId) {
        ArrayList<OrderDetail> orderDetailArrayList = orderDetailImpl.selectByCustomerId(userId);
        return orderDetailArrayList;
    }
}
