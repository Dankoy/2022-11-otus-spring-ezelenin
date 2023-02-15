function getGenresFromBook(book) {
  return book.genres.map(genre => {
    return genre.name;
  })
}

function getAuthorsFromBook(book) {
  return book.genres.map(author => {
    return author.name;
  })
}

function getCommentariesFromBook(book) {

  if (typeof book.commentaries !== 'undefined') {
    return book.commentaries.map(com => {
      return com.text;
    })
  } else {
    return [];
  }

}
