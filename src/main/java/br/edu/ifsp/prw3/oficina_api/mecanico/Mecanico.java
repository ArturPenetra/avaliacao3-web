package br.edu.ifsp.prw3.oficina_api.mecanico;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    @Column(name = "mecanico_nome")
    private String nome;

    @Column(name = "mecanico_anos_experiencia")
    private Integer anosExperiencia;

    public Mecanico(DadosMecanico dados) {
        this.nome = dados.nome();
        this.anosExperiencia = dados.anosExperiencia();
    }

    public void atualizarInformacoes(String nome, Integer anosExperiencia) {
        if (nome != null) {
            this.nome = nome;
        }
        if (anosExperiencia != null) {
            this.anosExperiencia = anosExperiencia;
        }
    }
}
