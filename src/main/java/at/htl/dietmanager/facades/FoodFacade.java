package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.Food;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FoodFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addFood(Food food) {
        entityManager.persist(food);
    }

    public Food getFoodByID(int id) {
        return entityManager.find(Food.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public List<Food> getAllFoods(){
        return entityManager.createQuery("select f from Food f").getResultList();
    }
}
