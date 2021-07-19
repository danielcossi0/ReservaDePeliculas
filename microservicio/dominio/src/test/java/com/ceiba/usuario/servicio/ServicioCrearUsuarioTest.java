package com.ceiba.usuario.servicio;

import com.ceiba.usuario.excepcion.ValidaLimiteReservas;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearUsuarioTest {

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

}
