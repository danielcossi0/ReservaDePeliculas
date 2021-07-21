package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoReserva {
	
    private Long idReserva;
    private String cedulaCliente;
    private String nombreDeLaPelicula;
    private LocalDate fechaDeReserva;
    private int diasDeReserva;
    private LocalDate fechaDeEntrega;
    private double precioCalculado;
    private String estado;

}
