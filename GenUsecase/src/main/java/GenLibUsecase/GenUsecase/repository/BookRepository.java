package GenLibUsecase.GenUsecase.repository;

import GenLibUsecase.GenUsecase.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByLibraryId(Long libraryId);
    Optional<Book> findByIdAndLibraryId(Long id, Long libraryId);

}