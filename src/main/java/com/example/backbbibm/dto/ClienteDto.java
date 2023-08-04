package com.example.backbbibm.dto;

import com.example.backbbibm.domain.Cartao;
import com.example.backbbibm.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.security.PrivateKey;

@NoArgsConstructor
@Getter
@Setter
public class ClienteDto implements Serializable {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    //private Cartao cartao;

    public ClienteDto(Cliente cliente){
        id = cliente.getIdCliente();
        nome = cliente.getNome();
        cpf = cliente.getCpf();
        telefone = cliente.getTelefone();
//        cartao = cl
    }
}
