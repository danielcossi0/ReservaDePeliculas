package com.ceiba.usuario.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva{

    private Long idReserva;
    private String cedulaCliente;
    private String nombreDeLaPelicula;
    private LocalDateTime fechaDeReserva;
    private int diasDeReserva;
    private LocalDateTime fechaDeEntrega;
    private double precioCalculado;
}
