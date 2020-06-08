package GenLibUsecase.GenUsecase.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import GenLibUsecase.GenUsecase.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import GenLibUsecase.GenUsecase.model.Library;
import GenLibUsecase.GenUsecase.repository.LibraryRepository;

@RestController
@RequestMapping("/api/v1")

//Enabling Cross Origin access for angular app
@CrossOrigin(origins = "http://localhost:4200")

public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;


    @GetMapping("/librarys")
    public List < Library > getLibrarys() {
        return libraryRepository.findAll();
    }

    @GetMapping("/librarys/{id}")
    public ResponseEntity < Library > getLibraryById(
            @PathVariable(value = "id") Long libraryId) throws ResourceNotFoundException {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + libraryId));
        return ResponseEntity.ok().body(library);
    }

    @PostMapping("/librarys")
    public Library createLibrary(@Valid @RequestBody Library library) {
        return libraryRepository.save(library);
    }

    @PutMapping("/librarys/{id}")
    public ResponseEntity < Library > updateLibrary(
            @PathVariable(value = "id") Long libraryId,
            @Valid @RequestBody Library libraryDetails) throws ResourceNotFoundException {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found :: " + libraryId));
        library.setLibraryName(libraryDetails.getLibraryName());
        final Library updatedUser = libraryRepository.save(library);
        return ResponseEntity.ok(updatedUser);
    }

}