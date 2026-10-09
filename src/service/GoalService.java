package service;

import dao.GoalDAO;
import model.Goal;

import java.util.List;

public class GoalService {

    private final GoalDAO goalDAO;

    public GoalService() {
        goalDAO = new GoalDAO();
    }

    public boolean createGoal(Goal goal) {

        if (goal.getTitle() == null || goal.getTitle().trim().isEmpty()) {
            return false;
        }

        if (goal.getTarget() <= 0) {
            return false;
        }

        return goalDAO.addGoal(goal);
    }

    public List<Goal> getUserGoals(int userId) {
        return goalDAO.getGoalsByUser(userId);
    }
}