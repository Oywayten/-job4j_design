package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Oywayten
 * version 1.01 2022-06-22
 * Класс для импорта списка спамероd (типа User), из файла dump, в базу spammer
 */
public class ImportDB {

    /**
     * В поле хранятся параметры для подключения к базе из файла app1.properties
     */
    private final Properties cfg;

    /**
     * Строковое поле с содержимым файла списока спамеров для добавления в базу
     */
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app1.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }

    /**
     * Метод создает экземпляры пользователей на основании поля dump
     *
     * @return список пользователей типа List<User>
     * @throws IOException ошибка потока ввода-вывода
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().map(s -> s.split(";")).forEach(s -> users.add(new User(s[0], s[1])));
        }
        return users;
    }

    /**
     * Метод добавляет пользователей в базу
     *
     * @param users список пользователей типа List<Uses> для добавления в базу
     * @throws Exception ошибка
     */
    public void save(List<User> users) throws Exception {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * Пользователь для добавления в базу
     */
    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}