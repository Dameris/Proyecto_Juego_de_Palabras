package com.example.juegopalabras.service;

import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.repos.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioImplPartida implements ServicioPartida{
    private final RepositorioPartida repositorioPartida;

    @Override
    public List<Partida> findAll() {
        return repositorioPartida.findAll();
    }

    @Override
    public Optional<Partida> findById(Long id) {
        return repositorioPartida.findById(id);
    }

    @Override
    public Partida save(Partida partida) {
        return repositorioPartida.save(partida);
    }

    @Override
    public void deleteById(Long id) {
        repositorioPartida.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repositorioPartida.existsById(id);
    }

    @Override
    public List<Partida> findByJugadorId(Long jugadorId) {
        return repositorioPartida.findByJugadorId(jugadorId);
    }

    @Override
    public int getTotalPuntosByJugadorId(Long jugadorId) {
        List<Partida> partidas = findByJugadorId(jugadorId);
        if(partidas == null || partidas.isEmpty()){
            throw new JugadorNotFoundException(jugadorId);
        }
        int totalPuntos = 0;
        for (Partida partida : partidas) {
            totalPuntos += partida.getPuntuacion();
        }
        return totalPuntos;
    }
}