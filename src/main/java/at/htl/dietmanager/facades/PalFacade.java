package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.Pal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PalFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addGoal(Pal goal) {
        entityManager.persist(goal);
    }

    public Pal getPalByID(int id) {
        return entityManager.find(Pal.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public List<Pal> getAllPals() {
        return (List<Pal>) entityManager.createQuery("select t from Pal t").getResultList();
    }
}