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
        .then(() => showResult(`Dato ${data} añadido correctamente.`))
        .catch(() => showResult("Error al añadir el dato."));
}

function eliminate() {
    const data = getInputValue();
    fetch(`${apiURL}/eliminar?dato=${data}`, { method: 'DELETE' })
        .then(() => showResult(`Dato ${data} eliminado correctamente.`))
        .catch(() => showResult("Error al eliminar el dato."));
}

function isEmpty() {
    fetch(`${apiURL}/estaVacio`)
        .then(response => response.json())
        .then(isEmpty => {
            showResult(isEmpty ? "El árbol esta vacio." : "El árbol no esta vacio.");
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
            showResult(`Dato ${data} esta en el nivel:  ${level}.`);
        });
}

function countLeaves() {
    fetch(`${apiURL}/contarHojas`)
        .then(response => response.json())
        .then(data => {
            showResult(`Numero de hojas: ${data}`);
        });
}

function lower() {
    fetch(`${apiURL}/menor`)
        .then(response => response.json())
        .then(data => {
            showResult(`Valor minimo: ${data}`);
        });
}

function higher() {
    fetch(`${apiURL}/mayor`)
        .then(response => response.json())
        .then(data => {
            showResult(`Valor maximo: ${data}`);
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
        .then(() => showResult("Se eliminó todo el árbol.."))
        .catch(() => showResult("Error al eliminar el árbol."));
}
