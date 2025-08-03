package cjmp.desafio.foroHub.domain.usuario;

import cjmp.desafio.foroHub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "usuariosPerfiles",
//            joinColumns = @JoinColumn(name = "usuarioId"),
//            inverseJoinColumns = @JoinColumn(name = "perfilId"))
//    private Set<Perfil> perfiles = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    private Boolean activo = true;

    public void actualizar(DatosActualizarUsuario datos) {
        if (datos.nombre() != null) this.nombre = datos.nombre();
//        if (datos.email() != null) this.email = datos.email();
//        if (datos.clave() != null) this.clave = datos.clave();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE.USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
