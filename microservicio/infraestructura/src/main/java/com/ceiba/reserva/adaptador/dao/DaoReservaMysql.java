package com.ceiba.reserva.adaptador.dao;

import java.util.List;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoReservaMysql implements DaoReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="reserva", value="listar")
    private static String sqlListar;
    
    @SqlStatement(namespace="reserva", value="listarPorCedula")
    private static String sqlListarPorCedula;

    public DaoReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoReserva> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoReserva());
    }

	@Override
	public List<DtoReserva> listarPorCedula(String cedulaCliente) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("cedulaCliente", cedulaCliente);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorCedula, paramSource, new MapeoReserva());       
 
        
	}
}
