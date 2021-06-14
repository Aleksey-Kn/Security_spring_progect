package ru.example.Authority;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import ru.example.Authority.dataManagers.*;

import java.util.List;

@Component
public class DataBaseAssistant {
    private final JdbcTemplate jdbcTemplate;

    DataBaseAssistant(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:/home/aleksey/home/public/h2");
        dataSource.setPassword("");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean add(Desktop data){
        try {
            jdbcTemplate.update("INSERT INTO descktop(series_number, maker, price, counter, form_factor)" +
                            " VALUES(?, ?, ?, ?, ?)", data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getFormFactor());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean add(HardDisk data){
        try {
            jdbcTemplate.update("INSERT INTO hard(series_number, maker, price, counter, volume)" +
                            " VALUES(?, ?, ?, ?, ?)", data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getVolume());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean add(Monitor data){
        try {
            jdbcTemplate.update("INSERT INTO monitor(series_number, maker, price, counter, diagonal)" +
                            " VALUES(?, ?, ?, ?, ?)", data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getDiagonal());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean add(Notebook data){
        try {
            jdbcTemplate.update("INSERT INTO notebook(series_number, maker, price, counter, size)" +
                            " VALUES(?, ?, ?, ?, ?)", data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getSize());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public Data searchId(String clazz, int id){
        switch (clazz){
            case "desktop":
                return jdbcTemplate.query(
                    "select * from descktop where id = ?", new Integer[]{id},
                    (rs, rowNum) -> new Desktop(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getString(6))).get(0);
            case "hard":
                return jdbcTemplate.query(
                    "select * from hard where id = ?", new Integer[]{id},
                    (rs, rowNum) -> new HardDisk(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6))).get(0);
            case "monitor":
                return jdbcTemplate.query(
                    "select * from monitor where id = ?", new Integer[]{id},
                    (rs, rowNum) -> new Monitor(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6))).get(0);
            case "notebook":
                return jdbcTemplate.query(
                    "select * from notebook where id = ?", new Integer[]{id},
                    (rs, rowNum) -> new Notebook(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6))).get(0);
        }
        return null;
    }

    public boolean set(int id, Desktop data){
        try {
            jdbcTemplate.update("UPDATE descktop SET series_number = ?, maker = ?, price = ?, counter = ?, form_factor = ?" +
                            "WHERE id = " + id,
                    data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getFormFactor());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean set(int id, HardDisk data){
        try {
            jdbcTemplate.update("UPDATE hard SET series_number = ?, maker = ?, price = ?, counter = ?, volume = ?" +
                            "WHERE id = " + id,
                    data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getVolume());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean set(int id, Monitor data){
        try {
            jdbcTemplate.update("UPDATE monitor SET series_number = ?, maker = ?, price = ?, counter = ?, diagonal = ?" +
                            "WHERE id = " + id,
                    data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getDiagonal());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public boolean set(int id, Notebook data){
        try {
            jdbcTemplate.update("UPDATE notebook SET series_number = ?, maker = ?, price = ?, counter = ?, size = ?" +
                    "WHERE id = " + id,
                    data.getSeriesNumber(), data.getMaker(), data.getPrice(),
                    data.getCounter(), data.getSize());
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public List<Data> getAll(String clazz){
        switch (clazz){
            case "desktop":
                return jdbcTemplate.query(
                    "select * from descktop",
                    (rs, rowNum) -> new Desktop(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getString(6)));
            case "hard":
                return jdbcTemplate.query(
                    "select * from hard",
                    (rs, rowNum) -> new HardDisk(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6)));
            case "monitor":
                return jdbcTemplate.query(
                    "select * from monitor",
                    (rs, rowNum) -> new Monitor(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6)));
            case "notebook":
                return jdbcTemplate.query(
                    "select * from notebook",
                    (rs, rowNum) -> new Notebook(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6)));
        }
        return null;
    }

    public boolean addUser(String name, String pass){
        try {
            if(jdbcTemplate.query("SELECT password, username  FROM user where username = ?", (rs, rowNum) ->
                    new User(rs.getString(1), rs.getString(2), List.of(new Role("ROLE_USER"))), name).isEmpty()) {
                jdbcTemplate.update("INSERT INTO user(username, password)" +
                        " VALUES(?, ?)", name, pass);
                return true;
            }
            return false;
        } catch (DataAccessException ignore){
            return false;
        }
    }

    public User getUser(String username){
        List<User> users = jdbcTemplate.query("SELECT password, username FROM user WHERE username = ?", (rs, rowNum) ->
                new User(rs.getString(1), rs.getString(2), List.of(new Role("ROLE_USER"))), username);
        if(users.isEmpty()){
            return null;
        } else return users.get(0);
    }
}
