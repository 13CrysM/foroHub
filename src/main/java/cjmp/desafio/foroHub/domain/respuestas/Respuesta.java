package cjmp.desafio.foroHub.domain.respuestas;


import cjmp.desafio.foroHub.domain.topico.Topico;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "activo")
    private Boolean solucion = false;

    public void marcarComoSolucion() {
        this.solucion = true;
    }

    public void actualizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}
