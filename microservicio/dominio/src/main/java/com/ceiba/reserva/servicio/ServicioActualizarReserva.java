package com.ceiba.reserva.servicio;

import java.time.LocalDate;
import java.time.Period;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioActualizarReserva {

	private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";
	private static final double PRECIO_POR_DIA_DE_RESERVA = 10000.0;
	private static final double PRECIO_ADICIONAL_POR_DIA_DE_RESERVA = 15000.0;
	private final RepositorioReserva repositorioReserva;

	public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
		this.repositorioReserva = repositorioReserva;
	}

	public void ejecutar(Reserva reserva) {
		validarExistenciaPrevia(reserva);

		validarFechaDeEntrega(reserva);

		this.repositorioReserva.actualizar(reserva);
	}

	private void validarExistenciaPrevia(Reserva reserva) {
		boolean existe = this.repositorioReserva.existe(reserva.getIdReserva());
		if (!existe) {
			throw new ExcepcionSinDatos(LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
		}
	}

	private Reserva validarFechaDeEntrega(Reserva reserva) {
		Period diasAdicionales = reserva.getFechaDeReserva().plusDays(reserva.getDiasDeReserva())
				.until(reserva.getFechaDeEntrega());

		LocalDate fechaRealDeEntrega = reserva.getFechaDeEntrega();
		LocalDate fechaDeEntregaEstimada = reserva.getFechaDeReserva().plusDays(reserva.getDiasDeReserva());

		if (fechaRealDeEntrega.isAfter(fechaDeEntregaEstimada)) {

			reserva.setPrecioCalculado(PRECIO_POR_DIA_DE_RESERVA * reserva.getDiasDeReserva()
					+ PRECIO_ADICIONAL_POR_DIA_DE_RESERVA * diasAdicionales.getDays());

		} else if (fechaRealDeEntrega.isBefore(fechaDeEntregaEstimada)) {
			Period diasRealesDeReserva = reserva.getFechaDeReserva().until(reserva.getFechaDeEntrega());
			reserva.setPrecioCalculado(diasRealesDeReserva.getDays() * PRECIO_POR_DIA_DE_RESERVA);
		}

		return reserva;
	}
}
