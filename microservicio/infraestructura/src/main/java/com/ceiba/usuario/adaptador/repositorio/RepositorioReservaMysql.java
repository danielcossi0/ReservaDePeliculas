package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "reserva", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "reserva", value = "actualizar")
	private static String sqlActualizar;

	@SqlStatement(namespace = "reserva", value = "eliminar")
	private static String sqlEliminar;

	@SqlStatement(namespace = "reserva", value = "existe")
	private static String sqlExiste;

	@SqlStatement(namespace = "reserva", value = "existeExcluyendoId")
	private static String sqlExisteExcluyendoId;

	@SqlStatement(namespace = "reserva", value = "puedeReservar")
	private static String sqlPuedeReservar;
	
	@SqlStatement(namespace = "reserva", value = "cantidadDeReservas")
	private static String sqlcantidadDeReservas;
	
	
	

	public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Reserva reserva) {
		return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
	}

	@Override
	public void eliminar(Long idReserva) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("idReserva", idReserva);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existe(String cedula) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cedulaCliente", cedula);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public void actualizar(Reserva reserva) {
		this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
	}

	@Override
	public boolean existeExcluyendoId(Long idReserva, String cedula) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("idReserva", idReserva);
		paramSource.addValue("cedulaCliente", cedula);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlExisteExcluyendoId, paramSource, Boolean.class);
	}

	

	@Override
	public int cantidadDeReservas(String cedula) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cedulaCliente", cedula);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlcantidadDeReservas,
				paramSource, int.class);
	}
}
