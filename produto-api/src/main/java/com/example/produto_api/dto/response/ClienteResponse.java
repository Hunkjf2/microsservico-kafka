package com.example.produto_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
}
