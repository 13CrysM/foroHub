package cjmp.desafio.foroHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje
) {
}
