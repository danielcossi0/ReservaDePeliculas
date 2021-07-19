package com.ceiba.configuracion;

import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.ServicioActualizarReserva;
import com.ceiba.usuario.servicio.ServicioCrearReserva;
import com.ceiba.usuario.servicio.ServicioEliminarReserva;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
