package andreademasi.dao;

import andreademasi.entities.Pubblicazioni;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public void findByIdAndDelete(int id) {
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

    public List<Pubblicazioni> getPubblicazioniByIssueYear(int issueYear) {
        TypedQuery<Pubblicazioni> getPubblicazioni = em.createQuery("SELECT p FROM Pubblicazioni p WHERE p.issueYear = :issueYear", Pubblicazioni.class);
        getPubblicazioni.setParameter("issueYear", issueYear);
        return getPubblicazioni.getResultList();
    }

    public List<Pubblicazioni> getPubblicazioniByAuthor(String author) {
        TypedQuery<Pubblicazioni> getPubblicazioni = em.createQuery("SELECT p FROM Pubblicazioni p WHERE LOWER(p.author) = LOWER(:author)", Pubblicazioni.class);
        getPubblicazioni.setParameter("author", author);
        return getPubblicazioni.getResultList();
    }

    public List<Pubblicazioni> getPubblicazioniByTitle(String title) {
        TypedQuery<Pubblicazioni> getPubblicazioni = em.createQuery("SELECT p FROM Pubblicazioni p WHERE LOWER(p.title) LIKE LOWER(CONCAT(:title, '%'))", Pubblicazioni.class);
        getPubblicazioni.setParameter("title", title);
        return getPubblicazioni.getResultList();
    }


}
