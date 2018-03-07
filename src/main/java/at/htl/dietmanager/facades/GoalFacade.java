package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.Goal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class GoalFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addGoal(Goal goal) {
        entityManager.persist(goal);
    }

    public Goal getGoalByID(int id) {
        return entityManager.find(Goal.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public List<Goal> getAllGoals() {
        return (List<Goal>) entityManager.createQuery("select t from Goal t").getResultList();
    }
}