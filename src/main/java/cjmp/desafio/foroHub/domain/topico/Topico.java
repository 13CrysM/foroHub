package cjmp.desafio.foroHub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

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
    private String mensaje;
    private LocalDate fecha_creacion;
    private Boolean activo;

//    private String usuario;
//    private String curso;
//    private String respuestas;
    public Topico(@Valid DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha_creacion = datosRegistroTopico.fecha_creacion();
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
}
