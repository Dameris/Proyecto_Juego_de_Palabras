package com.example.juegopalabras.service;

import com.example.juegopalabras.modelo.Juego;
import com.example.juegopalabras.repos.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServicioImplJuego implements ServicioJuego {
    private final RepositorioJuego repositorioJuego;

    @Override
    public List<Juego> findAll() {
        return repositorioJuego.findAll();
    }

    @Override
    public Optional<Juego> findById(Long id) {
        return repositorioJuego.findById(id);
    }

    @Override
    public Juego save(Juego juego) {
        return repositorioJuego.save(juego);
    }

    @Override
    public void deleteById(Long id) {
        repositorioJuego.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repositorioJuego.existsById(id);
    }
}