package GenLibUsecase.GenUsecase.controller;

import java.util.List;

import javax.validation.Valid;

import GenLibUsecase.GenUsecase.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import GenLibUsecase.GenUsecase.model.Book;
import GenLibUsecase.GenUsecase.repository.BookRepository;
import GenLibUsecase.GenUsecase.repository.LibraryRepository;

@RestController
@RequestMapping("/api/v1")
//Enabling Cross Origin access for angular app
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;



	@GetMapping("/librarys/{libraryId}/books")
	public List < Book > getBooksByLibrary(@PathVariable(value = "libraryId") Long libraryId) {
		return bookRepository.findByLibraryId(libraryId);
	}

	@PostMapping("/librarys/{libraryId}/books")
	public Book createBook(@PathVariable(value = "libraryId") Long libraryId,
							   @Valid @RequestBody Book book) throws ResourceNotFoundException {
		return libraryRepository.findById(libraryId).map(library -> {
				book.setLibrary(library);
		return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Library not found"));
	}

	@PutMapping("/librarys/{libraryId}/books/{bookId}")
	public Book updateBook(@PathVariable(value = "libraryId") Long libraryId,
							   @PathVariable(value = "bookId") Long bookId, @Valid @RequestBody Book bookRequest)
			throws ResourceNotFoundException {
		if (!libraryRepository.existsById(libraryId)) {
			throw new ResourceNotFoundException("LibraryID not found");
		}

		return bookRepository.findById(bookId).map(book -> {
				book.setTitle(bookRequest.getTitle());
		return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Book id not found"));
	}


}