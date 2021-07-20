package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import junit.framework.Assert;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import com.ceiba.BasePrueba;

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
