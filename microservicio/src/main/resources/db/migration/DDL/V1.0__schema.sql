create table reserva (
 idReserva int(11) not null auto_increment,
 cedulaCliente varchar(15) not null,
 nombreDeLaPelicula varchar(100) not null,
 fechaDeReserva datetime null,
 diasDeReserva int(1) not null,
 fechaDeEntrega datetime null,
 precioCalculado double null,
 estado varchar(20) null,
 primary key (idReserva)
);
