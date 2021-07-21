package com.ceiba.reserva.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

       
        Long idReserva = resultSet.getLong("idReserva");
        String cedulaCliente = resultSet.getString("cedulaCliente");
        String nombreDeLaPelicula = resultSet.getString("nombreDeLaPelicula");
        LocalDate fechaDeReserva = extraerLocalDate(resultSet, "fechaDeReserva");
        int diasDeReserva = resultSet.getInt("diasDeReserva");
        LocalDate fechaDeEntrega = extraerLocalDate(resultSet, "fechaDeEntrega");
        double precioCalculado = resultSet.getDouble("precioCalculado");
        String estado = resultSet.getString("estado");
        
        
        return new DtoReserva(idReserva, cedulaCliente, nombreDeLaPelicula, 
        		fechaDeReserva, diasDeReserva, fechaDeEntrega, precioCalculado, estado);
    }

}
