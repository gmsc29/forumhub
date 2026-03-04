package com.alura.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNot(String titulo, String mensagem, Long id);

    @Query("SELECT t FROM Topico t WHERE (:nomeCurso IS NULL OR t.curso.nome = :nomeCurso) AND (:ano IS NULL OR YEAR(t.dataCriacao) = :ano)")
    Page<Topico> buscarPorCursoEAno(@Param("nomeCurso") String nomeCurso, @Param("ano") Integer ano, Pageable paginacao);
}