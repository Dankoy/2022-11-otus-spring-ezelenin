let _table_ = document.createElement('table'),
    _tr_ = document.createElement('tr'),
    _th_ = document.createElement('th'),
    _td_ = document.createElement('td')

// Builds the HTML Table out of myList json data from Ivy restful service.
function buildHtmlTable(json) {
  let table = _table_.cloneNode(false),
      columns = addAllColumnHeaders(json, table);
  for (let i = 0, maxi = json.length; i < maxi; ++i) {
    let tr = _tr_.cloneNode(false);
    for (let j = 0, maxj = columns.length; j < maxj; ++j) {
      let td = _td_.cloneNode(false);
      let cellValue = json[i][columns[j]];
      td.appendChild(document.createTextNode(cellValue || ''));
      tr.appendChild(td);
    }
    table.appendChild(tr);
  }
  return table;
}

// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records
function addAllColumnHeaders(arr, table) {
  let columnSet = [],
      tr = _tr_.cloneNode(false);
  for (let i = 0, l = arr.length; i < l; i++) {
    for (let key in arr[i]) {
      if (arr[i].hasOwnProperty(key) && columnSet.indexOf(key) === -1) {
        columnSet.push(key);
        let th = _th_.cloneNode(false);
        th.appendChild(document.createTextNode(key));
        tr.appendChild(th);
      }
    }
  }
  table.appendChild(tr);
  return columnSet;
}