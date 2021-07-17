package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.entidad.Reserva;

public interface RepositorioReserva {
	/**
	 * Permite crear una reserva
	 * 
	 * @param reserva
	 * @return el idReserva generado
	 */
	Long crear(Reserva reserva);

	/**
	 * Permite actualizar una reserva
	 * 
	 * @param reserva
	 */
	void actualizar(Reserva reserva);

	/**
	 * Permite eliminar una reserva
	 * 
	 * @param id
	 */
	void eliminar(Long idReserva);

	/**
	 * Permite validar si existe una reserva con una cedula
	 * 
	 * @param cedula
	 * @return si existe o no
	 */
	boolean existe(String cedula);

	/**
	 * Permite validar si existe una reserva con una cedula excluyendo un id
	 * 
	 * @param cedula
	 * @return si existe o no
	 */
	boolean existeExcluyendoId(Long idReserva, String cedula);

	/**
	 * Permite validar si existen 3 reservas de un cliente
	 * 
	 * @param cedula
	 * @return si puede o no reservar
	 */
	boolean puedeReservar(String cedula);
	
	/**
	 * Permite contar cuantas reservas tiene un usuario
	 * 
	 * @param cedula
	 * @return numero de reservas
	 */
	int cantidadDeReservas(String cedula);

}
