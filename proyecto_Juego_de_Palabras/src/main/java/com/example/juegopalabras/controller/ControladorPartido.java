package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.ExcepcionPartidaNotFound;
import com.example.juegopalabras.modelo.Partida;
import com.example.juegopalabras.service.ServicioPartida;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(produces = "application/json")
@RestController
@RequiredArgsConstructor
public class PartidaController {
    @Autowired
    private ServicioPartida servicioPartida;
    @GetMapping("/partida")
    public List<Partida> obtenerTodos() {
        List<Partida> result =  servicioPartida.findAll();
        if(result.isEmpty()){
            throw new ExcepcionPartidaNotFound();
        }
        return result;
    }

    @GetMapping("/partida/{id}")
    public Partida getPartida(@PathVariable Long id) {
        return servicioPartida.findById(id).orElseThrow(() -> new ExcepcionPartidaNotFound(id));
    }
    @GetMapping("/jugador/{id}/puntos")
    public int getPuntosTotalesJugador(@PathVariable Long id) {
            return  servicioPartida.getTotalPuntosByJugadorId(id);

    }
    @PostMapping("/partida")
    public Partida newPartida(@RequestBody Partida newPartida)
    {
        return servicioPartida.save(newPartida);
    }

    @PutMapping("/partida/{id}")
    public Partida updatePartida(@RequestBody Partida partidaUpdate, @PathVariable Long id) {
        if (servicioPartida.existsById(id)) {
            Partida partida = servicioPartida.findById(id).orElseThrow(() -> new ExcepcionPartidaNotFound(id));
            partida.setStartTime(partidaUpdate.getStartTime());
            partida.setEndTime(partidaUpdate.getEndTime());
            partida.setIntentos(partidaUpdate.getIntentos());
            partida.setPalabraRonda(partidaUpdate.getPalabraRonda());
            partida.setPuntuacion(partidaUpdate.getPuntuacion());

            return servicioPartida.save(partida);
        } else {
            throw new ExcepcionPartidaNotFound(id);
        }
    }

    @DeleteMapping("partida/{id}")
    public Partida deletePartida(@PathVariable Long id) {
        if(servicioPartida.existsById(id)){
            Partida result = servicioPartida.findById(id).get();
            servicioPartida.deleteById(id);
            return result;
        }else{
            throw new ExcepcionPartidaNotFound(id);
        }
    }
}