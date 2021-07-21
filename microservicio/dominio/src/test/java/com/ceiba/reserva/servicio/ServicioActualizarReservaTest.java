package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.ServicioActualizarReserva;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarReservaTest {

	private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";

	@Test
	public void devolverPeliculaTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
		LocalDate fechaDeEntregaActualizada = reserva.getFechaDeEntrega().plusDays(1);
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);

		Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
		;

		// act

		reserva.setFechaDeEntrega(fechaDeEntregaActualizada);
		servicioActualizarReserva.ejecutar(reserva);

		// assert

		verify(repositorioReserva, atLeast(1)).actualizar(reserva);
		;

	}

	@Test
	public void actualizarReservaQueNoExisteTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
		Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(false);
		// act - assert

		BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva), ExcepcionSinDatos.class,
				LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
	}

}
