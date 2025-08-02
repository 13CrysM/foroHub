package cjmp.desafio.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean activo,
        String usuario,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getActivo(), topico.getUsuario().getNombre(),topico.getCurso().getNombre());
    }
}
