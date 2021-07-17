select idReserva,
 cedulaCliente,
 nombreDeLaPelicula,
 fechaDeReserva,
 diasDeReserva,
 fechaDeEntrega,
 precioCalculado
from reserva
where cedulaCliente=:cedulaCliente