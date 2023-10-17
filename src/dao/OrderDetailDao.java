package dao;

import java.util.ArrayList;

public interface OrderDetailDao<Order> {
    void insertOrder(Order order);

    void updateOrder(int id, Order order);

    void deleteOrder(int id);

    ArrayList<Order> selectOrder();

    Order findById(int id);

    ArrayList<Order> selectByCustomerId(String customerId);
}
