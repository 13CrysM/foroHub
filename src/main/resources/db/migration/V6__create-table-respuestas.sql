create table respuestas(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje varchar (255) not null,
    topico_id bigint not null,
    fecha datetime not null,
    usuario_id bigint not null,
    activo tinyint not null,

    constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id),
    constraint fk_respuestas_usuario_id foreign key (usuario_id) references usuarios(id)
);