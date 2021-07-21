package com.ceiba.reserva.consulta;

import java.util.List;

import com.ceiba.usuario.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoReserva;

@Component
public class ManejadorListarReservas {

    private final DaoReserva daoReserva;

    public ManejadorListarReservas(DaoReserva daoReserva){
        this.daoReserva = daoReserva;
    }

    public List<DtoReserva> ejecutarListar(){ return this.daoReserva.listar(); }
    public List<DtoReserva> ejecutarListarPorCedula(String cedulaCliente){ return this.daoReserva.listarPorCedula(cedulaCliente); }
}
