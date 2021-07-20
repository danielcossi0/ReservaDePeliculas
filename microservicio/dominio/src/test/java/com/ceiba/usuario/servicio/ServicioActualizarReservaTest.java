package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarReservaTest {

	private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";

	@Test
	public void devolverPeliculaAntesDeFechaDeEntregaActualizaPrecioTest() {
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

		verify(repositorioReserva, atLeastOnce()).actualizar(reserva);
		;

	}

	@Test(expected = AssertionError.class)
	public void validarReservaExisteEnElSistemaTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
		Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
		// act - assert

		BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva), ExcepcionSinDatos.class,
				LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
	}

}
