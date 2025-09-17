package com.app.notes.controller;

import com.app.notes.dto.NoteDto;
import com.app.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@Validated
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @PostMapping
    public ResponseEntity<NoteDto> addNote(@Validated @RequestBody NoteDto noteDto) {
        NoteDto created = noteService.createNote(noteDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDto> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
