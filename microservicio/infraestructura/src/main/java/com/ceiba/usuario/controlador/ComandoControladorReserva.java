package com.ceiba.usuario.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.comando.manejador.ManejadorActualizarReserva;
import com.ceiba.usuario.comando.manejador.ManejadorCrearReserva;
import com.ceiba.usuario.comando.manejador.ManejadorEliminarReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva"})
public class ComandoControladorReserva {

   
    private final ManejadorCrearReserva manejadorCrearReserva;
	private final ManejadorEliminarReserva manejadorEliminarReserva;
	private final ManejadorActualizarReserva manejadorActualizarReserva;

	
	
	@Autowired
    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva,
    								ManejadorEliminarReserva manejadorEliminarReserva,
    								ManejadorActualizarReserva manejadorActualizarReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
		this.manejadorEliminarReserva = manejadorEliminarReserva;
		this.manejadorActualizarReserva = manejadorActualizarReserva;
    }
	 
	

    @PostMapping
    @ApiOperation("Crear Reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrearReserva.ejecutar(comandoReserva);
    }

    @DeleteMapping(value="/{idReserva}")
	@ApiOperation("Eliminar Reserva")
	public void eliminar(@PathVariable Long idReserva) {
		manejadorEliminarReserva.ejecutar(idReserva);
	}

	@PutMapping(value="/{idReserva}")
	@ApiOperation("Actualizar Reserva")
	public void actualizar(@RequestBody ComandoReserva comandoReserva,@PathVariable Long idReserva) {
		comandoReserva.setIdReserva(idReserva);
		manejadorActualizarReserva.ejecutar(comandoReserva);
	}
}
