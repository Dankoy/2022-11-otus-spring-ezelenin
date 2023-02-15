function getBookById() {
  const idInput = document.getElementById("fid")
  const bookId = idInput.value

  return fetch(`${pathToApi}${bookApi}/${bookId}`)
  .then(response => response.json());
}

function printBook(json) {
  const bookContainer = document.getElementById('book');
  makeTableForBook(json, bookContainer);
}

async function initBook() {

  let json = await getBookById();
  printBook(json);
}

function makeTableForBook(book, tableContainer) {

  let listOfGenres = getGenresFromBook(book);
  let listOfAuthors = getAuthorsFromBook(book);
  let listOfCommentaries = getCommentariesFromBook(book);

  console.log(listOfCommentaries);

  let tr = document.createElement("tr");
  let tdId = document.createElement("td");
  let tdName = document.createElement("td");
  let tdGenres = document.createElement("td");
  let tdAuthors = document.createElement("td");
  let tdCommentaries = document.createElement("td");

  tdId.textContent = book.id;
  tdName.textContent = book.name;
  tdGenres.textContent = listOfGenres;
  tdAuthors.textContent = listOfAuthors;
  tdCommentaries.textContent = listOfCommentaries;

  tr.appendChild(tdId);
  tr.appendChild(tdName);
  tr.appendChild(tdGenres);
  tr.appendChild(tdAuthors);
  tr.appendChild(tdCommentaries);

  let tbody = tableContainer.getElementsByTagName('tbody')[0];
  tbody.innerHTML = "";
  tbody.appendChild(tr);

}
