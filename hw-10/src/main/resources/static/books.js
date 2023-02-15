function deleteBook(id) {
  fetch(`${pathToApi}${bookApi}/${id}`, {
    method: 'DELETE'
  }).then((response, reject) => {
    if (response.ok) {
      return response;
    }
    return Promise.reject(reject)
  })
  .then(() => window.location.reload());
}

function getAllBooks() {

  return fetch(`${pathToApi}${bookApi}`)
  .then(response => response.json());
}

function printBooks(json) {
  const bookContainer = document.getElementById('books');
  printHtmlTable(json, bookContainer);
}

async function initBooks() {
  let json = await getAllBooks();
  printBooks(json);
}

function printHtmlTable(json, tableContainer) {

  json.forEach(function (book) {

    let listOfGenres = getGenresFromBook(book);
    let listOfAuthors = getAuthorsFromBook(book);

    let tr = document.createElement("tr");
    let tdId = document.createElement("td");
    let tdName = document.createElement("td");
    let tdGenres = document.createElement("td");
    let tdAuthors = document.createElement("td");
    let buttonContainer = document.createElement("div");
    let buttonDelete = document.createElement("button");

    tdId.textContent = book.id;
    tdName.textContent = book.name;
    tdGenres.textContent = listOfGenres;
    tdAuthors.textContent = listOfAuthors;
    buttonDelete.textContent = "delete";
    buttonDelete.onclick = function () {
      deleteBook(book.id)
    };

    buttonContainer.appendChild(buttonDelete);

    tr.appendChild(tdId);
    tr.appendChild(tdName);
    tr.appendChild(tdGenres);
    tr.appendChild(tdAuthors);
    tr.appendChild(buttonContainer);

    tableContainer
    .getElementsByTagName('tbody')[0]
    .appendChild(tr)
  });

}
