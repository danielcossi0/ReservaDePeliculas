package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioEliminarReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


import org.junit.Test;
import org.mockito.Mockito;


public class ServicioEliminarReservaTest {

	@Test
	public void eliminarReservaTest() {
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
