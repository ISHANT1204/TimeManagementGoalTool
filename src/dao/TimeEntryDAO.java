package dao;

import model.TimeEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TimeEntryDAO {

    public boolean addTimeEntry(TimeEntry entry) {

        String sql = "INSERT INTO time_entries " +
                "(goal_id, start_time, end_time, duration) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entry.getGoalId());
            statement.setString(2, entry.getStartTime());
            statement.setString(3, entry.getEndTime());
            statement.setInt(4, entry.getDuration());

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TimeEntry> getTimeEntriesByGoal(int goalId) {

        List<TimeEntry> entries = new ArrayList<>();

        String sql = "SELECT * FROM time_entries WHERE goal_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, goalId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                TimeEntry entry = new TimeEntry(
                        resultSet.getInt("entry_id"),
                        resultSet.getInt("goal_id"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getInt("duration")
                );

                entries.add(entry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }
}