const apiURL = "http://localhost:8080/arbol";

const input = document.getElementById("inputData");
const resultDiv = document.getElementById("result");

function getInputValue() {
    const value = parseInt(input.value);
    input.value = ""; // Clear input
    return value;
}

function showResult(message) {
    resultDiv.textContent = message;
}

function addRoot() {
    const data = getInputValue();
    fetch(`${apiURL}/agregar?dato=${data}`, { method: 'POST' })
        .then(() => {
            showResult(`Dato ${data} añadido correctamente.`);
            fetchTree(); // Actualizar visualización
        })
        .catch(() => showResult("Error al añadir el dato."));
}

function eliminate() {
    const data = getInputValue();
    fetch(`${apiURL}/eliminar?dato=${data}`, { method: 'DELETE' })
        .then(() => {
            showResult(`Dato ${data} eliminado correctamente.`);
            fetchTree(); // Actualizar visualización
        })
        .catch(() => showResult("Error al eliminar el dato."));
}

function isEmpty() {
    fetch(`${apiURL}/estaVacio`)
        .then(response => response.json())
        .then(isEmpty => {
            showResult(isEmpty ? "El árbol está vacío." : "El árbol no está vacío.");
        });
}

function exist() {
    const data = getInputValue();
    fetch(`${apiURL}/existe?dato=${data}`)
        .then(response => response.json())
        .then(exists => {
            showResult(exists ? `Dato ${data} existe en el árbol.` : `Dato ${data} no existe en el árbol.`);
        });
}

function inOrderRout() {
    fetch(`${apiURL}/inorden`)
        .then(response => response.json())
        .then(data => {
            showResult(`Inorden: ${data.join(", ")}`);
        });
}

function preOrderRout() {
    fetch(`${apiURL}/preorden`)
        .then(response => response.json())
        .then(data => {
            showResult(`Preorden: ${data.join(", ")}`);
        });
}

function postOrderRout() {
    fetch(`${apiURL}/postorden`)
        .then(response => response.json())
        .then(data => {
            showResult(`Postorden: ${data.join(", ")}`);
        });
}

function weight() {
    fetch(`${apiURL}/peso`)
        .then(response => response.json())
        .then(data => {
            showResult(`Peso del árbol: ${data}`);
        });
}

function heigth() {
    fetch(`${apiURL}/altura`)
        .then(response => response.json())
        .then(data => {
            showResult(`Altura del árbol: ${data}`);
        });
}

function level() {
    const data = getInputValue();
    fetch(`${apiURL}/nivel?dato=${data}`)
        .then(response => response.json())
        .then(level => {
            showResult(`Dato ${data} está en el nivel: ${level}.`);
        });
}

function countLeaves() {
    fetch(`${apiURL}/contarHojas`)
        .then(response => response.json())
        .then(data => {
            showResult(`Número de hojas: ${data}`);
        });
}

function lower() {
    fetch(`${apiURL}/menor`)
        .then(response => response.json())
        .then(data => {
            showResult(`Valor mínimo: ${data}`);
        });
}

function higher() {
    fetch(`${apiURL}/mayor`)
        .then(response => response.json())
        .then(data => {
            showResult(`Valor máximo: ${data}`);
        });
}

function amplitude() {
    fetch(`${apiURL}/amplitud`)
        .then(response => response.json())
        .then(data => {
            showResult(`Amplitud: ${data.join(", ")}`);
        });
}

function eliminateTree() {
    fetch(`${apiURL}/borrar`, { method: 'DELETE' })
        .then(() => {
            showResult("Se eliminó todo el árbol.");
            fetchTree(); // Actualizar visualización
        })
        .catch(() => showResult("Error al eliminar el árbol."));
}

// --------------------- VISUALIZACIÓN DEL ÁRBOL ----------------------

function transformToTreant(node) {
    if (!node) return null;

    const current = {
        text: {
            name: node.data.toString()
        },
        children: []
    };

    if (node.left) {
        current.children.push(transformToTreant(node.left));
    }
    if (node.right) {
        current.children.push(transformToTreant(node.right));
    }

    return current;
}

function renderTree(treeData) {
    const treeContainer = document.getElementById("tree-container");
    treeContainer.innerHTML = ""; // limpiar

    if (!treeData) {
        treeContainer.innerHTML = "<p class='text-center'>El árbol está vacío.</p>";
        return;
    }

    const treantData = {
        chart: {
            container: "#tree-container",
            node: { collapsable: true },
            connectors: { type: "step" },
            animation: { nodeAnimation: "easeOutBounce", nodeSpeed: 700, connectorsAnimation: "bounce", connectorsSpeed: 700 }
        },
        nodeStructure: transformToTreant(treeData)
    };

    new Treant(treantData);
}

function fetchTree() {
    fetch(`${apiURL}/estructuraCompleta`)
        .then(response => response.json())
        .then(data => {
            console.log("Estructura completa:", data);
            renderTree(data.root); // Solo renderiza el árbol
        });
}



// Cargar visualización inicial
fetchTree();
