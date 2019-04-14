create database if not exists TorneoFrescas;
use TorneoFrescas;
create table IF NOT EXISTS resultados(
	id int primary key auto_increment,
	nombre_ganador varchar(50) not null default '',
	bebida_en_cuerpo int not null default 0
);
        