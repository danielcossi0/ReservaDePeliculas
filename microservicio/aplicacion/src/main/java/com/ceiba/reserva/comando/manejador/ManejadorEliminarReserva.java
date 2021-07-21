package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.servicio.ServicioEliminarReserva;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarReserva implements ManejadorComando<Long> {

    private final ServicioEliminarReserva servicioEliminarReserva;

    public ManejadorEliminarReserva(ServicioEliminarReserva servicioEliminarReserva) {
        this.servicioEliminarReserva = servicioEliminarReserva;
    }

    public void ejecutar(Long idReserva) {
        this.servicioEliminarReserva.ejecutar(idReserva);
    }
}
