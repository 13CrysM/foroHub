package cjmp.desafio.foroHub.domain.topico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean activo
) {
}
