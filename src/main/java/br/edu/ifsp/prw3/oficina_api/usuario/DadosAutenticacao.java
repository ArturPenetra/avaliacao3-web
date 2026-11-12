package br.edu.ifsp.prw3.oficina_api.usuario;

import jakarta.validation.constraints.NotBlank;

// Dados enviados na requisicao de login
public record DadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        String senha) {
}
