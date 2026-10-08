package br.edu.ifsp.prw3.oficina_api.controller;

import br.edu.ifsp.prw3.oficina_api.conserto.Conserto;
import br.edu.ifsp.prw3.oficina_api.conserto.ConsertoRepository;
import br.edu.ifsp.prw3.oficina_api.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3.oficina_api.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3.oficina_api.conserto.DadosDetalhamentoConserto;
import br.edu.ifsp.prw3.oficina_api.conserto.DadosListagemConserto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> cadastrar(
            @RequestBody @Valid DadosCadastroConserto dados,
            UriComponentsBuilder uriBuilder) {

        var conserto = new Conserto(dados);
        repository.save(conserto);

        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConserto(conserto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConserto>> listar(Pageable paginacao) {

        var pagina = repository.findAll(paginacao).map(DadosDetalhamentoConserto::new);
        return ResponseEntity.ok(pagina);
    }


    @GetMapping("/algunsdados")
    public ResponseEntity<List<DadosListagemConserto>> listarAlgunsDados() {

        var lista = repository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemConserto::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConserto> detalhar(@PathVariable Long id) {

        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            return ResponseEntity.ok(new DadosDetalhamentoConserto(consertoOptional.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> atualizar(
            @RequestBody @Valid DadosAtualizacaoConserto dados) {

        Optional<Conserto> consertoOptional = repository.findById(dados.id());

        if (consertoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Conserto conserto = consertoOptional.get();
        conserto.atualizarInformacoes(dados);
        // Nao precisa chamar save(): dentro da transacao a JPA atualiza o BD.
        return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Optional<Conserto> consertoOptional = repository.findById(id);

        if (consertoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        consertoOptional.get().excluir();
        return ResponseEntity.noContent().build();
    }
}
