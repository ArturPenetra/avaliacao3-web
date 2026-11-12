package br.edu.ifsp.prw3.oficina_api.util.security;

// DTO para devolver o token JWT no corpo da resposta do login
public record DadosTokenJWT(String token) {
}
