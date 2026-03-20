package com.clubdeportivo2.servicioreservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.clubdeportivo2.servicioreservas.model.Reserva;
import com.clubdeportivo2.servicioreservas.model.dto.Cancha;
import com.clubdeportivo2.servicioreservas.repository.ReservaRepository;
import java.util.List;
import com.clubdeportivo2.servicioreservas.feign_client.*;

@Service
public class ReservaServicesImpl implements ReservaServices {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    @SuppressWarnings("unused")
    private WebClient webClient; 

    @Autowired
    private CanchaClient canchaClient;
    
    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    /*
    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    } */


    /* 
    @Override
    public Reserva crearReserva(Reserva reserva) {

        Cancha cancha = webClient
                .get()
                .uri("/api/canchas/"+ reserva.getCanchaId())
                .retrieve()
                .bodyToMono(Cancha.class)
                .block();
        
        if (cancha == null){
            throw new RuntimeException("La cancha no existe");
        }

        return reservaRepository.save(reserva);
    }
    */

    @Override
    public Reserva crearReserva(Reserva reserva) {
        try {
            Cancha cancha = canchaClient.obtenerCanchaPorId(reserva.getCanchaId());
            
            if (cancha == null){
                throw new RuntimeException("La cancha no existe");
            }
        } catch (Exception e) {
            System.out.println("Advertencia: No se pudo validar la cancha con Feign. Continuando con la creación de la reserva. Error: " + e.getMessage());
        }

        return reservaRepository.save(reserva);
    }
   

    @Override
    public List<Reserva> buscarReservasPorCancha(Long canchaId) {
        return reservaRepository.findByCanchaId(canchaId);
    }

}