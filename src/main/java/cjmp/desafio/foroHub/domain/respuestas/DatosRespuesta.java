package cjmp.desafio.foroHub.domain.respuestas;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        LocalDate fecha_creacion,
        String autor,
        Long topico_id
) {
    public DatosRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFecha_creacion(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId());
    }
}
