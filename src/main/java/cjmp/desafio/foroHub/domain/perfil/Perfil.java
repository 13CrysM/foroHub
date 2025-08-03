package cjmp.desafio.foroHub.domain.perfil;

import cjmp.desafio.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre_perfil")
    private String nombre;
//    @ManyToMany(mappedBy = "perfiles")
//    private Set<Usuario> usuarios = new HashSet<>();
    @OneToMany
    private Set<Usuario> usuarios = new HashSet<>();

    public Perfil (DatosPerfil datosPerfil) {
        this.id = datosPerfil.id();
        this.nombre = datosPerfil.nombre();
    }
}
