package com.example.sitesdb.data;

import java.sql.*;
import java.util.*;

public class DataBaseRepository implements Repository {
    private DataBaseConnector dataBaseConnector;

    public DataBaseRepository(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
        try (Connection conn = dataBaseConnector.getConnection()) {
            String tableCreateStr =
                    "CREATE TABLE IF NOT EXISTS Websites (\n" +
                            "    id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                            "    name VARCHAR(255),\n" +
                            "    url VARCHAR(255),\n" +
                            "    category VARCHAR(255),\n" +
                            "    description TEXT,\n" +
                            "    pageCount INT,\n" +
                            "    dailyVisitors INT,\n" +
                            "    yearFounded INT,\n" +
                            "    PRIMARY KEY (id)\n" +
                            ");";
            Statement createTable = conn.createStatement();
            createTable.execute(tableCreateStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addWebsite(Website website) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Websites " +
                            "(name, url, category, description, pageCount, dailyVisitors, yearFounded) " +
                            "VALUES (?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, website.getName());
            preparedStatement.setString(2, website.getUrl());
            preparedStatement.setString(3, website.getCategory());
            preparedStatement.setString(4, website.getDescription());
            preparedStatement.setInt(5, website.getPages());
            preparedStatement.setInt(6, website.getVisitors());
            preparedStatement.setInt(7, website.getYearFounded());
            updCount = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    website.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ID.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public List<Website> getAll() {
        List<Website> websites = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Websites;");
            while (rs.next()) {
                websites.add(new Website(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("url"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("pageCount"),
                        rs.getInt("dailyVisitors"),
                        rs.getInt("yearFounded")
                ));
            }
            rs.close();
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return websites;
    }

    @Override
    public boolean updateWebsite(int id, Website website) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Websites " +
                            "SET name = ?, url = ?, category = ?, description = ?, " +
                            "pageCount = ?, dailyVisitors = ?, yearFounded = ? " +
                            "WHERE id = ?;");
            preparedStatement.setString(1, website.getName());
            preparedStatement.setString(2, website.getUrl());
            preparedStatement.setString(3, website.getCategory());
            preparedStatement.setString(4, website.getDescription());
            preparedStatement.setInt(5, website.getPages());
            preparedStatement.setInt(6, website.getVisitors());
            preparedStatement.setInt(7, website.getYearFounded());
            preparedStatement.setInt(8, id);
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public boolean deleteWebsite(int id) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("DELETE FROM Websites WHERE id = ?;");
            preparedStatement.setInt(1, id);
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public Website getWebsiteById(int id) {
        Website website = null;
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Websites WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                website = new Website(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("url"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("pageCount"),
                        rs.getInt("dailyVisitors"),
                        rs.getInt("yearFounded")
                );
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return website;
    }

    @Override
    public List<Website> getWebsitesByCategory(String category) {
        List<Website> websites = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Websites WHERE category = ?;");
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                websites.add(new Website(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("url"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("pageCount"),
                        rs.getInt("dailyVisitors"),
                        rs.getInt("yearFounded")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return websites;
    }

    @Override
    public List<Website> searchWebsitesByDescription(String searchText) {
        List<Website> websites = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Websites WHERE description LIKE ?;");
            statement.setString(1, "%" + searchText + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                websites.add(new Website(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("url"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("pageCount"),
                        rs.getInt("dailyVisitors"),
                        rs.getInt("yearFounded")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return websites;
    }
}
