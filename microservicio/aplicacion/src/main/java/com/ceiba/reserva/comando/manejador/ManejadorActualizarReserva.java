package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.servicio.ServicioActualizarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarReserva implements ManejadorComando<ComandoReserva> {

    private final FabricaReserva fabricaReserva;
    private final ServicioActualizarReserva actualizarReserva;

    public ManejadorActualizarReserva(FabricaReserva fabricaReserva, ServicioActualizarReserva actualizarReserva) {
        this.fabricaReserva = fabricaReserva;
        this.actualizarReserva = actualizarReserva;
    }

    public void ejecutar(ComandoReserva comandoReserva) {
        Reserva reserva = this.fabricaReserva.crear(comandoReserva);
        this.actualizarReserva.ejecutar(reserva);
    }
}
