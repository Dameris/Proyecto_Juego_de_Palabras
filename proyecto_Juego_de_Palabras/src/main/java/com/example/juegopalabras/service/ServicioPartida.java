package com.example.juegopalabras.service;

import com.example.juegopalabras.modelo.Partida;

import java.util.List;
import java.util.Optional;

public interface ServicioPartida {
    List<Partida> findAll();
    Optional<Partida> findById(Long id);
    Partida save(Partida jugador);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Partida> findByJugadorId(Long jugadorId);
    int getTotalPuntosByJugadorId(Long jugadorId);
}