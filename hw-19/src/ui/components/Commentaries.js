import React, {useEffect, useState} from 'react'
import {apiPath, workPath, commentaryPath} from "../context";
import {styles} from "../styles/styles";

const Header = (props) => (
    <h1>{props.title}</h1>
);

export const Commentaries = (props) => {

  const {bookId} = props;
  const [commentaries, setCommentaries] = useState([]);

  useEffect(() => {
    fetchCommentaries();
  }, [])

  function fetchCommentaries() {
    fetch(`${apiPath}/${workPath}/${bookId}/${commentaryPath}`, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
    .then(response => response.json())
    .then(coms => {
      setCommentaries(coms);
    });
  }

  const deleteCommentaryById = (id) => {
    fetch(`${apiPath}/${commentaryPath}/${id}`, {
      method: 'DELETE'
    }).then(
        () => setCommentaries(commentaries.filter(com => com.id !== id))
    )
  }

  return (
      <div>
        <Header title={props.title}/>
        <table style={styles.bookTable}>
          <thead>
          <tr style={styles.bookTableItem}>
            <th style={styles.bookTableItem}>ID</th>
            <th style={styles.bookTableItem}>Text</th>
            <th style={styles.bookTableItem}>Book</th>
            <th style={styles.bookTableItem}>Action</th>
          </tr>
          </thead>
          <tbody>
          {
            commentaries.map((com, i) => (
                    <tr style={styles.bookTableItem} key={i}>
                      <td style={styles.bookTableItem}>{com.id}</td>
                      <td style={styles.bookTableItem}>{com.text}</td>
                      <td style={styles.bookTableItem}>{com.book.name}</td>
                      <td style={styles.bookTableItem}>
                        <button
                            onClick={() => deleteCommentaryById(com.id)}>
                          Delete commentary
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