package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(
        		comandoReserva.getIdReserva(),
        		comandoReserva.getCedulaCliente(),
        		comandoReserva.getNombreDeLaPelicula(),
        		comandoReserva.getFechaDeReserva(),
        		comandoReserva.getDiasDeReserva(),
        		comandoReserva.getFechaDeEntrega(),
        		comandoReserva.getPrecioCalculado(),
        		comandoReserva.getEstado()
        );
    }

}
