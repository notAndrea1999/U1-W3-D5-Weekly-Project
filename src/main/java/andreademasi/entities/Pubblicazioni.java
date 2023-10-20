package andreademasi.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "publicatiion_type")
public abstract class Pubblicazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int isbn;
    protected String title;
    @Column(name = "issue_year")
    protected int issueYear;
    @Column(name = "pages_num")
    protected int pagesNum;
    @OneToMany(mappedBy = "pubblicazioni", cascade = CascadeType.REMOVE)
    private Set<Loan> loanSet;

    public Pubblicazioni() {
    }

    public Pubblicazioni(String title, int issueYear, int pagesNum) {
        this.title = title;
        this.issueYear = issueYear;
        this.pagesNum = pagesNum;
    }

    public int getIsbn() {
        return isbn;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    @Override
    public String toString() {
        return "Pubblicazioni{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", issueYear=" + issueYear +
                ", pagesNum=" + pagesNum +
                '}';
    }
}
