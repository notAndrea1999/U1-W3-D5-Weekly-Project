package andreademasi.dao;

import andreademasi.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("Prestito salvato con successo");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Loan findById(int id) {
        return em.find(Loan.class, id);
    }

    public void findByIdAndDelete(int id) {
        Loan foundLoan = em.find(Loan.class, id);

        if (foundLoan != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundLoan);
            transaction.commit();
            System.out.println("Il prestito con id: " + id + " e' stato eliminato con successo");
        } else {
            System.out.println("Il prestito con id: " + id + " non e' stata trovato");
        }

    }

    public void refresh(Loan loan) {
        em.refresh(loan);
        System.out.println("La pubblicazione e' stata refreshata");
    }

    public List<Loan> getNullAndExpiredLoans() {
        TypedQuery<Loan> getLoan = em.createQuery("SELECT l FROM Loan l WHERE l.expectedReturnDate < l.returnDate OR l.returnDate IS null ", Loan.class);
        return getLoan.getResultList();
    }

    public List<Loan> getActiveLoanByUserId(int userId) {
        TypedQuery<Loan> getLoan = em.createQuery("SELECT p FROM Loan p WHERE p.user.cardNumber = :userId ", Loan.class);
        getLoan.setParameter("userId", userId);
        return getLoan.getResultList();
    }
}
