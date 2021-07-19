package com.ceiba.usuario.comando;

import java.time.LocalDate;

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
    private LocalDate fechaDeReserva;
    private int diasDeReserva;
    private LocalDate fechaDeEntrega;
    private double precioCalculado;
    private String estado;
}
