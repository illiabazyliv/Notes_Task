package com.app.notes.controller;

import com.app.notes.dto.NoteDto;
import com.app.notes.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "add new note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Note added successfully"),
            @ApiResponse(responseCode = "500" , description = "failed to add note")
    })
    @PostMapping
    public ResponseEntity<NoteDto> addNote(@Validated @RequestBody NoteDto noteDto) {
        NoteDto created = noteService.createNote(noteDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Get all notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Note found successfully"),
            @ApiResponse(responseCode = "500" , description = "Internal server error")
    })
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }
    @Operation(summary = "Get note by id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Note found successfully"),
            @ApiResponse(responseCode = "204" , description = "No Note found for these user"),
            @ApiResponse(responseCode = "500" , description = "Internal server error")
    })
   @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
    @Operation(summary = "delete note by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Note deleted successfully"),
            @ApiResponse(responseCode = "500" , description = "Failed to delete note")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDto> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
