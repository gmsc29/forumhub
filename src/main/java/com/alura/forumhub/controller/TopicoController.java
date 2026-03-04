package com.alura.forumhub.controller;

import com.alura.forumhub.domain.curso.CursoRepository;
import com.alura.forumhub.domain.topico.*;
import com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Tópico já existente com este título e mensagem.");
        }

        var autor = usuarioRepository.getReferenceById(dados.autorId());
        var curso = cursoRepository.getReferenceById(dados.cursoId());

        var topico = new Topico(dados, autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @RequestParam(required = false) String nomeCurso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {

        var page = topicoRepository.buscarPorCursoEAno(nomeCurso, ano, paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            if (topicoRepository.existsByTituloAndMensagemAndIdNot(dados.titulo(), dados.mensagem(), id)) {
                return ResponseEntity.badRequest().body("Tópico já existente com este título e mensagem.");
            }

            var topico = topicoOptional.get();
            topico.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}