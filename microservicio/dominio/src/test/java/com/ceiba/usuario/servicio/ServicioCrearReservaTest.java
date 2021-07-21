package com.ceiba.usuario.servicio;

import com.ceiba.usuario.excepcion.ValidaLimiteReservas;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.testdatabuilder.ReservaTestDataBuilder;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

public class ServicioCrearReservaTest {

	private static final String EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR = "El usuario ya tiene 3 reservas pendientes sin entregar";
	private static final String LA_RESERVA_DEBE_SER_MENOR_A_5_DIAS = "Los dias de reserva deben ser menores a 5";
	private static final String DEBE_INGRESAR_EL_NOMBRE_DE_LA_PELICULA = "Debe ingresar el nombre de la pelicula";
	private static final String DEBE_INGRESAR_LA_CEDULA_DEL_CLIENTE = "Se debe ingresar la cedula del cliente";
	private static final String DEBE_INGRESAR_LOS_DIAS_DE_RESERVA = "Debe ingresar los dias de la reserva";

	@Test
	public void alquilarPeliculaCorrectamenteTest() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);

		// act - assert

		assertEquals(1L, servicioCrearReserva.ejecutar(reserva), 0.0);

	}

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

	@Test(expected = AssertionError.class)
	public void reservarSinNombreDePeliculaTest() {
		// arrange

		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

		// act

		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(null);
		// assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorObligatorio.class,
				DEBE_INGRESAR_EL_NOMBRE_DE_LA_PELICULA);
	}

	@Test(expected = AssertionError.class)
	public void reservarSinCedulaDeClienteTest() {
		// arrange

		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

		// act

		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(null);
		// assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorObligatorio.class,
				DEBE_INGRESAR_LA_CEDULA_DEL_CLIENTE);
	}

	@Test(expected = AssertionError.class)
	public void reservarSinIngresarDiasDeReservaTest() {
		// arrange

		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

		// act

		Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(null);
		// assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorObligatorio.class,
				DEBE_INGRESAR_LOS_DIAS_DE_RESERVA);
	}

	@Test
	public void crearDtoReservaTest() {

		// arrange
		DtoReserva dtoReserva = new DtoReserva(1L, "123456789", "Pelicula Test", LocalDate.now(), 1,
				LocalDate.now().plusDays(1), 10000., "Pendiente");
		// act - assert

		assertEquals(1L, dtoReserva.getIdReserva(), 0.0);
		assertEquals("123456789", dtoReserva.getCedulaCliente());
		assertEquals("Pelicula Test", dtoReserva.getNombreDeLaPelicula());
		assertEquals(LocalDate.now(), dtoReserva.getFechaDeReserva());
		assertEquals(1, dtoReserva.getDiasDeReserva());
		assertEquals(LocalDate.now().plusDays(1), dtoReserva.getFechaDeEntrega());
		assertEquals(10000.0, dtoReserva.getPrecioCalculado(), 0.0);
		assertEquals("Pendiente", dtoReserva.getEstado());

	}

	@Test
	public void crearReserva() {
		// arrange
		Reserva Reserva = new Reserva(1L, "123456789", "Pelicula Test", LocalDate.now(), 1, LocalDate.now().plusDays(1),
				10000., "Pendiente");
		// act - assert

		assertEquals(1L, Reserva.getIdReserva(), 0.0);
		assertEquals("123456789", Reserva.getCedulaCliente());
		assertEquals("Pelicula Test", Reserva.getNombreDeLaPelicula());
		assertEquals(LocalDate.now(), Reserva.getFechaDeReserva());
		assertEquals(1, Reserva.getDiasDeReserva());
		assertEquals(LocalDate.now().plusDays(1), Reserva.getFechaDeEntrega());
		assertEquals(10000.0, Reserva.getPrecioCalculado(), 0.0);
		assertEquals("Pendiente", Reserva.getEstado());

	}

	@Test
	public void ValidaLimiteReservasTest() throws Exception {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().build();
		RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
		ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
		Mockito.when(repositorioReserva.cantidadDeReservas(Mockito.any())).thenReturn(5);

		// act - assert

		BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ValidaLimiteReservas.class,
				EL_USUARIO_YA_TIENE_TRES_RESERVAS_PENDIENTES_SIN_ENTREGAR);

	}

}
