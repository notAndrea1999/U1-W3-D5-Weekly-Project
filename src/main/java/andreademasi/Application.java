package andreademasi;

import andreademasi.dao.LoanDAO;
import andreademasi.dao.PubblicazioneDAO;
import andreademasi.dao.UserDAO;
import andreademasi.entities.Books;
import andreademasi.entities.Magazine;
import andreademasi.entities.Periodicity;
import andreademasi.entities.User;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
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
            //Rimozione di un elemento dal catalogo dato codice isbn
            pd.findByIdAndDelete();

        } catch (Exception ex) {
            System.out.println(ex);
        }


        System.out.println("Hello World!");
        em.close();
        emf.close();
    }
}
