package com.ceiba.usuario.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {
	
    private Long idReserva;
    private String cedulaCliente;
    private String nombreDeLaPelicula;
    private LocalDateTime fechaDeReserva;
    private int diasDeReserva;
    private LocalDateTime fechaDeEntrega;
    private double precioCalculado;

}
