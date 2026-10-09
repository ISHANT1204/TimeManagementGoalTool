package model;

import interfaces.ProgressTrackable;

public class Goal implements ProgressTrackable {

    private int goalId;
    private int userId;
    private String title;
    private String description;
    private double target;
    private String deadline;
    private String status;

    public Goal() {
    }

    public Goal(int goalId, int userId, String title, String description,
                double target, String deadline, String status) {
        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.target = target;
        this.deadline = deadline;
        this.status = status;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public double calculateProgress(double target, double timeSpent) {

        if (target <= 0) {
            return 0;
        }

        return (timeSpent / target) * 100;
    }
}