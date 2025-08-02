package cjmp.desafio.foroHub.controller;

import cjmp.desafio.foroHub.domain.curso.Curso;
import cjmp.desafio.foroHub.domain.curso.CursoRepository;
import cjmp.desafio.foroHub.domain.curso.DatosRegistroCurso;
import cjmp.desafio.foroHub.domain.usuario.DatosRegistroUsuario;
import cjmp.desafio.foroHub.domain.usuario.DatosRespuestaUsuario;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRegistroCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos) {
        Curso curso = new Curso();
        curso.setNombre(datos.nombre());
        curso.setCategoria(datos.categoria());
        cursoRepository.save(curso);
        return ResponseEntity.ok(new DatosRegistroCurso(curso));
    }

}
