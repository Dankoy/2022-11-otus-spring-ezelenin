import React, {Suspense, useEffect, useState} from 'react'
import {apiPath, workPath} from "../context";
import {styles} from "../styles/styles";
import {LoadingSpinner} from "./LoadingSpinner";
import {Commentaries} from "./Commentaries";

const Header = (props) => (
    <h1>{props.title}</h1>
);

export const Book = (props) => {

  const {bookId} = props;
  const [loading, setLoading] = useState(true);
  const [book, setBook] = useState({});

  useEffect(() => {
    fetch(`${apiPath}/${workPath}/${bookId}`,{
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
    .then(response => response.json())
    .then(book => {
      setLoading(false);
      setBook(book);
    });
  }, [])

  if (loading) {
    return (
        <LoadingSpinner/>
    )
  }

  if (book) {
    return (

        <div>
          <Header title={props.title}/>
          <table style={styles.bookTable}>
            <thead>
            <tr style={styles.bookTableItem}>
              <th style={styles.bookTableItem}>ID</th>
              <th style={styles.bookTableItem}>Name</th>
              <th style={styles.bookTableItem}>Genres</th>
              <th style={styles.bookTableItem}>Authors</th>
            </tr>
            </thead>
            <tbody>
            {
              <tr style={styles.bookTableItem}>
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
              </tr>
            }
            </tbody>
          </table>
          <br></br>
          <div>
            <Suspense fallback={<LoadingSpinner/>}>
              <Commentaries bookId={book.id} title={"Commentaries"}/>
            </Suspense>
          </div>
        </div>
    );
  }

}