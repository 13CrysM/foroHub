package cjmp.desafio.foroHub.domain.respuestas;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Long topico_id
) {
    public DatosRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getId());
    }
}
