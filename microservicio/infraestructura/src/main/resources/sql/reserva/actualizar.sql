update reserva
set  cedulaCliente =:cedulaCliente,
 nombreDeLaPelicula =:nombreDeLaPelicula,
 fechaDeReserva =:fechaDeReserva,
 diasDeReserva =:diasDeReserva,
 fechaDeEntrega =:fechaDeEntrega,
 precioCalculado =:precioCalculado,
 estado = :estado
where idReserva = :idReserva