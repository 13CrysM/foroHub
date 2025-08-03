package cjmp.desafio.foroHub.controller;

import cjmp.desafio.foroHub.domain.respuestas.*;
import cjmp.desafio.foroHub.domain.topico.Topico;
import cjmp.desafio.foroHub.domain.topico.TopicoRepository;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import cjmp.desafio.foroHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("respuestas")
public class RespuestaController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos) {
        Topico topico = topicoRepository.getReferenceById(datos.topico_id());
        Usuario usuario = usuarioRepository.getReferenceById(datos.usuario_id());

        respuestaService.registrarRespuesta(datos, topico, usuario);

        return ResponseEntity.ok("Respuesta registrada");
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuesta>> listarRespuestas(@RequestParam(required = false) Long topicoId) {
        var respuestas = respuestaService.obtenerRespuestas(topicoId);
        var datos = respuestas.stream().map(DatosRespuesta::new).toList();
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datos) {
        respuestaService.actualizarRespuesta(id, datos.mensaje());
        return ResponseEntity.ok("Respuesta actualizada.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLogicamente(@PathVariable Long id) {
        respuestaService.marcarComoSolucion(id);
        return ResponseEntity.ok("Respuesta marcada como solución.");
    }
//
//    // GET: Listar todas las respuestas o filtrar por idTopico
//    @GetMapping
//    public ResponseEntity<List<DatosRespuesta>> listarRespuestas(@RequestParam(required = false) Long topicoId) {
//        List<Respuesta> respuestas;
//        if (topicoId != null) {
//            respuestas = respuestaRepository.findByTopicoIdAndSolucionFalse(topicoId);
//        } else {
//            respuestas = respuestaRepository.findBySolucionFalse();
//        }
//
//        var datos = respuestas.stream().map(DatosRespuesta::new).toList();
//        return ResponseEntity.ok(datos);
//    }
//    // PUT: Actualizar el mensaje de una respuesta
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<String> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datos) {
//        Respuesta respuesta = respuestaRepository.getReferenceById(id);
//        respuesta.actualizarMensaje(datos.mensaje());
//        return ResponseEntity.ok("Respuesta actualizada.");
//    }
//
//    // DELETE: Marcar la respuesta como solución (eliminado lógico)
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<String> eliminarLogicamente(@PathVariable Long id) {
//        Respuesta respuesta = respuestaRepository.getReferenceById(id);
//        respuesta.marcarComoSolucion();
//        return ResponseEntity.ok("Respuesta marcada como solución.");
//    }

}
