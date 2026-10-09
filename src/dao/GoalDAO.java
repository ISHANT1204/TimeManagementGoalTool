package dao;

import model.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {

    public boolean addGoal(Goal goal) {

        String sql = "INSERT INTO goals " +
                "(user_id, title, description, target, deadline, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, goal.getUserId());
            statement.setString(2, goal.getTitle());
            statement.setString(3, goal.getDescription());
            statement.setDouble(4, goal.getTarget());
            statement.setString(5, goal.getDeadline());
            statement.setString(6, goal.getStatus());

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Goal> getGoalsByUser(int userId) {

        List<Goal> goals = new ArrayList<>();

        String sql = "SELECT * FROM goals WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Goal goal = new Goal(
                        resultSet.getInt("goal_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("target"),
                        resultSet.getString("deadline"),
                        resultSet.getString("status")
                );

                goals.add(goal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goals;
    }

    public boolean deleteGoal(int goalId) {

        String sql = "DELETE FROM goals WHERE goal_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, goalId);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}