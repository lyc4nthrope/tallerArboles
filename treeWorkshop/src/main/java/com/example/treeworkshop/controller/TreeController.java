package com.example.treeworkshop.controller;

import com.example.treeworkshop.service.ServiceBinaryTree;
import com.example.treeworkshop.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arbol")
@CrossOrigin(origins = "*")
public class TreeController {

    @Autowired
    private ServiceBinaryTree serviceBinaryTree;

    @GetMapping("/estructura")
    public ResponseEntity<TreeNode> obtenerEstructura() {
        return ResponseEntity.ok(serviceBinaryTree.getRoot());
    }

    @GetMapping("/estaVacio")
    public ResponseEntity<Boolean> estaVacio() {
        return ResponseEntity.ok(serviceBinaryTree.isEmpty());
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregar(@RequestParam int dato) {
        serviceBinaryTree.addRoot(dato);
        return ResponseEntity.ok("Dato agregado exitosamente.");
    }

    @GetMapping("/inorden")
    public ResponseEntity<List<Integer>> inorden() {
        return ResponseEntity.ok(serviceBinaryTree.inOrderRout());
    }

    @GetMapping("/preorden")
    public ResponseEntity<List<Integer>> preorden() {
        return ResponseEntity.ok(serviceBinaryTree.preOrderRout());
    }

    @GetMapping("/postorden")
    public ResponseEntity<List<Integer>> postorden() {
        return ResponseEntity.ok(serviceBinaryTree.postOrderRout());
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existe(@RequestParam int dato) {
        return ResponseEntity.ok(serviceBinaryTree.exist(dato));
    }

    @GetMapping("/peso")
    public ResponseEntity<Integer> peso() {
        return ResponseEntity.ok(serviceBinaryTree.getWeight());
    }

    @GetMapping("/altura")
    public ResponseEntity<Integer> altura() {
        return ResponseEntity.ok(serviceBinaryTree.getHeight());
    }

    @GetMapping("/nivel")
    public ResponseEntity<Integer> nivel(@RequestParam int dato) {
        return ResponseEntity.ok(serviceBinaryTree.getLevel(dato));
    }

    @GetMapping("/contarHojas")
    public ResponseEntity<Integer> contarHojas() {
        return ResponseEntity.ok(serviceBinaryTree.countLeaves());
    }

    @GetMapping("/menor")
    public ResponseEntity<Integer> menor() {
        return ResponseEntity.ok(serviceBinaryTree.getLower());
    }

    @GetMapping("/mayor")
    public ResponseEntity<Integer> mayor() {
        return ResponseEntity.ok(serviceBinaryTree.getHigher());
    }

    @GetMapping("/amplitud")
    public ResponseEntity<List<Integer>> amplitud() {
        return ResponseEntity.ok(serviceBinaryTree.printAmplitude());
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam int dato) {
        serviceBinaryTree.eliminate(dato);
        return ResponseEntity.ok("Dato eliminado exitosamente.");
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> borrar() {
        serviceBinaryTree.eliminateTree();
        return ResponseEntity.ok("Árbol eliminado completamente.");
    }
}
