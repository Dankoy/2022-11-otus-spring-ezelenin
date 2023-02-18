import React from 'react'
import ReactDOM from 'react-dom/client'
import {Books} from './components/Books';
import {BrowserRouter} from "react-router-dom";

let container = document.getElementById('root');

export const root = ReactDOM.createRoot(container);

function App() {
  return <Books title={"Books"}/>;
}

// Initial render
root.render(
    <BrowserRouter>
      <App/>
    </BrowserRouter>
);
