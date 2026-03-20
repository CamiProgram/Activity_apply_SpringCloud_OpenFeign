package com.clubdeportivo2.servicioreservas.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.clubdeportivo2.servicioreservas.model.dto.Cancha; 

@FeignClient(name = "canchas-api", url = "http://localhost:8080/api/canchas")
public interface CanchaClient {
    
    @GetMapping("/{id}")
    Cancha obtenerCanchaPorId(@PathVariable("id") Long id);

}


