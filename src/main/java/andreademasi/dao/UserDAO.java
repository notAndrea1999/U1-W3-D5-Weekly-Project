package andreademasi.dao;

import andreademasi.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("L'utente e' stato salvato con successo");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public void findByIdAndDelete(long id) {
        User foundUser = em.find(User.class, id);

        if (foundUser != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundUser);
            transaction.commit();
            System.out.println("L'utente con id: " + id + " e' stato eliminato con successo");
        } else {
            System.out.println("L'utente con id: " + id + " non e' stato trovato");
        }

    }

    public void refresh(User user) {
        em.refresh(user);
        System.out.println("La pubblicazione e' stata refreshata");
    }
}
