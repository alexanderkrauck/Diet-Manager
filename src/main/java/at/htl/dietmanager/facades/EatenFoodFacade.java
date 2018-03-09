package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.EatenFood;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EatenFoodFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addEatenFood(EatenFood eatenFood) {
        entityManager.persist(eatenFood);
    }

    public List<EatenFood> getTodayEatenFood() {
        return entityManager.createQuery("SELECT ef FROM DM_EATEN_FOOD ef WHERE DATE(ef.EF_EATEN_DATE_TIME) = CURRENT_DATE").getResultList();
    }
}
