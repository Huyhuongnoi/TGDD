package dao.implement;

import dao.DataSource;
import dao.ProductDAO;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static constant.Constant.ConstantProduct.*;


public class ProductImpl implements ProductDAO<Product> {
    @Override
    public void insertProduct(Product product) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = INSERT_PRODUCT.formatted(TABLE_NAME, COLUMN_1, COLUMN_2,
                    COLUMN_3, COLUMN_4);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setFloat(3, product.getRate());
            preparedStatement.setInt(4, product.getQuantity());
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
    public void updateProduct(Product product) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = UPDATE_PRODUCT.formatted(TABLE_NAME, COLUMN_2, COLUMN_3,
                    COLUMN_4, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setFloat(2, product.getRate());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getId());
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
    public void deleteProduct(String id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = DELETE_PRODUCT.formatted(TABLE_NAME, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
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
    public ArrayList<Product> selectProduct() {
        Connection connection = null;
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_PRODUCT.formatted(TABLE_NAME);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(COLUMN_1);
                String productName = resultSet.getString(COLUMN_2);
                float rate = resultSet.getFloat(COLUMN_3);
                int quantity = resultSet.getInt(COLUMN_4);
                Product product = new Product(id, productName, rate, quantity);
                productArrayList.add(product);
            }
            if (productArrayList != null) {
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
        return productArrayList;
    }

    @Override
    public Product findById(String id) {
        Connection connection = null;
        Product product = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_BY_ID.formatted(TABLE_NAME, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idProduct = resultSet.getString(COLUMN_1);
                String productName = resultSet.getString(COLUMN_2);
                float rate = resultSet.getFloat(COLUMN_3);
                int quantity = resultSet.getInt(COLUMN_4);
                product = new Product(idProduct, productName, rate, quantity);
            }
            if (product != null) {
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
        return product;
    }
}
