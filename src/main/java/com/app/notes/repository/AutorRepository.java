package com.app.notes.repository;

import com.app.notes.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Author , Long> {

}
