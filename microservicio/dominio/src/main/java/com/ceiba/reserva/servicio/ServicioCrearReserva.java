package com.ceiba.reserva.servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.ceiba.reserva.excepcion.ValidaLimiteReservas;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;


public class ServicioCrearReserva {

    private static final String EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR = "El usuario ya tiene 3 reservas pendientes sin entregar";
    private static final int CANTIDAD_MAXIMA_DE_RESERVAS_POR_CLIENTE = 3;
	private static final double PRECIO_POR_DIA_DE_RESERVA = 10000.0;
	private static final String ESTADO_PENDIENTE = "Pendiente";

    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
    	validarLimiteReservas(reserva);
		reserva.setFechaDeReserva(LocalDate.now());
		reserva.setPrecioCalculado(PRECIO_POR_DIA_DE_RESERVA * reserva.getDiasDeReserva());
		reserva.setEstado(ESTADO_PENDIENTE);
    	reserva.setFechaDeEntrega(sumarDiasHabiles(LocalDate.now(), reserva.getDiasDeReserva()));
        return this.repositorioReserva.crear(reserva);
    }

    private void validarLimiteReservas(Reserva reserva) {
        int cantidad = repositorioReserva.cantidadDeReservas(reserva.getCedulaCliente());
        if(cantidad>=CANTIDAD_MAXIMA_DE_RESERVAS_POR_CLIENTE) {
            throw new ValidaLimiteReservas(EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR);
        }
    }
    
    public LocalDate sumarDiasHabiles(LocalDate fechaActual, int diasHabiles) {
        int diasSumados = 0;

        while (diasHabiles > diasSumados) {
          fechaActual = fechaActual.plusDays(1);
          if (!(fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY
              || fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            ++diasSumados;
          }

        }
        return fechaActual;
      }
    
}
