package com.ceiba.usuario.excepcion;

public class ValidaLimiteReservas extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidaLimiteReservas(String mensaje) {
		super(mensaje);
	}

}