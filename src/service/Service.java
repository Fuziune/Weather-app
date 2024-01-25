package service;

import main.domain.Weather;
import repository.Repository;

import java.lang.annotation.Documented;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Weather> getAll()  {
        List<Weather> repoo = new ArrayList<>();
        List<Weather> sortedRepooByName = null;
        try (Connection connection = repo.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Weather");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Weather weather = new Weather(
                        resultSet.getInt("start"),
                        resultSet.getInt("end"),
                        resultSet.getInt("temeperature"),
                        resultSet.getInt("precip"),
                        resultSet.getString("description")
                );

                repoo.add(weather);
                sortedRepooByName = repoo.stream()
                        .sorted(Comparator.comparing(Weather::getStart)).collect(Collectors.toList());

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedRepooByName;
    }
    public void update(Weather weather) {
        try (Connection connection = repo.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Weather SET   temeperature = ?, precip = ? ,description=? WHERE start = ? and end = ?"
             )) {
            statement.setInt(1, weather.getTemeperature());
            statement.setInt(2, weather.getPrecip());
            statement.setString(3, weather.getDescription());
            statement.setInt(4, weather.getStart());
            statement.setInt(5, weather.getEnd());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

}}
