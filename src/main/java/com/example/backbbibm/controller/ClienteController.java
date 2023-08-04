package com.example.backbbibm.controller;

import ch.qos.logback.core.net.server.Client;
import com.example.backbbibm.domain.Cliente;
import com.example.backbbibm.dto.ClienteDto;
import com.example.backbbibm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServ;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ClienteDto clienteDto){
        Cliente cliente = clienteServ.insert(clienteDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getIdCliente())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> find(@PathVariable Integer idCliente){
        Cliente cliente = clienteServ.find(idCliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = clienteServ.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> updateCliente(@RequestBody ClienteDto clienteDto, @PathVariable Integer idCliente){
        Cliente clienteAtualizado = clienteServ.fromDto(clienteDto);
        clienteAtualizado.setIdCliente(idCliente);
        clienteServ.updateCliente(clienteAtualizado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer idCliente){
        clienteServ.deleteCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}
