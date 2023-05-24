import React, {useEffect, useState} from 'react'
import {apiPath, workPath} from "../context";
import {styles} from "../styles/styles";
import {Book} from "./Book";

export const Header = (props) => (
    <h1>{props.title}</h1>
);

export const Books = (props) => {

  const {title} = props;
  const [books, setBooks] = useState([]);
  const [renderBookById, setRenderBookById] = useState(false);
  const [bookId, setBookId] = useState(0);

  useEffect(() => {
    fetchBooks();
  }, [])

  function fetchBooks() {
    fetch(`${apiPath}/${workPath}`, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
    .then(response => response.json())
    .then(books => {
      setBooks(books);
    });
  }

  const handleButtonRenderBookById = (bookId) => {
    setBookId(bookId);
    setRenderBookById(true);
  }

  const handleButtonDeleteBookById = (bookId) => {
    setBookId(bookId);
    deleteBookById(bookId);
  }

  const deleteBookById = (bookId) => {
    fetch(`${apiPath}/${workPath}/${bookId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    }).then((response, reject) => {
      if (response.ok) {
        return response;
      }
      return Promise.reject(reject)
    }).then(
        // изменение состояния компонента для ререндеринга
        () => setBooks(books.filter(book => book.id !== bookId))
    );

  }

  const booksTable = () => {
    return (
        <div>
          <Header title={title}/>
          <table style={styles.bookTable}>
            <thead>
            <tr style={styles.bookTableItem}>
              <th style={styles.bookTableItem}>ID</th>
              <th style={styles.bookTableItem}>Name</th>
              <th style={styles.bookTableItem}>Genres</th>
              <th style={styles.bookTableItem}>Authors</th>
              <th style={styles.bookTableItem}>Actions</th>
            </tr>
            </thead>
            <tbody>
            {
              books.map((book, i) => (
                      <tr style={styles.bookTableItem} key={i}>
                        <td style={styles.bookTableItem}>{book.id}</td>
                        <td style={styles.bookTableItem}>{book.name}</td>
                        <td style={styles.bookTableItem}>
                          <div>
                            {book.genres.map(function (g) {
                              return (<span key={g.name}>{g.name}<br></br></span>)
                            })}
                          </div>
                        </td>
                        <td style={styles.bookTableItem}>
                          <div>
                            {book.authors.map(function (a) {
                              return (<span key={a.id}>{a.name}<br></br></span>)
                            })}
                          </div>
                        </td>
                        <td style={styles.bookTableItem}>
                          <button
                              onClick={() => handleButtonRenderBookById(book.id)}>
                            Go to book
                          </button>
                          <button
                              onClick={() => handleButtonDeleteBookById(
                                  book.id)}>
                            Delete book
                          </button>
                        </td>
                      </tr>
                  )
              )
            }
            </tbody>
          </table>
        </div>
    )
  }

  const getInner = () => {

    if (renderBookById) {
      return <Book bookId={bookId} title={"Book"}/>
    }
    return booksTable();
  }

  return (
      <>
        {getInner()}
      </>
  );

}