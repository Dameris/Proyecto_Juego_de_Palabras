package com.example.juegopalabras.repos;

import com.example.juegopalabras.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioJuego extends JpaRepository<Juego, Long> {}