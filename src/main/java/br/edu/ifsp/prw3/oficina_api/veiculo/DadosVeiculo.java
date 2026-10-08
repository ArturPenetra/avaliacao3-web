package br.edu.ifsp.prw3.oficina_api.veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosVeiculo(

        @NotBlank(message = "A marca do veículo é obrigatória")
        String marca,

        @NotBlank(message = "O modelo do veículo é obrigatório")
        String modelo,

        @NotBlank(message = "O ano do veículo é obrigatório")
        @Pattern(regexp = "\\d{4}", message = "O ano do veículo deve ter o formato xxxx")
        String ano,

        String cor) {   // opcional
}
