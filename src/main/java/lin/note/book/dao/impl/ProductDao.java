package lin.note.book.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import lin.note.book.dao.IProductDao;
import lin.note.book.model.ProductBean;
import lin.note.book.util.PropertiesTool;

public class ProductDao implements IProductDao {

    @Override
    public ProductBean getProductByBooknum(String booknum) {
        ProductBean result = null;

        Properties properties = PropertiesTool.getDatabaseProperties();
        String classForName = properties.getProperty("class-for-name");
        String jdbcURL = properties.getProperty("jdbc-url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(classForName);
            connection = DriverManager.getConnection(jdbcURL, username, password);
            preparedStatement = connection.prepareStatement("SELECT bookname, price FROM product WHERE booknum = ?");
            preparedStatement.setString(1, booknum);
            resultSet = preparedStatement.executeQuery();

            if (true == resultSet.next()) {
                String bookname = resultSet.getString("bookname");
                int price = resultSet.getInt("price");

                java.util.Date createTime = new java.util.Date();
                ProductBean productBean = new ProductBean();

                productBean.setBooknum(booknum);
                productBean.setBookname(bookname);
                productBean.setPrice(price);
                productBean.setCreateTime(createTime);

                result = productBean;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
