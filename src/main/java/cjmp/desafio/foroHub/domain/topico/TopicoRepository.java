package cjmp.desafio.foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository <Topico, Long> {
    Page <Topico> findAllByActivoTrue(Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findTop10ByActivoTrueOrderByFechaCreacionAsc();

    @Query("SELECT t FROM Topico t JOIN t.curso c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombreCurso, '%')) AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    Page<Topico> buscarPorCursoNombreYAño(@Param("nombreCurso") String nombreCurso, @Param("anio") int anio, Pageable pageable);


    Page<Topico> findByCursoNombreContainingIgnoreCase(String nombreCurso, Pageable pageable);

    @Query("SELECT t FROM Topico t JOIN t.curso c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombreCurso, '%')) AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    List<Topico> buscarPorCursoNombreYAño(@Param("nombreCurso") String nombreCurso, @Param("anio") int anio);
}
