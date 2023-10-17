package Implement;

import dao.DataSource;
import dao.OrderDetailDao;
import model.OrderDetail;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Constant.Constant.ConstantOrder.*;

public class OrderDetailImpl implements OrderDetailDao<OrderDetail> {
    @Override
    public void insertOrder(OrderDetail orderDetail) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = INSERT_Order.formatted(TABLE_NAME, COLUMN_2,
                    COLUMN_3, COLUMN_4);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDetail.getCustomerId());
            preparedStatement.setString(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("added successfully!");
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
    }

    @Override
    public void updateOrder(int id, OrderDetail orderDetail) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = UPDATE_Order.formatted(TABLE_NAME, COLUMN_2, COLUMN_3,
                    COLUMN_4, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDetail.getCustomerId());
            preparedStatement.setString(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setInt(4, id);
            int result = preparedStatement.executeUpdate();
            connection.commit();
            if (result != 0) {
                System.out.println("update successfully!");
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
    }

    @Override
    public void deleteOrder(int id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = DELETE_Order.formatted(TABLE_NAME, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("delete successfully!");
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
    }

    @Override
    public ArrayList<OrderDetail> selectOrder() {
        Connection connection = null;
        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<OrderDetail>();
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_Order.formatted(TABLE_NAME);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString(COLUMN_2);
                String productId = resultSet.getString(COLUMN_3);
                int quantity = resultSet.getInt(COLUMN_4);
                OrderDetail orderDetail = new OrderDetail(customerId, productId, quantity);
                orderDetailArrayList.add(orderDetail);
            }
            if (orderDetailArrayList != null) {
                System.out.println("select successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
        return orderDetailArrayList;
    }

    @Override
    public OrderDetail findById(int id) {
        Connection connection = null;
        OrderDetail orderDetail = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_BY_ID.formatted(TABLE_NAME, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString(COLUMN_2);
                String productId = resultSet.getString(COLUMN_3);
                int quantity = resultSet.getInt(COLUMN_4);
                orderDetail = new OrderDetail(customerId, productId, quantity);
            }
            if (orderDetail != null) {
                System.out.println("select by id successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
        return orderDetail;
    }

    @Override
    public ArrayList<OrderDetail> selectByCustomerId(String userId) {
        Connection connection = null;
        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<OrderDetail>();
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_BY_CUSTOMER_ID.formatted(TABLE_NAME, userId);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString(COLUMN_2);
                String productId = resultSet.getString(COLUMN_3);
                int quantity = resultSet.getInt(COLUMN_4);
                OrderDetail orderDetail = new OrderDetail(customerId, productId, quantity);
                orderDetailArrayList.add(orderDetail);
            }
            if (orderDetailArrayList != null) {
                System.out.println("select successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing");
                }
            }
        }
        return orderDetailArrayList;
    }
}
