package com.ceiba.reserva.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservas")
@Api(tags={"Controlador consulta reserva"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas) {
        this.manejadorListarReservas = manejadorListarReservas;
    }

    @GetMapping
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar() {
        return this.manejadorListarReservas.ejecutarListar();
    }
    
    @GetMapping(value="/{cedulaCliente}")
    @ApiOperation("Listar Reservas Por Cedula")
    public List<DtoReserva> listarPorCedula(@PathVariable String cedulaCliente) {
        return this.manejadorListarReservas.ejecutarListarPorCedula(cedulaCliente);
    }

}
