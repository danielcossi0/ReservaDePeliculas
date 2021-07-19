select idReserva,
 cedulaCliente,
 nombreDeLaPelicula,
 fechaDeReserva,
 diasDeReserva,
 fechaDeEntrega,
 precioCalculado,
 estado
from reserva
where cedulaCliente=:cedulaCliente