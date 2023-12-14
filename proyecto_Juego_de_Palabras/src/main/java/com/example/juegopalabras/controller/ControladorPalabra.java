package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.ExcepcionPalabraNotFound;
import com.example.juegopalabras.service.ServicioPalabra;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(produces = "application/json")
@RestController
@RequiredArgsConstructor
public class PalabraController {
    @Autowired
    private ServicioPalabra servicioPalabra;
    @GetMapping("/palabra")
    public String obtenerPalabra() {
        String palabra = servicioPalabra.obtenerPalabra();
        if(palabra == null){
            throw new ExcepcionPalabraNotFound();
        }
        return palabra;


    }
    @GetMapping("/palabras")
    public List<String> obtenerTodasPalabras() {
        List<String> palabras = servicioPalabra.obtenerTodasPalabras();
        if(palabras == null || palabras.isEmpty()){
            throw new ExcepcionPalabraNotFound();
        }
        return palabras;


    }
    @GetMapping("/palabras/{numero}")
    public List<String> obtenerPalabras(@PathVariable Long numero) {
        List<String> palabras =  servicioPalabra.obtenerPalabras(numero);
        if(palabras == null || palabras.isEmpty()){
            throw new ExcepcionPalabraNotFound();
        }
        return palabras;

    }
    @GetMapping("/palabra/filtro={filtro}")
    public String obtenerPalabraFiltrada(@PathVariable String filtro) {
        String palabra=  servicioPalabra.obtenerPalabraFiltrada(filtro);
        if(palabra==null){
            throw new ExcepcionPalabraNotFound();
        }
        return palabra;


    }
    @GetMapping("/palabras/filtro={filtro}")
    public List<String> obtenerTodasPalabraFiltrada(@PathVariable String filtro) {
        List<String> palabras = servicioPalabra.obtenerTodasPalabraFiltrada(filtro);
        if(palabras==null || palabras.isEmpty()){
            throw new ExcepcionPalabraNotFound();
        }
        return palabras;
    }
}