package model;

public class TimeEntry {

    private int entryId;
    private int goalId;
    private String startTime;
    private String endTime;
    private int duration;

    public TimeEntry() {
    }

    public TimeEntry(int entryId, int goalId, String startTime,
                     String endTime, int duration) {
        this.entryId = entryId;
        this.goalId = goalId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}