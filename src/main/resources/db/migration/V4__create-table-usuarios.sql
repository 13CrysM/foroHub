create table usuarios(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar (100) not null,
    email varchar(100) not null unique,
    clave varchar(255) not null,
    perfil_id bigint NOT NULL,

    constraint fk_usuarios_perfil_id foreign key (perfil_id) references perfiles(id)

);