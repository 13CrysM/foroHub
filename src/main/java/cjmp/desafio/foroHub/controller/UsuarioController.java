package cjmp.desafio.foroHub.controller;

import cjmp.desafio.foroHub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // POST: Registrar usuario
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        Usuario usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setClave(datos.clave());
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    // GET: Listar usuarios activos
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 10, sort = "nombre")Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllByActivoTrue(paginacion).map(DatosListadoUsuario::new));
    }

    // PUT: Actualizar usuario
    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datos) {
        Usuario usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizar(datos);
        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    // DELETE lógico: cambiar campo activo a false
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setActivo(false);
        return ResponseEntity.ok("Usuario eliminado lógicamente.");
    }
}
