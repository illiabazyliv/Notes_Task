package com.app.notes.service;


import com.app.notes.dto.NoteDto;
import com.app.notes.exceptions.NotFoundException;
import com.app.notes.model.Author;
import com.app.notes.model.Note;
import com.app.notes.repository.AutorRepository;
import com.app.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
//    POST /notes — utworzenie nowej notatki
//    GET /notes — pobranie listy wszystkich notatek
//    GET /notes/{id} — pobranie notatki po ID
//    DELETE /notes/{id} — usunięcie notatki po ID


    private NoteRepository noteRepository;
    private AutorRepository autorRepository;

    public NoteService(NoteRepository noteRepository, AutorRepository autorRepository) {
        this.noteRepository = noteRepository;
        this.autorRepository = autorRepository;
    }

    public NoteDto createNote(NoteDto noteDto) {
        Author author = autorRepository.findById(noteDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author with id " + noteDto.getAuthorId() + " not found"));

        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setAuthor(author);
        note.setCreatedAt(LocalDateTime.now());

        Note saved = noteRepository.save(note);
        return new NoteDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getCreatedAt(),
                saved.getAuthor().getId(),
                saved.getAuthor().getName()
        );
    }

    public List<NoteDto> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(note -> new NoteDto(
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getCreatedAt(),
                        note.getAuthor().getId(),
                        note.getAuthor().getName()
                )).collect(Collectors.toList());
    }

    public NoteDto getNoteById(Long id) {
        return noteRepository.findById(id)
                .map(note -> new NoteDto(
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getCreatedAt(),
                        note.getAuthor().getId(),
                        note.getAuthor().getName()
                ))
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
    }
    public void deleteNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        noteRepository.delete(note);
    }
}

