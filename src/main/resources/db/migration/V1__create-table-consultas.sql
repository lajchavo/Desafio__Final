create table consultas (
id int not null auto_increment,
id_Usuario varchar (100) not null ,
titulo varchar(100),
nombre_Curso varchar(100),
mensaje varchar(200) not null unique,
fecha TIMESTAMP,



primary key (id)

);