package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private final Properties properties;
    private Connection connection;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        String path = "src/main/java/ru/job4j/jdbc/app.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        try (TableEditor tableEditor = new TableEditor(properties)) {
            String tableName1 = "test_devices";
            tableEditor.createTable(tableName1);
            System.out.println(getTableScheme(tableEditor.connection, tableName1));
            tableEditor.addColumn(tableName1, "id", "serial primary key");
            tableEditor.addColumn(tableName1, "fname", "varchar(255)");
            tableEditor.addColumn(tableName1, "price", "float");
            System.out.println(getTableScheme(tableEditor.connection, tableName1));
            tableEditor.renameColumn(tableName1, "price", "fprice");
            System.out.println(getTableScheme(tableEditor.connection, tableName1));
            tableEditor.dropColumn(tableName1, "fprice");
            System.out.println(getTableScheme(tableEditor.connection, tableName1));
            tableEditor.dropTable(tableName1);
        }
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Приватный метод для выполнения запросов
     *
     * @param s строка запроса
     * @throws SQLException ошибка SQL
     */
    private void execute(String s) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        }
    }

    /**
     * создает пустую таблицу без столбцов с указанным именем;
     *
     * @param tableName имя таблицы
     * @throws SQLException ошибка SQL
     */
    public void createTable(String tableName) throws SQLException {
        String s = String.format("create table IF NOT EXISTS %s()", tableName);
        execute(s);
    }

    /**
     * удаляет таблицу по указанному имени;
     *
     * @param tableName имя таблицы
     * @throws SQLException ошибка SQL
     */
    public void dropTable(String tableName) throws SQLException {
        String s = String.format("drop table %s", tableName);
        execute(s);
    }

    /**
     * добавляет столбец в таблицу;
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     * @param type       тип столбца
     * @throws SQLException ошибка SQL
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String s = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", tableName, columnName, type);
        execute(s);
    }

    /**
     * @param tableName  удаляет столбец из таблицы;
     * @param columnName имя столбца для удаления
     * @throws SQLException ошибка SQL
     */
    public void dropColumn(String tableName, String columnName) throws SQLException {
        String s = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s", tableName, columnName);
        execute(s);
    }

    /**
     * переименовывает столбец.
     *
     * @param tableName     имя таблицы
     * @param columnName    старое имя столбца
     * @param newColumnName новое имя столбца
     * @throws SQLException ошибка SQL
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String s = String.format("ALTER TABLE %s RENAME %s TO %s;", tableName, columnName, newColumnName);
        execute(s);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}