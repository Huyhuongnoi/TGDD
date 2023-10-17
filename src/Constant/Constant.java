package Constant;

public class Constant {
    public static class ConstantProduct {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_1 = "id";
        public static final String COLUMN_2 = "product_name";
        public static final String COLUMN_3 = "rate";
        public static final String COLUMN_4 = "quantity";
        public static final String INSERT_PRODUCT = "INSERT INTO %s(%s, %s, %s, %s)VALUES (?, ?, ?, ?)";
        public static final String UPDATE_PRODUCT = "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?";
        public static final String DELETE_PRODUCT = "DELETE FROM %s WHERE %s = ?";
        public static final String SELECT_PRODUCT = "SELECT * FROM %s";
        public static final String SELECT_BY_ID = "SELECT * FROM %s WHERE %s = ?";
    }

    public static class ConstantClient {
        public static final String TABLE_NAME = "client";
        public static final String COLUMN_1 = "customer_id";
        public static final String COLUMN_2 = "customer_name";
        public static final String COLUMN_3 = "age";
        public static final String COLUMN_4 = "sex";
        public static final String INSERT_CLIENT = "INSERT INTO %s(%s, %s, %s, %s)VALUES (?, ?, ?, ?)";
        public static final String UPDATE_CLIENT = "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?";
        public static final String DELETE_CLIENT = "DELETE FROM %s WHERE %s = ?";
        public static final String SELECT_CLIENT = "SELECT * FROM %s";
        public static final String SELECT_BY_ID = "SELECT * FROM %s WHERE %s = ?";
    }

    public static class ConstantOrder {
        public static final String TABLE_NAME = "order_detail";
        public static final String COLUMN_1 = "id";
        public static final String COLUMN_2 = "customer_id";
        public static final String COLUMN_3 = "product_id";
        public static final String COLUMN_4 = "quantity";
        public static final String INSERT_Order = "INSERT INTO %s(%s, %s, %s)VALUES (?, ?, ?)";
        public static final String UPDATE_Order = "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?";
        public static final String DELETE_Order = "DELETE FROM %s WHERE %s = ?";
        public static final String SELECT_Order = "SELECT * FROM %s";
        public static final String SELECT_BY_ID = "SELECT * FROM %s WHERE %s = ?";
        public static final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM %s WHERE %s = ?";
    }

}
