update reserva
set  cedulaCliente =:cedulaCliente,
 nombreDeLaPelicula =:nombreDeLaPelicula,
 fechaDeReserva =:fechaDeReserva,
 diasDeReserva =:diasDeReserva,
 fechaDeEntrega =:fechaDeEntrega,
 precioCalculado =:precioCalculado
where idReserva = :idReserva