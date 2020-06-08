package GenLibUsecase.GenUsecase.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "library")
public class Library extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libraryid")
    private long id;

    @Column(name = "library_name")
    private String libraryName;

    @JsonManagedReference
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List < Book > books;

    public Library() {

    }

    public Library(String libraryName) {
        this.libraryName = libraryName;

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public List < Book > getBooks() {
        return books;
    }

    public void setBooks(List < Book > books) {
        this.books = books;
    }
}