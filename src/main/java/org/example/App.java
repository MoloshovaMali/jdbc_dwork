package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
//        Db.connection().close();

//        createTable();
//        addCity("Bishkek",976800);
//        addCity("Osh",256500);
//        addCity("Naryn",240000);
//        addCity("Jalal-Abad",87000);
//        addCity("Moscow",11000000);
//        for ( City city : getList()) {
//            System.out.println(city);
//        }
//        addCityById(4);
//        createTable2();
//        addCountry("Kyrgyzstan",7000000);
//        addCountry("Russia",134000000);
//        addCountry("Japan",125000000);
//        for(Country country:getList2()){
//            System.out.println(country);
//        }
//createTable3();
//    addLeaders("Malika",17);
//    addLeaders("Nurisa",20);
//    addLeaders("Bakyt",22);
//    addLeaders("Uson",23);
//    addLeaders("Asan",23);
    }


    public static void addCityById(int id) {
        String SQL = "SELECT * FROM cities WHERE id=?";
        try (Connection connection = Db.connection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt("id") + " "
                    + resultSet.getString("name") + " "
                    + resultSet.getInt("population"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

