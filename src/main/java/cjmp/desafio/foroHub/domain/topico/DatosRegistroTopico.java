package cjmp.desafio.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Boolean activo,
        @NotNull
        Long usuario_id,
        @NotNull
        Long curso_id
) {
}
