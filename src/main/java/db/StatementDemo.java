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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = null;

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

        /* Создаем экземпляр statement для выполнениня SQL-запросов */
        statement = connection.createStatement(
                /* Указатьель двигается только вперед по множеству полученных результатов */
                ResultSet.TYPE_FORWARD_ONLY,
                /* Создает экземпляр ResultSet, который может изменять данные */
                ResultSet.CONCUR_UPDATABLE
        );

        /* Выгружаем все данные из таблицы БД
         * метод executeQuery() возвращает экземпляр ResultSet */
        ResultSet resultSet = statement.executeQuery("SELECT * FROM  java_tutorial");

//        Todo: реализовать добавление новых элементов в таблицу и обновление полей у текущих
//        /* Добавляем нового разработчика в таблицу */
//        statement.executeQuery("INSERT into java_tutorial values (5, 'Mike', 'PHP', 1500)");
//        /* Перемещает указатель на первый ряд */
//        resultSet.first();
//        /* Обновлаяет поле salary у всех разработчиков */
//        while (resultSet.next()) {
//            int newSalary = resultSet.getInt("salary") * 10;
//            resultSet.updateInt("salary", newSalary);
//            resultSet.updateRow();
//        }

        /* Метод next() класса ResultSet перемещает указатель на следующий ряд,
         * возвращает false, если следующий ряд находится за пределами множества результатов */
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\nDeveloper " + id);
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: " + salary);
        }
        resultSet.close();
    }
}
