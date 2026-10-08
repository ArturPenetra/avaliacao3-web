package br.edu.ifsp.prw3.oficina_api.mecanico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosMecanico(

        @NotBlank(message = "O nome do mecânico é obrigatório")
        String nome,

        @PositiveOrZero(message = "Os anos de experiência não podem ser negativos")
        Integer anosExperiencia) {
}
