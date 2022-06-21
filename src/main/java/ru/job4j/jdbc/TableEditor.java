package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    /**
     * настройки для подключения
     */
    private final Properties properties;
    /**
     * соединение с базой данных
     */
    private Connection connection;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    /**
     * отрисовывает таблицу для проверки состояния
     *
     * @param connection соединение с базой
     * @param tableName  имя таблицы
     * @return возвращает строковое представление базы данных с префиксом, данными и суффиксом
     * @throws Exception при возникновении ошибок
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from %s limit 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static TableEditor of(String fileName) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(fileName)) {
            config.load(in);
            return new TableEditor(config);
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = of("app.properties")) {
            String tableName1 = "test_devices";
            tableEditor.createTable(tableName1);
            tableEditor.addColumn(tableName1, "id", "serial primary key");
            tableEditor.addColumn(tableName1, "fname", "varchar(255)");
            tableEditor.addColumn(tableName1, "price", "float");
            tableEditor.renameColumn(tableName1, "price", "fprice");
            tableEditor.dropColumn(tableName1, "fprice");
            tableEditor.dropTable(tableName1);
        }
    }

    /**
     * Метод устанавливаем connection для экземляра
     *
     * @throws ClassNotFoundException если класс не найден
     * @throws SQLException           в случае ошибка SQL
     */
    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Приватный метод для выполнения запросов и отрисовки таблицы
     *
     * @param request   срока запроса
     * @param tableName имя таблицы для отрисовки
     * @throws Exception ошибки
     */
    private void execute(String request, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        }
        if (!request.startsWith("drop table")) {
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    /**
     * создает пустую таблицу без столбцов с указанным именем;
     *
     * @param tableName имя таблицы
     * @throws SQLException ошибка SQL
     */
    public void createTable(String tableName) throws Exception {
        String s = String.format("create table IF NOT EXISTS %s()", tableName);
        execute(s, tableName);
    }

    /**
     * удаляет таблицу по указанному имени;
     *
     * @param tableName имя таблицы
     * @throws SQLException ошибка SQL
     */
    public void dropTable(String tableName) throws Exception {
        String s = String.format("drop table %s", tableName);
        execute(s, tableName);
    }

    /**
     * добавляет столбец в таблицу;
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца
     * @param type       тип столбца
     * @throws SQLException ошибка SQL
     */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String s = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", tableName, columnName, type);
        execute(s, tableName);
    }

    /**
     * метод удаляет столбец из таблицы
     *
     * @param tableName  имя таблицы
     * @param columnName имя столбца для удаления
     * @throws SQLException ошибка SQL
     */
    public void dropColumn(String tableName, String columnName) throws Exception {
        String s = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s", tableName, columnName);
        execute(s, tableName);
    }

    /**
     * переименовывает столбец.
     *
     * @param tableName     имя таблицы
     * @param columnName    старое имя столбца
     * @param newColumnName новое имя столбца
     * @throws SQLException ошибка SQL
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String s = String.format("ALTER TABLE %s RENAME %s TO %s;", tableName, columnName, newColumnName);
        execute(s, tableName);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}