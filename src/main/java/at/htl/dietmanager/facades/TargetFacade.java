package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.Target;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TargetFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addTarget(Target target){
        entityManager.persist(target);
    }

    public Target getTargetByID(int id) {
        return entityManager.find(Target.class, id, LockModeType.PESSIMISTIC_READ);
    }
    public List<Target> getAllTargets(){
        return (List<Target>) entityManager.createQuery("select t from Target t").getResultList();
    }
}