-- drop  database ahorcado;
create database ahorcado;
use ahorcado;

create table palabras(
	palabra varchar(50) unique
);

create table ganadores(
	nombre varchar(50),
	palabra varchar(50),
    fecha datetime
);


insert into palabras(palabra) values 
('Luz'),('Acariciar'),('Armonia'),('Alma'),('Suave'),('Musica'),('Sonrisa'),
('Imponente'),('Piedad'),('Claridad'),('Simiente'),('Pureza'),('Naturaleza'),
('Suspiro'),('Fiesta'),('Paz'),('Esperanza'),('Afinado'),('Amor'),('Atardecer'),
('Indomable'),('Ver'),('Sueño'),('Mama'),('Voladores'),('Deseo'),('Infinito'),
('Arena'),('exito'),('Peripecia'),('Jitanjafora'),('Chocolate'),('Casquivana'),
('Etopeya'),('Bailongo'),('Libertina'),('Oximoron'),('Arcano'),('Poesia'),('Tlapaleria'),
('Clavicordio'),('Universo'),('Vulva'),('Saeta'),('Espiral'),('Cine'),('Unir'),('Concordia'),
('Fe'),('Espanto'),('Bebe'),('Mexico'),('Piano'),('Sandia'),('Libelula'),('Azulado'),('Rascacielos'),('Corazon'),('Sublime'),('Susurro'),('Mirada'),('Caracol'),
('Metafora'),('Sentir'),('Inocencia'),('Nube'),('Burbuja'),('Chicozapote'),('Amaranto'),('Cienfuegos'),('Voragine'),('Melancolia'),('Onomatopeya'),('Apasionado'),('ambar'),
('Acantilado'),('Machucar'),('Fosforescencia'),('Ilusion'),('Pelele'),('Aereo'),('Imaginacion'),('Muchedumbre'),('Nomeolvides'),('Sorpresa'),('Guanabana'),('Colaborar'),('Amistad'),
('Aura'),('Pulpo'),('Lluvia'),('Felicidad'),('Intuicion'),('Ficcion'),('Utopia'),('Equidad'),('Frenesi'),('Fauna')
