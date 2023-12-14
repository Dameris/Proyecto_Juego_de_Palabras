package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.ExcepcionJuegoNotFound;
import com.example.juegopalabras.modelo.Juego;
import com.example.juegopalabras.modelo.Jugador;
import com.example.juegopalabras.service.ServicioJuego;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorJuego {
    private final ServicioJuego servicioJuego;
    @GetMapping("/juego")
    public List<Juego> obtenerTodos() {
        List<Juego> result =  servicioJuego.findAll();
        if(result.isEmpty()){
            throw new ExcepcionJuegoNotFound();
        }
        return result;
    }

    @GetMapping("/juego/{id}")
    public Juego obtenerUno(@PathVariable Long id) {
        return servicioJuego.findById(id).orElseThrow(() -> new ExcepcionJuegoNotFound(id));
    }

    @PostMapping("/juego")
    public Juego newJuego(@RequestBody Juego newJuego){
        return servicioJuego.save(newJuego);
    }

    @PutMapping("/juego/{id}")
    public Juego updateJuego(@RequestBody Juego updateJuego, @PathVariable Long id){
        if (servicioJuego.existsById(id)) {
            Juego juego = servicioJuego.findById(id).get();
            juego.setDificultadjuego(updateJuego.getDificultadjuego());
            juego.setDescripcion(updateJuego.getDescripcion());
            juego.setIntentosDificultad(updateJuego.getIntentosDificultad());
            return servicioJuego.save(updateJuego);
        } else {
            throw new ExcepcionJuegoNotFound(id);
        }
    }

    @DeleteMapping("/juego/{id}")
    public Juego deleteJuego(@PathVariable Long id) {
        if(servicioJuego.existsById(id)){
            Juego result = servicioJuego.findById(id).get();
            servicioJuego.deleteById(id);
            return result;
        }else{
            throw new ExcepcionJuegoNotFound(id);
        }
    }
}