package com.example.juegopalabras.repos;

import com.example.juegopalabras.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEquipo extends JpaRepository<Equipo, Long> {}