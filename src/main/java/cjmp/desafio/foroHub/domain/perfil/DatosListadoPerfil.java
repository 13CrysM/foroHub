package cjmp.desafio.foroHub.domain.perfil;

public record DatosListadoPerfil(long id, String nombre) {
    public DatosListadoPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre());
    }
}
