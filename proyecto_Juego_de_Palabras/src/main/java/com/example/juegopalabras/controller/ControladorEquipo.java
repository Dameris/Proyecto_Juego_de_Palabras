package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.ExepcionEquipoNotFound;
import com.example.juegopalabras.modelo.Equipo;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.service.ServicioImplEquipo;
import com.example.juegopalabras.service.ServicioJugador;
import com.example.juegopalabras.service.ServicioPartida;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ControladorEquipo {
    private final ServicioImplEquipo servicioEquipo;
    private final ServicioJugador servicioJugador;
    private final ServicioPartida servicioPartida;
    @GetMapping("/equipo")
    public List<Equipo> obtenerTodos() {
        List<Equipo> result =  servicioEquipo.findAll();
        if(result.isEmpty()){
            throw new ExepcionEquipoNotFound();
        }
        return result;
    }
    @GetMapping("/equipo/{id}/puntos")
    public int obtenerPuntosTotales(@PathVariable Long id) {
        List<Jugador> jugadores = servicioEquipo.findById(id).orElseThrow(() -> new ExepcionEquipoNotFound(id)).getJugadores();
        int totalPuntos = 0;

        for (Jugador jugador : jugadores) {
            totalPuntos += servicioPartida.getTotalPuntosByJugadorId(jugador.getId());
        }

        return totalPuntos;
    }

    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) {
        return servicioEquipo.findById(id).orElseThrow(() -> new ExepcionEquipoNotFound(id));
    }

    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return servicioEquipo.save(newEquipo);
    }

    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        if (servicioEquipo.existsById(id)) {
            updateEquipo.setId(id);
            Equipo equipo = servicioEquipo.findById(id).get();
            equipo.setName(updateEquipo.getName());
            equipo.setEmblema(updateEquipo.getEmblema());
            equipo.setPuntuacion(updateEquipo.getPuntuacion());
            return servicioEquipo.save(updateEquipo);
        } else {
            throw new ExepcionEquipoNotFound(id);
        }
    }

    @DeleteMapping("/equipo/{id}")
    public Equipo deleteEquipo(@PathVariable Long id) {
        if(servicioEquipo.existsById(id)){
            Equipo result = servicioEquipo.findById(id).get();
            servicioEquipo.deleteById(id);
            return result;
        } else{
            throw new ExepcionEquipoNotFound(id);
        }
    }
}