package at.htl.dietmanager.facades;

import at.htl.dietmanager.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserFacade {
    @PersistenceContext
    EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User getUserByID(int id) {
        return entityManager.find(User.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public boolean isEmailAvailable(String email) {
        int i = entityManager.createQuery("select u from User u where u.email = '" + email + "'").getResultList().size();
        return i == 0;
    }

    public boolean isUsernameAvailable(String username) {
        int i = entityManager.createQuery("select u from User u where u.userName = '" + username + "'").getResultList().size();
        return i == 0;
    }

    public User getUserByUsername(String username) {
        try {
            return (User) entityManager.createQuery("select u from User u where u.userName = '" + username + "'").getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public void update(User user) {
        entityManager.merge(user);
    }
}
