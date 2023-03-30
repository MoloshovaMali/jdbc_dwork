package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Country {
    private int id;
    private String name;
    private int population;

    public Country() {
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
        return "Country{" +
                "id=" + id +
                ", name='" + name +
                ", population=" + population ;

    }


    public static void createTable2() {
        String SQL = "CREATE TABLE IF NOT EXISTS countries(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "population INTEGER);";
        try (Connection connection = Db.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCountry(String name, int population) {
        String SQL = "INSERT INTO countries (name,population)" +
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

    public static List<Country> getList2() {
        String SQL = "SELECT * FROM countries";
        List<Country> countries = new ArrayList<>();
        try (Connection connection = Db.connection()) {
            Statement statement = connection.createStatement();
            {
                ResultSet resultSet = statement.executeQuery(SQL);
                while (resultSet.next()) {
                    Country country = new Country();
                    country.setId(resultSet.getInt("id"));
                    country.setName(resultSet.getString("name"));
                    country.setPopulation(resultSet.getInt("population"));
                    countries.add(country);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

}
