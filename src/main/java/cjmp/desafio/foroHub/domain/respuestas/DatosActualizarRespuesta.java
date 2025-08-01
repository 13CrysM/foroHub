package cjmp.desafio.foroHub.domain.respuestas;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarRespuesta(
        @NotBlank
        String mensaje
) {
}
