package andreademasi;

import andreademasi.dao.LoanDAO;
import andreademasi.dao.PubblicazioneDAO;
import andreademasi.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliografico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PubblicazioneDAO pd = new PubblicazioneDAO(em);
        UserDAO ud = new UserDAO(em);
        LoanDAO ld = new LoanDAO(em);
        try {

        } catch (Exception ex) {
            System.out.println(ex);
        }


        System.out.println("Hello World!");
        em.close();
        emf.close();
    }
}
