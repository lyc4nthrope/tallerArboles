package com.example.treeworkshop.controller;


import com.example.treeworkshop.dto.DatoRequest;
import com.example.treeworkshop.service.ServiceBinaryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arbol")
@CrossOrigin(origins = "*")  // Permite que el frontend se conecte sin problemas
public class TreeController {

    @Autowired
    private ServiceBinaryTree serviceBinaryTree;

    @GetMapping("/estaVacio")
    public boolean estaVacio() {
        return serviceBinaryTree.estaVacio();
    }

    @PostMapping("/agregar")
    public void agregar(@RequestBody DatoRequest request) {
        serviceBinaryTree.addRoot(request.getData());
    }

    @GetMapping("/recorrerInorden")
    public List<Integer> recorrerInorden() {
        return serviceBinaryTree.routInOrder();
    }

    // 🚨 Aquí luego agregamos más endpoints (preorden, postorden, eliminar, etc.)
}
