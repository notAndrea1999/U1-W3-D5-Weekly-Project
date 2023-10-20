package andreademasi.dao;

import andreademasi.entities.Pubblicazioni;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PubblicazioneDAO {
    private final EntityManager em;

    public PubblicazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Pubblicazioni pubblicazioni) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(pubblicazioni);
            transaction.commit();
            System.out.println("Pubblicazione salvata con successo");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Pubblicazioni findById(int id) {
        return em.find(Pubblicazioni.class, id);
    }

    public void findByIdAndDelete(long id) {
        Pubblicazioni foundPublication = em.find(Pubblicazioni.class, id);

        if (foundPublication != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundPublication);
            transaction.commit();
            System.out.println("La pubblicazione con id: " + id + " e' stata eliminata con successo");
        } else {
            System.out.println("La pubblicazione con id: " + id + " non e' stata trovata");
        }

    }

    public void refresh(Pubblicazioni pubblicazioni) {
        em.refresh(pubblicazioni);
        System.out.println("La pubblicazione e' stata refreshata");
    }
}
