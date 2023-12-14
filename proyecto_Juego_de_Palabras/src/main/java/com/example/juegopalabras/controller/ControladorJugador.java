package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.ExcepcionJugadorNotFound;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.service.ServicioJugador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final ServicioJugador servicioJugador;

    @GetMapping("/jugador")
    public List<Jugador> findAll() {
        List<Jugador> result =  servicioJugador.findAll();
        if(result.isEmpty()){
            throw new ExcepcionJugadorNotFound();
        }
        return result;
    }

    @GetMapping("/jugador/{id}")
    public Jugador findById(@PathVariable Long id) {
        return servicioJugador.findById(id).orElseThrow(() -> new ExcepcionJugadorNotFound(id));
    }
    @PostMapping("/jugador")
    public Jugador newJugador(@RequestBody Jugador newJugador){
        return servicioJugador.save(newJugador);
    }

    @PutMapping("/jugador/{id}")
    public Jugador updateJugador(@RequestBody Jugador updateJugador, @PathVariable Long id){
        if(servicioJugador.existsById(id)){
            Jugador jugador = servicioJugador.findById(id).get();
            jugador.setUser(updateJugador.getUser());
            jugador.setScore(updateJugador.getScore());
            jugador.setAvatar(updateJugador.getAvatar());
            jugador.setEquipo(updateJugador.getEquipo());
            return servicioJugador.save(jugador);
        } else {
            throw new ExcepcionJugadorNotFound(id);
        }
    }

    @DeleteMapping("/jugador/{id}")
    public Jugador deleteJugador(@PathVariable Long id) {
        if(servicioJugador.existsById(id)){
            Jugador result = servicioJugador.findById(id).get();
            servicioJugador.deleteById(id);
            return result;
        } else {
            throw new ExcepcionJugadorNotFound(id);
        }
    }
    @GetMapping("/equipo/{id_equipo}/jugadores")
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable Long id_equipo) {
        List<Jugador> jugadores = servicioJugador.obtenerJugadoresPorEquipo(id_equipo);

        if(jugadores == null || jugadores.isEmpty()){
            throw new ExcepcionJugadorNotFound();
        }
        return ResponseEntity.ok(jugadores);
    }
}