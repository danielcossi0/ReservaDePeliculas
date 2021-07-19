package com.ceiba.usuario.servicio;

import com.ceiba.usuario.excepcion.ValidaLimiteReservas;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearReservaTest {

	private static final String EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR = "El usuario ya tiene 3 reservas pendientes sin entregar";

	
	

	@Test(expected = AssertionError.class)
	public void alquilarPeliculaConLimiteDeReservas() {

		// arrange
		
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
		
		//act 
		
		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);		
		//assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ValidaLimiteReservas.class,
				EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR);}

	public void calcularFechaDeEntregaDebeSerMenorA5Dias() {
		// arrange

		// act

		// assert

	}

	public void calcularPrecioDeReservaSumandoDiezMilDiarios() {
		// arrange

		// act

		// assert

	}


}
