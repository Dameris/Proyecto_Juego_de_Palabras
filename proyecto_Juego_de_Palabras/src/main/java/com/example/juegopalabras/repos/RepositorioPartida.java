package com.example.juegopalabras.repos;
import com.example.juegopalabras.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioPartida extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorId(Long id);
}