package cjmp.desafio.foroHub.domain.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long topico_id,
        @NotNull Long usuario_id,
        LocalDateTime fechaCreacion,
        @NotNull Boolean activo
        ) {
}
