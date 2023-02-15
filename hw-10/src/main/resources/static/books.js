const bookApi = 'book';

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

function goToBook(id) {
  fetch(`${pathToApi}${bookApi}/${id}`, {
    method: 'GET'
  });
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

    let listOfGenres = book.genres.map(genre => {
      return genre.name;
    });

    let listOfAuthors = book.genres.map(author => {
      return author.name;
    });

    let tr = document.createElement("tr");
    let tdId = document.createElement("td");
    let tdName = document.createElement("td");
    let tdGenres = document.createElement("td");
    let tdAuthors = document.createElement("td");
    let buttonContainer = document.createElement("div");
    let buttonDelete = document.createElement("button");
    let buttonGoTo = document.createElement("button");

    tdId.textContent = book.id;
    tdName.textContent = book.name;
    tdGenres.textContent = listOfGenres;
    tdAuthors.textContent = listOfAuthors;
    buttonDelete.textContent = "delete";
    buttonDelete.onclick = function () {
      deleteBook(book.id)
    };
    buttonGoTo.textContent = "Go to book";
    buttonGoTo.onclick = function () {
    };

    buttonContainer.appendChild(buttonDelete);
    buttonContainer.appendChild(buttonGoTo);

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
