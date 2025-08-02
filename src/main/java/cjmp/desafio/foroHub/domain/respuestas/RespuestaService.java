package cjmp.desafio.foroHub.domain.respuestas;

import cjmp.desafio.foroHub.domain.topico.Topico;
import cjmp.desafio.foroHub.domain.topico.TopicoRepository;
import cjmp.desafio.foroHub.domain.usuario.Usuario;
import cjmp.desafio.foroHub.domain.usuario.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void registrarRespuesta(DatosRegistroRespuesta datos, Topico topico, Usuario usuario) {
//        Topico topico = topicoRepository.getReferenceById(datos.topico_id());
//        Usuario usuario = usuarioRepository.getReferenceById(datos.usuario_id());

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setUsuario(usuario);
        respuesta.setTopico(topico);
        respuesta.setSolucion(false);

        respuestaRepository.save(respuesta);
    }

    public List<Respuesta> obtenerRespuestas(Long topicoId) {
        if (topicoId != null) {
            return respuestaRepository.findByTopicoIdAndSolucionFalse(topicoId);
        } else {
            return respuestaRepository.findBySolucionFalse();
        }
    }

    @Transactional
    public void actualizarRespuesta(Long id, String nuevoMensaje) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarMensaje(nuevoMensaje);
    }

    @Transactional
    public void marcarComoSolucion(Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.marcarComoSolucion();
    }
}
