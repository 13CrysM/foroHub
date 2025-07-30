create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(255) not null,
    fecha_creacion datetime not null,
    activo tinyint not null,

    primary key (id)

);