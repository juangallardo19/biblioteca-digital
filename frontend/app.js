const catalog = [
  { id: "123", title: "El Quijote", category: "Ficción", author: "Miguel de Cervantes" },
  { id: "124", title: "Cien años de soledad", category: "Ficción", author: "Gabriel García Márquez" },
  { id: "125", title: "Clean Code", category: "Tecnología", author: "Robert C. Martin" },
  { id: "126", title: "Sapiens", category: "Historia", author: "Yuval Noah Harari" },
];

function renderList(list, elementId) {
  const el = document.getElementById(elementId);
  el.innerHTML = "";
  list.forEach((b) => {
    const li = document.createElement("li");
    const left = document.createElement("span");
    left.textContent = `${b.title} — ${b.author}`;
    const right = document.createElement("span");
    right.textContent = b.category;
    li.appendChild(left);
    li.appendChild(right);
    el.appendChild(li);
  });
}

function recommend(catalog) {
  // Simulación: recomendar primeros 3
  return catalog.slice(0, 3);
}

document.addEventListener("DOMContentLoaded", () => {
  renderList(catalog, "book-list");
  document.getElementById("recommend-btn").addEventListener("click", () => {
    renderList(recommend(catalog), "recommendations");
  });
});