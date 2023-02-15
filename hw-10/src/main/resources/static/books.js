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
