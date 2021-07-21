package com.ceiba.reserva.servicio;

import com.ceiba.reserva.excepcion.ValidaLimiteReservas;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;


public class ServicioCrearReserva {

    private static final String EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR = "El usuario ya tiene 3 reservas pendientes sin entregar";
    private static final int CANTIDAD_MAXIMA_DE_RESERVAS_POR_CLIENTE = 3;

    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
    	validarLimiteReservas(reserva);
        return this.repositorioReserva.crear(reserva);
    }

    private void validarLimiteReservas(Reserva reserva) {
        int cantidad = repositorioReserva.cantidadDeReservas(reserva.getCedulaCliente());
        if(cantidad>=CANTIDAD_MAXIMA_DE_RESERVAS_POR_CLIENTE) {
            throw new ValidaLimiteReservas(EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR);
        }
    }
}
