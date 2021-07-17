package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarMenor;;

@Getter
public class Reserva {

    private static final String DEBE_INGRESAR_LA_CEDULA_DEL_CLIENTE = "Se debe ingresar la cedula del cliente";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DE_LA_PELICULA = "Se debe ingresar la cedula del cliente";
    private static final String DEBE_INGRESAR_LOS_DIAS_DE_RESERVA = "Se debe ingresar la cedula del cliente";
    private static final String LA_RESERVA_DEBE_SER_MENOR_A_5_DIAS = "Se debe ingresar la cedula del cliente";
    
    private static final int DIAS_MAXIMOS_DE_RESERVA= 5;
    
    private Long idReserva;
    private String cedulaCliente;
    private String nombreDeLaPelicula;
    private LocalDate fechaDeReserva;
    private int diasDeReserva;
    private LocalDate fechaDeEntrega;
    private double precioCalculado;
    
    public Reserva(Long idReserva, 
    		String cedulaCliente, 
    		String nombreDeLaPelicula, 
    		LocalDate fechaDeReserva,
    		int diasDeReserva,
    		LocalDate fechaDeEntrega,
    		double precioCalculado) {
    	
    	validarObligatorio(cedulaCliente, DEBE_INGRESAR_LA_CEDULA_DEL_CLIENTE);
    	validarObligatorio(nombreDeLaPelicula, DEBE_INGRESAR_EL_NOMBRE_DE_LA_PELICULA);
    	validarObligatorio(diasDeReserva, DEBE_INGRESAR_LOS_DIAS_DE_RESERVA);
    	validarMenor(Long.valueOf(diasDeReserva), Long.valueOf(DIAS_MAXIMOS_DE_RESERVA), LA_RESERVA_DEBE_SER_MENOR_A_5_DIAS);
    	
    	
    	this.idReserva=idReserva;
    	this.cedulaCliente=cedulaCliente;
    	this.nombreDeLaPelicula=nombreDeLaPelicula;
    	this.fechaDeReserva=fechaDeReserva;
    	this.diasDeReserva=diasDeReserva;
    	this.fechaDeEntrega=fechaDeEntrega;
    	this.precioCalculado=precioCalculado;
    	
    }
    
    
    

}