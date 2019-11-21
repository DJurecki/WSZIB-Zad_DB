package com.jurecki.db;

import com.jurecki.model.Game;

import java.sql.*;
import java.util.Scanner;

public class GameRepository {

    public static Connection connection = null;

    public static void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            GameRepository.connection = DriverManager.getConnection("jdbc:mysql://localhost/games?user=root&password=");
        } catch (SQLException | ClassNotFoundException e){
            System.err.println("Not connected with database. Ending program...");
            System.exit(0);
        }
    }

    public static void addGame(Game game){
        String sql = "INSERT INTO game (id, title, publisher) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = GameRepository.connection.prepareStatement(sql);
            preparedStatement.setInt(1, game.getId());
            preparedStatement.setString(2, game.getTitle());
            preparedStatement.setString(3, game.getPublisher());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Game getGame(String title){
        String sqlSelect = "Select * FROM game WHERE title = ?";
        try {
            PreparedStatement preparedStatement = GameRepository.connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Game userFromDB = new Game();
                userFromDB.setId(resultSet.getInt("id"));
                userFromDB.setTitle(resultSet.getString("title"));
                userFromDB.setPublisher(resultSet.getString("publisher"));

                return userFromDB;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteGame(String title){
        String sqlDelete = "Delete FROM game WHERE title = ?";

        try {
            PreparedStatement preparedStatement = GameRepository.connection.prepareStatement(sqlDelete);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void updateGame(String title){
        String sqlUpdate = "Update game SET title = ?, publisher = ? WHERE title = ?";

        try{
            PreparedStatement preparedStatement = GameRepository.connection.prepareStatement(sqlUpdate);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter correct title: ");
            String updatedTitle = scanner.nextLine();
            System.out.println("Enter correct publisher: ");
            String updatedPublisher = scanner.nextLine();
            preparedStatement.setString(1 , updatedTitle);
            preparedStatement.setString(2 , updatedPublisher);
            preparedStatement.setString(3, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
