package com.example.produto_api.mapper;

import com.example.produto_api.dto.request.ClienteRequest;
import com.example.produto_api.dto.response.ClienteResponse;
import com.example.produto_api.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .endereco(request.getEndereco())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .endereco(cliente.getEndereco())
                .build();
    }

}
