package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private static final double PRECIO_POR_DIA_DE_RESERVA = 10000.0;
	private static final String ESTADO_PENDIENTE = "Pendiente";
	private static final String ID_RESERVA = "idReserva";

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

	@SqlStatement(namespace = "reserva", value = "cantidadDeReservas")
	private static String sqlcantidadDeReservas;

	public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Reserva reserva) {
		reserva.setFechaDeReserva(LocalDate.now());
		reserva.setPrecioCalculado(PRECIO_POR_DIA_DE_RESERVA * reserva.getDiasDeReserva());
		reserva.setFechaDeEntrega(LocalDate.now().plusDays(reserva.getDiasDeReserva()));
		reserva.setEstado(ESTADO_PENDIENTE);
		return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
	}

	@Override
	public void eliminar(Long idReserva) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID_RESERVA, idReserva);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existe(Long idReserva) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID_RESERVA, idReserva);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public void actualizar(Reserva reserva) {

		this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);

	}

	@Override
	public int cantidadDeReservas(String cedulaCliente) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cedulaCliente", cedulaCliente);
		paramSource.addValue("estado", ESTADO_PENDIENTE);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlcantidadDeReservas, paramSource, int.class);
	}

}
