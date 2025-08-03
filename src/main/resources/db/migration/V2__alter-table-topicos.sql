ALTER TABLE topicos MODIFY titulo VARCHAR(255) NOT NULL UNIQUE;
ALTER TABLE topicos MODIFY mensaje VARCHAR(255) NOT NULL UNIQUE;

ALTER TABLE topicos ADD COLUMN usuario_id bigint;
ALTER TABLE topicos ADD COLUMN curso_id bigint;
ALTER TABLE topicos ADD COLUMN respuesta_id bigint;