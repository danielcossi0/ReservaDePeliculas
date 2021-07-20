package com.ceiba.usuario.servicio;

import com.ceiba.usuario.excepcion.ValidaLimiteReservas;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearReservaTest {

	private static final String EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR = "El usuario ya tiene 3 reservas pendientes sin entregar";
	private static final String LA_RESERVA_DEBE_SER_MENOR_A_5_DIAS = "Los dias de reserva deben ser menores a 5";

	@Test(expected = AssertionError.class)
	public void alquilarPeliculaConLimiteDeReservasTest() {

		// arrange

		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

		// act

		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);
		// assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ValidaLimiteReservas.class,
				EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR);
	}

	@Test(expected = AssertionError.class)
	public void calcularFechaDeEntregaDebeSerMenorA5DiasTest() {
		// arrange

		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

		// act

		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);
		// assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorInvalido.class,
				LA_RESERVA_DEBE_SER_MENOR_A_5_DIAS);
	}

	@Test
	public void alquilarPeliculaCorrectamenteTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);
		
		// act -  assert
		
		
		assertEquals(1L, servicioCrearReserva.ejecutar(reserva), 0.0);
		 
	}

}
