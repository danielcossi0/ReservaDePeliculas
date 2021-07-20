package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;


import java.time.LocalDate;
import java.time.Period;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private final double precioPorDiaDeReserva = 10000.0;
	private final double precioPorDiaAdicionalDeReserva = 15000.0;
	private final String estadoPendiente="Pendiente";
	private static final String id = "idReserva";
	
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
		reserva.setPrecioCalculado(precioPorDiaDeReserva * reserva.getDiasDeReserva());
		reserva.setFechaDeEntrega(LocalDate.now().plusDays(reserva.getDiasDeReserva()));
		reserva.setEstado(estadoPendiente);
		return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
	}

	@Override
	public void eliminar(Long idReserva) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(id, idReserva);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existe(Long idReserva) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(id, idReserva);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public void actualizar(Reserva reserva) {
		Period diasAdicionales = reserva.getFechaDeReserva().plusDays(reserva.getDiasDeReserva())
				.until(reserva.getFechaDeEntrega());

		this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
		
		LocalDate fechaRealDeEntrega = reserva.getFechaDeEntrega();
		LocalDate fechaDeEntregaEstimada = reserva.getFechaDeReserva().plusDays(reserva.getDiasDeReserva());
		
		if (fechaRealDeEntrega.isAfter(fechaDeEntregaEstimada)) {

			reserva.setPrecioCalculado(precioPorDiaDeReserva * reserva.getDiasDeReserva()
					+ precioPorDiaAdicionalDeReserva * diasAdicionales.getDays());
			
			this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
			
		}else if(fechaRealDeEntrega.isBefore(fechaDeEntregaEstimada)){
			Period diasRealesDeReserva = reserva.getFechaDeReserva().until(reserva.getFechaDeEntrega());
			reserva.setPrecioCalculado(diasRealesDeReserva.getDays()*precioPorDiaDeReserva);
			this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
		}
		
	}
	
	

	@Override
	public boolean existeExcluyendoId(Long idReserva, String cedula) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(id, idReserva);
		paramSource.addValue("cedulaCliente", cedula);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlExisteExcluyendoId, paramSource, Boolean.class);
	}

	@Override
	public int cantidadDeReservas(String cedulaCliente) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cedulaCliente", cedulaCliente);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlcantidadDeReservas, paramSource, int.class);
	}

	
}
