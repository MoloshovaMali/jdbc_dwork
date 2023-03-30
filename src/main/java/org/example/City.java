package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class City {
    private int id;
    private String name;
    private int population;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name +
                ", population=" + population ;


}
    public static void createTable() {
        String SQL = "CREATE TABLE  cities(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "population INTEGER);" ;
        try (Connection connection = Db.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCity(String name, int population) {
        String SQL = "INSERT INTO cities (name,population)" +
                "VALUES(?,?)";
        try (Connection connection = Db.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setInt(2, population);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<City> getList() {
        String SQL = "SELECT * FROM cities";
        List<City> cities = new ArrayList<>();
        try (Connection connection = Db.connection()) {
            Statement statement = connection.createStatement();
            {
                ResultSet resultSet = statement.executeQuery(SQL);
                while (resultSet.next()) {
                    City city = new City();
                    city.setId(resultSet.getInt("id"));
                    city.setName(resultSet.getString("name"));
                    city.setPopulation(resultSet.getInt("population"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }


}
