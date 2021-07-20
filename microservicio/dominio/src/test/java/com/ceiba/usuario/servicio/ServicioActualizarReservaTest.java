package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import junit.framework.Assert;

import static org.junit.Assert.assertSame;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarReservaTest {

	private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";

	@Test
	public void devolverPeliculaAntesDeFechaDeEntregaActualizaPrecioTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
		LocalDate fechaDeReserva = LocalDate.parse("2021-07-19", DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		LocalDate fechaDeEntrega = LocalDate.parse("2021-07-24", DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		
		DtoReserva dtoReserva = new DtoReserva(1L, "132456789",
				"Pelicula test", fechaDeReserva, 5, fechaDeEntrega, 50000.0, "Pendiente");
		// act - assert

		//AQUI VOY
		
		
		
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

	@Test
	public void eliminarReserva() {
		// arrange

		// act

		// assert

	}

}
