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
import java.util.Scanner;
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
            /*
            Ricerca e Rimozione di un elemento dal catalogo dato codice isbn
            Pubblicazioni foundPublication = pd.findById(1);
            System.out.println("Hai trovato la pubblicazione: " + foundPublication);
            pd.findByIdAndDelete(1);
            System.out.println(foundPublication);
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
                            //foundLoanByUserId.forEach(elem -> System.out.println(elem));
                        } else {
                            System.out.println("Nessun prestito associato all'utente");
                        }
            */

            Scanner input = new Scanner(System.in);
            loop:
            while (true) {
                System.out.println("Che vuoi fare?");
                System.out.println("1:RICERCA TRAMITE ISBN, 2: RICERCA PER ANNO DI PUBBLICAZIONE, 3: RICERCA PER AUTORE, " +
                        "4: RICERCA PER TITOLO, 5: RICERCA PUBBLICAZIONI IN PRESTITO PER UTENTE, 6: RICERCA TUTTI I PRESTITI SCADUTI O NON ANCORA RESITUTITI");
                int number = Integer.parseInt(input.nextLine());
                if (number == 0) {
                    break loop;
                }
                switch (number) {
                    case 1: {
                        System.out.println("Digita il codice della pubblicazione da ricercare: ");
                        int isbn = Integer.parseInt(input.nextLine());
                        Pubblicazioni foundPub = pd.findById(isbn);
                        System.out.println(foundPub);
                        break;
                    }
                    case 2: {
                        System.out.println("Digita l'anno della pubblicazione da ricercare:");
                        int year = Integer.parseInt(input.nextLine());
                        List<Pubblicazioni> pubblicazioniList = pd.getPubblicazioniByIssueYear(year);
                        if (!pubblicazioniList.isEmpty()) {
                            pubblicazioniList.forEach(System.out::println);
                        }
                        break;

                    }
                    case 3: {
                        System.out.println("Digita l'autore della pubblicazione da ricercare:");
                        String autore = input.nextLine();
                        List<Pubblicazioni> pubblicazioniListByAuthor = pd.getPubblicazioniByAuthor(autore);
                        if (!pubblicazioniListByAuthor.isEmpty()) {
                            pubblicazioniListByAuthor.forEach(System.out::println);
                        }
                        break;
                    }

                    case 4: {
                        System.out.println("Digita il titolo o un frammento di esso della pubblicazione da ricercare: ");
                        String titolo = input.nextLine();
                        List<Pubblicazioni> pubblicazioniListByTitle = pd.getPubblicazioniByTitle(titolo);
                        if (!pubblicazioniListByTitle.isEmpty()) {
                            pubblicazioniListByTitle.forEach(System.out::println);
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Digita il tuo codice utente per sapere lo status dei tuoi noleggi: ");
                        int userId = Integer.parseInt(input.nextLine());
                        List<Loan> loanListByUserId = ld.getActiveLoanByUserId(userId);
                        if (!loanListByUserId.isEmpty()) {
                            loanListByUserId.forEach(System.out::println);
                        }
                        break;

                    }
                    case 6: {
                        System.out.println("Ricerca tutti i prestiti scaduti o non ancora restituiti");
                        List<Loan> nullAndExpiredLoans = ld.getNullAndExpiredLoans();
                        if (!nullAndExpiredLoans.isEmpty()) {
                            nullAndExpiredLoans.forEach(System.out::println);
                        }
                        break;
                    }

                }


            }


        } catch (Exception ex) {
            System.out.println(ex);
        }


        System.out.println("Hello World!");
        em.close();
        emf.close();
    }
}
