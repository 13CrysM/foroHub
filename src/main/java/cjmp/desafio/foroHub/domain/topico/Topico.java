package cjmp.desafio.foroHub.domain.topico;

import cjmp.desafio.foroHub.domain.curso.Curso;
import cjmp.desafio.foroHub.domain.respuestas.DatosRegistroRespuesta;
import cjmp.desafio.foroHub.domain.respuestas.Respuesta;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean activo;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;


    public Topico(@Valid DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
        this.activo = datosRegistroTopico.activo();
    }

    public void desactivarTopico() {
        this.activo = false;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }

    public void agregarRespuesta(DatosRegistroRespuesta datos, Usuario autor) {
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setUsuario(autor);
        respuesta.setTopico(this);

        this.respuestas.add(respuesta);
    }

}
