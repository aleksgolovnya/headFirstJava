package jdbc;

import java.io.*;
import java.sql.*;

/*
 * Работа с потоками в JDBC при помощи экземпляра PreparedStatement.
 * Приложение создаем в базе данных таблицы с именем Developer,
 * затем содрежимое файла DeveloperExample.xml загружается в созданную таблицу в БД
 * */

//TODO: написать комментарии и провести тестирование приложения

public class StreamingDataDemo {

    /* JDBC Driver and database url, user and password */
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost/devTest";
    static final String USER = "postgres";
    static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            createXMLTable(statement);

            File file = new File("src/main/resources/DeveloperExample.xml");
            long fileLength = file.length();
            FileInputStream fileInputStream = new FileInputStream(file);

            preparedStatement = connection.prepareStatement("INSERT INTO Developer VALUES (?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setAsciiStream(2, fileInputStream, (int) fileLength);
            preparedStatement.execute();

            fileInputStream.close();

            resultSet = statement.executeQuery("SELECT DATA FROM Developer WHERE id=1");

            if (resultSet.next()) {
                InputStream xmlInputStream = resultSet.getAsciiStream(1);
                int c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while ((c = xmlInputStream.read()) != -1) {
                    byteArrayOutputStream.write(c);
                }
                System.out.println(byteArrayOutputStream.toString());
            }
            resultSet.close();
            statement.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createXMLTable(Statement statement) {

        try {
            statement.executeUpdate("CREATE TABLE Developer(id INTEGER, Data varchar)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
