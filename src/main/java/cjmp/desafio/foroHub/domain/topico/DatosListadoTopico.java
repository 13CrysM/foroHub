package cjmp.desafio.foroHub.domain.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        //LocalDateTime fecha_creacion,
        String mensaje
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje());
    }
}
