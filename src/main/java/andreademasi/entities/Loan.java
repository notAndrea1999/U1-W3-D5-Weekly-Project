package andreademasi.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private int loanId;

    private User user;
    private Pubblicazioni pubblicazioni;
    @Column(name = "start_loan_date")
    private LocalDate startDate;
    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(User user, Pubblicazioni pubblicazioni, LocalDate startDate, LocalDate expectedReturnDate, LocalDate returnDate) {
        this.user = user;
        this.pubblicazioni = pubblicazioni;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pubblicazioni getPubblicazioni() {
        return pubblicazioni;
    }

    public void setPubblicazioni(Pubblicazioni pubblicazioni) {
        this.pubblicazioni = pubblicazioni;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", user=" + user +
                ", pubblicazioni=" + pubblicazioni +
                ", startDate=" + startDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
