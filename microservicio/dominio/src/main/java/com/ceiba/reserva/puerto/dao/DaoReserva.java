package com.ceiba.reserva.puerto.dao;

import java.util.List;

import com.ceiba.reserva.modelo.dto.DtoReserva;

public interface DaoReserva {

	/**
     * Permite listar todas las reservas
     * @return las reservas
     */
    List<DtoReserva> listar();
    
    /**
     * Permite listar las reservas de una persona
     * @return las reservas de una persona
     */ 
    List<DtoReserva> listarPorCedula(String cedulaCliente);
}
