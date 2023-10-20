package andreademasi.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pubblicazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int isbn;
    protected String title;
    protected int issueYear;
    protected int pagesNum;

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
