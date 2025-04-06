

package com.zt.eweb.framework.event.stream.dispatch.sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionProvider {

    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

    public static void closeConnection() {

        try {
            Connection connection = connection();

            if (connection != null) {
                connection.close();

                // System.out.println("---CONNECTION CLOSED");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Cannot close connection because: "
                            + e.getMessage(),
                    e);
        } finally {
            connectionHolder.set(null);
        }
    }

    public static Connection connection() {
        Connection connection = connectionHolder.get();

        return connection;
    }

    public static Connection connection(DataSource aDataSource) {

        Connection connection = connection();

        try {
            if (connection == null) {
                connection = aDataSource.getConnection();

                connectionHolder.set(connection);

                // System.out.println("CONNECTION OPENED");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Connection not provided because: "
                            + e.getMessage(),
                    e);
        }

        return connection;
    }
}
