package com.app.notes.service;

import com.app.notes.dto.AuthorDto;
import com.app.notes.exceptions.NotFoundException;
import com.app.notes.model.Author;
import com.app.notes.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AutorRepository autorRepository;

    public AuthorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        Author saved = autorRepository.save(author);
        return new AuthorDto(saved.getId(), saved.getName());
    }
    public List<AuthorDto> getAllAuthors() {
        return autorRepository.findAll()
                .stream()
                .map(author -> new AuthorDto(
                        author.getId(),
                        author.getName()
                )).collect(Collectors.toList());
    }
    public AuthorDto getAuthorById(Long id) {
        return autorRepository.findById(id)
                .map(author -> new AuthorDto(
                        author.getId(),
                        author.getName()))
                .orElseThrow(() ->
                        new NotFoundException("Author with not found"));
    }
}
