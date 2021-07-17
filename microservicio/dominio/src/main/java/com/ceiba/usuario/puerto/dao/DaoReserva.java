package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoReserva;

import java.util.List;

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
    List<DtoReserva> listarPorCedula();
}
