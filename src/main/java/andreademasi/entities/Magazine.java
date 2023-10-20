package andreademasi.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends Pubblicazioni {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(String title, int issueYear, int pagesNum, Periodicity periodicity) {
        super(title, issueYear, pagesNum);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", issueYear=" + issueYear +
                ", pagesNum=" + pagesNum +
                '}';
    }
}
