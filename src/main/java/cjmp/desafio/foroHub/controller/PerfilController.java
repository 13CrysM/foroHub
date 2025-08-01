package cjmp.desafio.foroHub.controller;

import cjmp.desafio.foroHub.domain.perfil.DatosListadoPerfil;
import cjmp.desafio.foroHub.domain.perfil.DatosPerfil;
import cjmp.desafio.foroHub.domain.perfil.Perfil;
import cjmp.desafio.foroHub.domain.perfil.PerfilRepository;
import cjmp.desafio.foroHub.domain.usuario.DatosListadoUsuario;
import cjmp.desafio.foroHub.domain.usuario.DatosRegistroUsuario;
import cjmp.desafio.foroHub.domain.usuario.DatosRespuestaUsuario;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    // POST: Registrar perfil
    @PostMapping
    @Transactional
    public ResponseEntity<DatosPerfil> registrarPerfil(@RequestBody @Valid DatosPerfil datos) {
        Perfil perfil = new Perfil();
        perfil.setNombre_perfil(datos.nombre());
        perfilRepository.save(perfil);
        return ResponseEntity.ok(new DatosPerfil(perfil));
    }
    //GET
    @GetMapping
    public ResponseEntity<Page<DatosListadoPerfil>> listarPerfiles(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(perfilRepository.findAll(paginacion).map(DatosListadoPerfil::new));
    }
    //PUT
    //DELETE
}
