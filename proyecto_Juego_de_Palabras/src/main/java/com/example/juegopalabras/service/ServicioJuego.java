package com.example.juegopalabras.service;

import com.example.juegopalabras.modelo.Juego;

import java.util.List;
import java.util.Optional;

public interface ServicioJuego {
    List<Juego> findAll();
    Optional<Juego> findById(Long id);
    Juego save(Juego juego);
    void deleteById(Long id);
    boolean existsById(Long id);
}