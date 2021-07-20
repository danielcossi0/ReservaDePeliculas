package com.ceiba.usuario.servicio;

import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


import org.junit.Test;
import org.mockito.Mockito;


public class ServicioEliminarReservaTest {

	@Test
	public void eliminarReserva() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva);

		// act

		servicioEliminarReserva.ejecutar(reserva.getIdReserva());

		// assert

		verify(repositorioReserva, atLeastOnce()).eliminar(1L);
		

	}

}
