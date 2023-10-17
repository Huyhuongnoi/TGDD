package dao.implement;

import dao.ClientDAO;
import dao.DataSource;
import model.Client;

import static constant.Constant.ConstantClient.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientImpl implements ClientDAO<Client> {
    @Override
    public void insertClient(Client client) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = INSERT_CLIENT.formatted(TABLE_NAME, COLUMN_1, COLUMN_2,
                    COLUMN_3, COLUMN_4);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getCustomerId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setFloat(3, client.getAge());
            preparedStatement.setString(4, client.getSex());
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
    public void updateClient(Client client) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = UPDATE_CLIENT.formatted(TABLE_NAME, COLUMN_2, COLUMN_3,
                    COLUMN_4, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setFloat(2, client.getAge());
            preparedStatement.setString(3, client.getSex());
            preparedStatement.setString(4, client.getCustomerId());
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
    public void deleteClient(String id) {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = DELETE_CLIENT.formatted(TABLE_NAME, COLUMN_1);
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
    public ArrayList<Client> selectProduct() {
        Connection connection = null;
        ArrayList<Client> clientArrayList = new ArrayList<Client>();
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_CLIENT.formatted(TABLE_NAME);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString(COLUMN_1);
                String customerName = resultSet.getString(COLUMN_2);
                int age = resultSet.getInt(COLUMN_3);
                String sex = resultSet.getString(COLUMN_4);
                Client client = new Client(customerId, customerName, age, sex);
                clientArrayList.add(client);
            }
            if (clientArrayList != null) {
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
        return clientArrayList;
    }

    @Override
    public Client findById(String id) {
        Connection connection = null;
        Client client = null;
        try {
            connection = DataSource.getInstance().getConnection();
            String sql = SELECT_BY_ID.formatted(TABLE_NAME, COLUMN_1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString(COLUMN_1);
                String customerName = resultSet.getString(COLUMN_2);
                int age = resultSet.getInt(COLUMN_3);
                String sex = resultSet.getString(COLUMN_4);
                client = new Client(customerId, customerName, age, sex);
            }
            if (client != null) {
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
        return client;
    }
}
