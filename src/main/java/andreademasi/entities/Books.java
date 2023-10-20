package andreademasi.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books extends Pubblicazioni {
    private String author;
    private String genre;

    public Books() {
    }

    public Books(String title, int issueYear, int pagesNum, String author, String genre) {
        super(title, issueYear, pagesNum);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthore() {
        return author;
    }

    public void setAuthore(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Books{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", issueYear=" + issueYear +
                ", pagesNum=" + pagesNum +
                '}';
    }
}
