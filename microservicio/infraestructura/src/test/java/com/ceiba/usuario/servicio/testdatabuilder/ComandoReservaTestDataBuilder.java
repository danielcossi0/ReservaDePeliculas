package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;

import com.ceiba.reserva.comando.ComandoReserva;

public class ComandoReservaTestDataBuilder {

	private Long idReserva;
	private String cedulaCliente;
	private String nombreDeLaPelicula;
	private LocalDate fechaDeReserva;
	private int diasDeReserva;
	private LocalDate fechaDeEntrega;
	private double precioCalculado;
	private String estado;

	public ComandoReservaTestDataBuilder() {
		cedulaCliente = "1005879105";
		nombreDeLaPelicula = "Piratas del caribe";
		fechaDeReserva = LocalDate.now();
		diasDeReserva = 5;
		fechaDeEntrega = LocalDate.now().plusDays(diasDeReserva);
		precioCalculado = 50000;
		estado = "Pendiente";

	}


	public ComandoReserva build() {
		return new ComandoReserva(idReserva, cedulaCliente, nombreDeLaPelicula, fechaDeReserva, diasDeReserva, fechaDeEntrega,
				precioCalculado, estado);
	}
}
