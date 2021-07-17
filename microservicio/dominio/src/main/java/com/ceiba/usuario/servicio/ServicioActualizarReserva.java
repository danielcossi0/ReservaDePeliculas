package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;

public class ServicioActualizarReserva {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";

    private final RepositorioReserva repositorioReserva;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Reserva reserva) {
        validarExistenciaPrevia(reserva);
        this.repositorioReserva.actualizar(reserva);
    }

    private void validarExistenciaPrevia(Reserva reserva) {
        boolean existe = this.repositorioReserva.existeExcluyendoId(reserva.getIdReserva(),reserva.getCedulaCliente());
        if(!existe) {
            throw new ExcepcionSinDatos(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
