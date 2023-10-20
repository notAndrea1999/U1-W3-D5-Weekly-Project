package andreademasi;

import andreademasi.dao.LoanDAO;
import andreademasi.dao.PubblicazioneDAO;
import andreademasi.dao.UserDAO;
import andreademasi.entities.*;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliografico");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PubblicazioneDAO pd = new PubblicazioneDAO(em);
        UserDAO ud = new UserDAO(em);
        LoanDAO ld = new LoanDAO(em);
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();
        LocalDate l1 = LocalDate.of(rndm.nextInt(1970, 2023), rndm.nextInt(01, 12), rndm.nextInt(01, 30));

        try {
            Supplier<Books> booksSupplier = () -> new Books(faker.book().title(), rndm.nextInt(1970, 2023), rndm.nextInt(50, 300), faker.book().author(), faker.book().genre());
            Supplier<Magazine> magazineSupplier = () -> new Magazine(faker.book().title(), rndm.nextInt(1970, 2023), rndm.nextInt(50, 300), Periodicity.randomPeriodicity());
            Supplier<User> userSupplier = () -> new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(rndm.nextInt(1970, 2023), rndm.nextInt(01, 12), rndm.nextInt(01, 30)));

        /*
Creazione books and magazines
            for (int i = 0; i < 30; i++) {
                pd.save(booksSupplier.get());
                pd.save(magazineSupplier.get());
            }
Crezione utenti
            for (int i = 0; i < 30; i++) {
                ud.save(userSupplier.get());
            }
Creazione prestiti
            for (int i = 0; i < 30; i++) {
                Pubblicazioni foundPubblicazione = pd.findById(rndm.nextInt(1, 30));
                User foundUser = ud.findById(rndm.nextInt(31, 60));
                Loan newLoan = new Loan(foundUser, foundPubblicazione, LocalDate.of(rndm.nextInt(1970, 2023), rndm.nextInt(01, 12), rndm.nextInt(01, 30)));
                int number = rndm.nextInt(0, 100);
                if (number % 2 == 0) {
                    newLoan.setReturnDate(LocalDate.of(rndm.nextInt(1970, 2023), rndm.nextInt(01, 12), rndm.nextInt(01, 30)));
                }
                ld.save(newLoan);
            }
*/
            //Ricerca e Rimozione di un elemento dal catalogo dato codice isbn
            //Pubblicazioni foundPublication = pd.findById(1);
            //System.out.println("Hai trovato la pubblicazione: " + foundPublication);
            //pd.findByIdAndDelete(1);
            //System.out.println(foundPublication);

            System.out.println("**************** Ricerca pubblicazione per anno *************************");
            //Ricerca pubblicazione per anno
            List<Pubblicazioni> foundPublicationByYear = pd.getPubblicazioniByIssueYear(1988);
            //foundPublicationByYear.forEach(elem -> System.out.println(elem));
            System.out.println("**************** Ricerca per autore *************************");
            //Ricerca per autore
            List<Pubblicazioni> foundPublicationByAuthor = pd.getPubblicazioniByAuthor("Pericle Sartori");
            //foundPublicationByAuthor.forEach(elem -> System.out.println(elem));
            System.out.println("**************** Ricerca per titolo o parte di esso *************************");
            //Ricerca per titolo o parte di esso
            List<Pubblicazioni> foundPublicationByTitle = pd.getPubblicazioniByTitle("th");
            if (foundPublicationByTitle != null) {
                // foundPublicationByTitle.forEach(elem -> System.out.println(elem));
            } else {
                System.out.println("Nessuna pubblicazione con titolo inserito trovata");
            }

            //Ricerca prestiro scaduto o null
            System.out.println("**************** Ricerca prestiro scaduto o null *************************");
            List<Loan> foundLoan = ld.getNullAndExpiredLoans();
            if (!foundLoan.isEmpty()) {
                //foundLoan.forEach(elem -> System.out.println(elem));
            } else {
                System.out.println("Nessun prestito trovato");
            }
            //Ricerca elementi in noleggio trmite utente
            System.out.println("**************** Ricerca elementi in noleggio trmite utente *************************");
            List<Loan> foundLoanByUserId = ld.getActiveLoanByUserId(35);
            if (!foundLoan.isEmpty()) {
                foundLoanByUserId.forEach(elem -> System.out.println(elem));
            } else {
                System.out.println("Nessun prestito associato all'utente");
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }


        System.out.println("Hello World!");
        em.close();
        emf.close();
    }
}
