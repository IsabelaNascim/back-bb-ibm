package com.example.backbbibm.service;

import com.example.backbbibm.domain.Cliente;
import com.example.backbbibm.dto.ClienteDto;
import com.example.backbbibm.repository.ClienteRepository;
import com.example.backbbibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public Cliente insert(ClienteDto clienteDto){
        clienteDto.setId(null);
        Cliente cliente = fromDto(clienteDto);
        cliente = clienteRepo.save(cliente);
        return cliente;
    }

    public Cliente find(Integer idCliente) {
        Optional<Cliente> busca = clienteRepo.findById(idCliente);
        return busca.orElseThrow(() -> new ObjectNotFoundException(
                    "Cliente não localizado no id:"
                            + idCliente + ' '
                            + Cliente.class.getName()));
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> buscaTodos = clienteRepo.findAll();
        if (buscaTodos.isEmpty()) {
            throw new ObjectNotFoundException(
                    "Cliente não possui nenhuma ocorrência para esse cartão"
            );
        }
        return buscaTodos;
    }

    public Cliente updateCliente(Cliente cliente){
        Cliente clienteAtualizado = find(cliente.getIdCliente());
        updateData(clienteAtualizado, cliente);
        return clienteRepo.save(clienteAtualizado);
    }

    private void updateData(Cliente novo, Cliente antigo){
        novo.setNome(antigo.getNome());
        novo.setCpf(antigo.getCpf());
        novo.setTelefone(antigo.getTelefone());
    }

    public void deleteCliente(Integer idCliente){
        clienteRepo.deleteById(idCliente);
    }
    public Cliente fromDto(ClienteDto clienteDto){
        return new Cliente(
                clienteDto.getId(),
                clienteDto.getNome(),
                clienteDto.getCpf(),
                clienteDto.getTelefone()
        );
    }
}
