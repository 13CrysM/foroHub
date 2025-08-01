package cjmp.desafio.foroHub.domain.perfil;

public record DatosPerfil(Long id, String nombre) {
    public DatosPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre_perfil());
    }
}
