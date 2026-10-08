package br.edu.ifsp.prw3.oficina_api.conserto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;


public record DadosAtualizacaoConserto(

        @NotNull(message = "O id do conserto é obrigatório")
        Long id,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data de saída deve ter o formato xx/xx/xxxx")
        String dataSaida,

        @Pattern(regexp = ".*\\S.*", message = "O nome do mecânico não pode ficar em branco")
        String nomeMecanico,

        @PositiveOrZero(message = "Os anos de experiência não podem ser negativos")
        Integer anosExperienciaMecanico) {
}
