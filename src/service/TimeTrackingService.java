package service;

import dao.TimeEntryDAO;
import model.TimeEntry;

import java.util.List;

public class TimeTrackingService {

    private final TimeEntryDAO timeEntryDAO;

    public TimeTrackingService() {
        timeEntryDAO = new TimeEntryDAO();
    }

    public boolean addTimeEntry(TimeEntry entry) {

        if (entry.getGoalId() <= 0) {
            return false;
        }

        if (entry.getDuration() <= 0) {
            return false;
        }

        return timeEntryDAO.addTimeEntry(entry);
    }

    public List<TimeEntry> getTimeEntries(int goalId) {
        return timeEntryDAO.getTimeEntriesByGoal(goalId);
    }
}