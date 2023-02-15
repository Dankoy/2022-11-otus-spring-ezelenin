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

    tdId.textContent = book.id;
    tdName.textContent = book.name;
    tdGenres.textContent = listOfGenres;
    tdAuthors.textContent = listOfAuthors;

    tr.appendChild(tdId);
    tr.appendChild(tdName);
    tr.appendChild(tdGenres);
    tr.appendChild(tdAuthors);

    tableContainer
    .getElementsByTagName('tbody')[0]
    .appendChild(tr)
  });

}
