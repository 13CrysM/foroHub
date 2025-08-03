package cjmp.desafio.foroHub.controller;

import cjmp.desafio.foroHub.domain.curso.Curso;
import cjmp.desafio.foroHub.domain.curso.CursoRepository;
import cjmp.desafio.foroHub.domain.respuestas.DatosRegistroRespuesta;
import cjmp.desafio.foroHub.domain.topico.*;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import cjmp.desafio.foroHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());
        if (existeDuplicado) {
            return ResponseEntity.status(409).build();
        }
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.usuario_id());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.curso_id());

        Topico topico = new Topico(datosRegistroTopico);
        topico.setUsuario(usuario);
        topico.setCurso(curso);
        topicoRepository.save(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getActivo()));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<DatosListadoTopico>> top10Topicos() {
        List<Topico> topicos = topicoRepository.findTop10ByActivoTrueOrderByFechaCreacionAsc();
        return ResponseEntity.ok(topicos.stream().map(DatosListadoTopico::new).toList());
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAllByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }
    @GetMapping("/buscar")
    public ResponseEntity<Page<DatosListadoTopico>> buscarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<Topico> topicos;

        if (curso != null && !curso.isEmpty()) {
            if (anio != null) {
                topicos = topicoRepository.buscarPorCursoNombreYAño(curso, anio, paginacion);
            } else {
                topicos = topicoRepository.findByCursoNombreContainingIgnoreCase(curso, paginacion);
            }
        } else {
            topicos = topicoRepository.findAllByActivoTrue(paginacion);
        }

        return ResponseEntity.ok(topicos.map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallarTopico(@PathVariable Long id){
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no se encuentra"));
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var topico = topicoBuscado.get();
        topico.actualizarDatos(datosActualizarTopico);
        var respuesta =  new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getActivo());
        return ResponseEntity.ok(respuesta);
        }

    // DELETE LÓGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();

    }

//    @PostMapping("/respuesta")
//    @Transactional
//    public ResponseEntity<String> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos) {
//        Topico topico = topicoRepository.getReferenceById(datos.topico_id());
//        Usuario autor = usuarioRepository.getReferenceById(datos.usuario_id());
//
//        topico.agregarRespuesta(datos, autor);
//
//        return ResponseEntity.ok("Respuesta registrada correctamente.");
//    }

}