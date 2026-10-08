package br.edu.ifsp.prw3.oficina_api.conserto;

import br.edu.ifsp.prw3.oficina_api.mecanico.DadosMecanico;
import br.edu.ifsp.prw3.oficina_api.veiculo.DadosVeiculo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConserto(

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data de entrada deve ter o formato xx/xx/xxxx")
        String dataEntrada,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data de saída deve ter o formato xx/xx/xxxx")
        String dataSaida,

        @NotNull(message = "Os dados do mecânico são obrigatórios")
        @Valid
        DadosMecanico mecanico,

        @NotNull(message = "Os dados do veículo são obrigatórios")
        @Valid
        DadosVeiculo veiculo) {
}
