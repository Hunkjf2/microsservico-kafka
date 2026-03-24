package com.example.produto_api.service;

import com.example.produto_api.dto.request.ClienteRequest;
import com.example.produto_api.dto.response.ClienteResponse;
import com.example.produto_api.entity.Cliente;
import com.example.produto_api.mapper.ClienteMapper;
import com.example.produto_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        log.info("Criando cliente com email: {}", request.getEmail());

        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException(request.getEmail());
        }

        Cliente cliente = clienteMapper.toEntity(request);

        return clienteMapper.toResponse(salvar(cliente));
    }

    private Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Long id) {
        log.info("Buscando cliente por ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return clienteMapper.toResponse(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setEndereco(request.getEndereco());

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }
}
