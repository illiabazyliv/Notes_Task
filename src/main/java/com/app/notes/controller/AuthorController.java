package com.app.notes.controller;


import com.app.notes.dto.AuthorDto;
import com.app.notes.model.Author;
import com.app.notes.repository.AutorRepository;
import com.app.notes.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.getName;

@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

    private final AutorRepository autorRepository;
    private final AuthorService authorService;

    public AuthorController(AutorRepository autorRepository, AuthorService authorService) {
        this.autorRepository = autorRepository;
        this.authorService = authorService;
    }
    @Operation(summary = "Get all authors")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Authors found successfully"),
            @ApiResponse(responseCode = "500" , description = "Internal server error"),

    })
    @GetMapping
    public ResponseEntity <List<AuthorDto>> getAllAuthors(){
        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);

    }
    @Operation(summary = "Create new autor")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),

    })
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        AuthorDto created = authorService.createAuthor(authorDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @Operation(summary = "get autor by Id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Autor found successfully"),
            @ApiResponse(responseCode = "500" , description = "Internal server error"),
    })
@GetMapping("/{id}")
public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        AuthorDto dto = authorService.getAuthorById(id);
        return ResponseEntity.ok(dto);
    }

}
