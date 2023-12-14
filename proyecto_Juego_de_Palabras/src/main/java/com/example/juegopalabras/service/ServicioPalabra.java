package com.example.juegopalabras.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioPalabra {
    public String obtenerPalabra(){
        List<String> palabras= new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            String line;
            while((line=reader.readLine()) != null){
                palabras.add(line.trim());
            }
        } catch (IOException ioe){
            return null;
        }
        return palabras.get((int) (Math.random() * palabras.size() + 1));
    }

    public List<String> obtenerTodasPalabras(){
        List<String> palabras= new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            String line;
            while((line=reader.readLine()) != null){
                palabras.add(line.trim());

            }
        } catch (IOException ioe){
            return null;
        }
        return palabras;
    }

    public String obtenerPalabraFiltrada(String cadena) {
        List<String> palabras = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                palabras.add(line.trim());
            }
        } catch (IOException ioe) {
            return null;
        }
        List<String> palabrasFiltradas = palabras.stream()
                .filter(p -> p.contains(cadena))
                .collect(Collectors.toList());
        if (palabrasFiltradas.isEmpty()) {
            return null;
        }
        return palabrasFiltradas.get((int) (Math.random() * palabrasFiltradas.size()));
    }

    public List<String> obtenerTodasPalabraFiltrada(String cadena) {
        List<String> palabras = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                palabras.add(line.trim());
            }
        } catch (IOException ioe) {
            return null;
        }

        List<String> palabrasFiltradas = palabras.stream()
                .filter(p -> p.contains(cadena))
                .collect(Collectors.toList());
        if (palabrasFiltradas.isEmpty()) {
            return null;
        }
        return palabrasFiltradas;
    }

    public List<String> obtenerPalabras(Long numero){
        List<String> palabras= new ArrayList<>();
        List<String> palabras_devueltas = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            String line;

            while((line=reader.readLine()) != null){
                palabras.add(line.trim());
            }

            for(int i=0; i<numero; i++){
                palabras_devueltas.add(palabras.get((int) (Math.random() * palabras.size() + 1)));
            }
        } catch (IOException ioe){
            return null;
        }
        return palabras_devueltas;
    }
}