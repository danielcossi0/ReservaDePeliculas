package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.ServicioEliminarReserva;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva) {
        return new ServicioCrearReserva(repositorioReserva);
    }

    @Bean
    public ServicioEliminarReserva servicioEliminarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioEliminarReserva(repositorioReserva);
    }

    @Bean
    public ServicioActualizarReserva servicioActualizarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioActualizarReserva(repositorioReserva);
    }
	

}
