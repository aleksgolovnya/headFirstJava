package db;

import java.sql.*;

/* Тестовый класс для работы с JDBC, реализована работа с интерфейсом Statement
 * Выполняется запрос SELECT, который выгружает всю информацию из базы данных
 * Реализован вывод полученных данных в консоль
 * */

public class StatementDemo {

    /* JDBC Driver and database url, user and password */
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost/devTest";
    static final String USER = "postgres";
    static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection connection = null;

        try {

            /* Этот интерфейс используется для доступа к БД для общих целей
             * Полезел, когда мы используем статические SQL-выражеения во время работы программы.
             * Этот интерфейс не принимает никаких параметров */
            Statement statement = null;

            /* Регистрируем JDBC Driver
             * Класс драйвера динамически загружается в память,
             * после чего происходит его регистрация */
            Class.forName(JDBC_DRIVER);

            /* Передаем информацию для соединения с БД: url, user, pass
             * и создаем соединение с помощью метода getConnection() */
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            /* Создаем экземпляр statement для выполнениня SQL-запросов */
            statement = connection.createStatement(
                    /* Указатьель двигается только вперед по множеству полученных результатов */
                    ResultSet.TYPE_FORWARD_ONLY,
                    /* Создает экземпляр ResultSet, который может изменять данные */
                    ResultSet.CONCUR_UPDATABLE
            );

            /* Определяет точку созранения и возвращает экземпляр Savepoint */
            Savepoint savepointOne = connection.setSavepoint("savepointOne");

            try {
                /* Перед запуском неоьходимо поменять значения на уникальнык
                 * @throws PSQLException    Если пытаемся добавить данные, которые уже есть в таблице */
                statement.executeUpdate("INSERT INTO java_tutorial VALUES ('Vasya', 'JavaScript', 3400, 21)");

                /* Мы выполнили необходимые нам изменения и вызываем метод commit() */
                connection.commit();
            } catch (SQLException e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                e.printStackTrace();

                /* Выполняем откат изменений, если вызвалось исключение */
                connection.rollback(savepointOne);
            }

            /* Выгружаем все данные из таблицы БД
             * метод executeQuery() возвращает экземпляр ResultSet */
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  java_tutorial");

            /* Метод next() класса ResultSet перемещает указатель на следующий ряд,
             * возвращает false, если следующий ряд находится за пределами множества результатов */
            while (resultSet.next()) {

                /* Получаем значения полей из таблицы БД */
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                int salary = resultSet.getInt("salary");

                /* Вывод элементов, полученных из таблицы БД */
                System.out.println("\nDeveloper " + id);
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

