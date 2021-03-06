package com.ceiba.reserva.servicio.testdatabuilder;

import java.time.LocalDate;

import com.ceiba.reserva.modelo.entidad.Reserva;

public class ReservaTestDataBuilder {
	
    private Long idReserva;
    private String cedulaCliente;
    private String nombreDeLaPelicula;
    private LocalDate fechaDeReserva;
    private int diasDeReserva;
    private LocalDate fechaDeEntrega;
    private double precioCalculado;
    private String estado;
    
    public ReservaTestDataBuilder() {
    	  cedulaCliente="1005879105";
          nombreDeLaPelicula="Piratas del caribe";
          fechaDeReserva=LocalDate.now();
          diasDeReserva=5;
          fechaDeEntrega=LocalDate.now().plusDays(diasDeReserva);
          precioCalculado=50000;
          estado="Pendiente";
    	
    }
    
    public ReservaTestDataBuilder conId(Long idReserva) {
     this.idReserva=idReserva;
     return this;
    }
    
    public Reserva build() {
    	return new Reserva(idReserva, cedulaCliente, nombreDeLaPelicula, fechaDeReserva, diasDeReserva, fechaDeEntrega, precioCalculado, estado);
    }

}
