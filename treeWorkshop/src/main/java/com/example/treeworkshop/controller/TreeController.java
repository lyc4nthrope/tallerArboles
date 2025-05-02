package com.example.treeworkshop.controller;

import com.example.treeworkshop.model.BinaryTree;
import com.example.treeworkshop.model.Node;
import com.example.treeworkshop.service.ServiceBinaryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arbol")
@CrossOrigin(origins = "*") // Permite que el frontend se conecte sin bloqueos
public class TreeController {
    private final BinaryTree tree = new  BinaryTree();

    @Autowired
    private ServiceBinaryTree serviceBinaryTree;

    //para el dibujo pero no sirve todavia toca mirar el html
    @GetMapping("/estructura")
    public ResponseEntity<Node> getStructure() {
        Node root = tree.root;
        return ResponseEntity.ok(root);
    }

    // 1. verifica si esta vacio el arbol
    @GetMapping("/estaVacio")
    public boolean isEmpty() {
        return serviceBinaryTree.isEmpty();
    }

    // 2. Agregar dato al arbol
    @PostMapping("/agregar")
    public void addRoot(@RequestParam int dato) {
        serviceBinaryTree.addRoot(dato);
    }

    // 3. Inorden
    @GetMapping("/inorden")
    public List<Integer> inOrderRout() {
        return serviceBinaryTree.inOrderRout();
    }

    // 4. Preorden
    @GetMapping("/preorden")
    public List<Integer> preOrderRout() {
        return serviceBinaryTree.preOrderRout();
    }

    // 5. Postorden
    @GetMapping("/postorden")
    public List<Integer> postOrderRout() {
        return serviceBinaryTree.postOrderRout();
    }

    // 6. verifica si esta el dato
    @GetMapping("/existe")
    public boolean exist(@RequestParam int dato) {
        return serviceBinaryTree.exist(dato);
    }

    // 7. Obtener peso del arbol, osea todos los nodos
    @GetMapping("/peso")
    public int weight() {
        return serviceBinaryTree.getWeight();
    }

    // 8. Obtener altura del arbol
    @GetMapping("/altura")
    public int heigth() {
        return serviceBinaryTree.getHeigth();
    }

    // 9. Obtener nivel de un dato en especifico
    @GetMapping("/nivel")
    public int level(@RequestParam int dato) {
        return serviceBinaryTree.getLevel(dato);
    }

    // 10. Contar las hojas del arbol
    @GetMapping("/contarHojas")
    public int countLeaves() {
        return serviceBinaryTree.countLeaves();
    }

    // 11. Obtener menor dato de una raiz
    @GetMapping("/menor")
    public Integer lower() {
        return serviceBinaryTree.getLower();
    }

    // 12. Obtener mayor dato de una raiz
    @GetMapping("/mayor")
    public Integer higher() {
        return serviceBinaryTree.getHigher();
    }

    // 13. Imprimir amplitud osea por niveles  0 1 2 3
    @GetMapping("/amplitud")
    public List<Integer> amplitude() {
        return serviceBinaryTree.printAmplitude();
    }

    // 14. Eliminar dato del arbol
    @DeleteMapping("/eliminar")
    public void eliminate(@RequestParam int dato) {
        serviceBinaryTree.eliminate(dato);
    }

    // 15. Borrar completo el arbol
    @DeleteMapping("/borrar")
    public void eliminateTree() {
        serviceBinaryTree.eliminateTree();
    }
}
