package br.edu.ifsp.prw3.oficina_api.conserto;

import br.edu.ifsp.prw3.oficina_api.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.oficina_api.veiculo.DadosVeiculo;


public record DadosDetalhamentoConserto(Long id, String dataEntrada, String dataSaida,
                                        DadosMecanico mecanico, DadosVeiculo veiculo,
                                        boolean ativo) {

    public DadosDetalhamentoConserto(Conserto conserto) {
        this(conserto.getId(),
             conserto.getDataEntrada(),
             conserto.getDataSaida(),
             new DadosMecanico(conserto.getMecanico().getNome(),
                               conserto.getMecanico().getAnosExperiencia()),
             new DadosVeiculo(conserto.getVeiculo().getMarca(),
                              conserto.getVeiculo().getModelo(),
                              conserto.getVeiculo().getAno(),
                              conserto.getVeiculo().getCor()),
             conserto.isAtivo());
    }
}
