package cjmp.desafio.foroHub.domain.respuestas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository <Respuesta, Long>{

    List<Respuesta> findByTopicoIdAndSolucionFalse(Long topicoId);

    List<Respuesta> findBySolucionFalse();
}
