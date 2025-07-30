package cjmp.desafio.foroHub.domain.topico;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fecha_creacion,
        Boolean activo
) {
}
