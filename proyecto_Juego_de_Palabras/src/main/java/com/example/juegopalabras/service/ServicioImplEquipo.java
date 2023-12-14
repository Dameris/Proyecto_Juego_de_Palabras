package com.example.juegopalabras.service;
import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.repos.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioImplEquipo {
    @Autowired
    private EquipoRepository RepositorioEquipo;

    public List<Equipo> findAll() {
        return RepositorioEquipo.findAll();
    }

    public Optional<Equipo> findById(Long id) {
        return RepositorioEquipo.findById(id);
    }

    public Equipo save(Equipo equipo) {
        return RepositorioEquipo.save(equipo);
    }

    public void deleteById(Long id) {
        RepositorioEquipo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return RepositorioEquipo.existsById(id);
    }
}