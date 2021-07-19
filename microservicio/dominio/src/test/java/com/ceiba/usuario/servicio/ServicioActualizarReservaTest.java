package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarReservaTest {

	@Test
	public void devolverPeliculaAntesDeFechaDeEntregaActualizaPrecio() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		
		
		// act
		
		// assert
		
	}

	@Test
	public void devolverPeliculaDespuesDeFechaDeEntregaActualizaPrecio() {
		// arrange
		
		// act
		
		// assert

	}

	@Test
	public void eliminarReserva() {
		// arrange
		
		// act
		
		// assert
		
		
	}

}
