package com.example.juegopalabras.service;
import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.repos.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {
    private final RepositorioJugador repositorioJugador;

    @Override
    public List<Jugador> findAll() {
        return repositorioJugador.findAll();
    }

    @Override
    public Optional<Jugador> findById(Long id) {
        return repositorioJugador.findById(id);
    }

    @Override
    public Jugador save(Jugador jugador) {
        return repositorioJugador.save(jugador);
    }

    @Override
    public void deleteById(Long id) {
        repositorioJugador.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repositorioJugador.existsById(id);
    }

    @Override
    public List<Jugador> findByEquipo(Equipo equipo) {
        return repositorioJugador.findByEquipoId(equipo.getId());
    }

    @Override
    public List<Jugador> obtenerJugadoresPorEquipo(Long id_equipo) {
        return jugadorRepository.findByEquipoId(id_equipo);
    }

    @Override
    public List<Jugador> findByEquipoId(Long id) {
        return repositorioJugador.findByEquipoId(id);
    }
}