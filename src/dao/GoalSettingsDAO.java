package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoalSettingsDAO {

    public List<String> getGoalTypes() {

        List<String> goalTypes = new ArrayList<>();

        String sql = "SELECT goal_type FROM goal_settings";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                goalTypes.add(resultSet.getString("goal_type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goalTypes;
    }

    public boolean addGoalSetting(String goalType, String trackingMetric) {

        String sql = "INSERT INTO goal_settings " +
                "(goal_type, tracking_metric) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, goalType);
            statement.setString(2, trackingMetric);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}