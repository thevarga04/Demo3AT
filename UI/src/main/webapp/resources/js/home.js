let data_div;                         // Container to generate the data: Legend, test, buttons
let storage = window.sessionStorage;  // Session storage

let resources = new Map();
resources.set( "Illness", "http://localhost:38001/api/" );
resources.set( "Doctor", "http://localhost:38001/api/" );
resources.set( "Patient", "http://localhost:38001/api/" );


let colors = new Map();
colors.set( "Illness", "danger" );
colors.set( "Doctor", "success" );
colors.set( "Patient", "primary" );

let doctor = [];
let illness = [];

document.addEventListener('DOMContentLoaded', function() {
  data_div = document.getElementById( 'data_div' );
  
  pageButtons();
  getAndShowResources();
}, false);


function pageButtons() {
  let row = document.createElement("div");
  row.setAttribute("class", "row mt-0 py-0 mb-4");
  
  let col = document.createElement("div");
  col.setAttribute("class", "col my-0 py-0");
  
  for( let [ resource, url ] of resources ) {
    let link = document.createElement( "a" );
    link.setAttribute( "class", "btn py-1 my-1 mx-3 px-3 btn-outline-" + colors.get( resource ));
    link.title = "Create " + resource;
    link.href = "/new" + resource;
    let link_text = document.createTextNode( "Create " + resource );
    link.append( link_text );
    col.append( link );
    row.append( col );
  }
  data_div.append( row );
}


function getAndShowResources() {
  for( let [ resource, url ] of resources ) {
    getResource( resource, url );
  }
}

function getResource( resource, url ) {
  let xhttp = new XMLHttpRequest();
  xhttp.open( 'GET', url + "get" + resource );
  
  // TODO: Timeout & load balancing ...
  // xhttp.timeout = 500;
  
  xhttp.send();
  
  xhttp.onreadystatechange = function () {
    if ( this.readyState === 4 ) {
      if ( this.status === 200 ) {
        let dto = JSON.parse( xhttp.responseText );
        console.log( "Received dto of " + dto.length + " entries" );
        showResource( resource, url, dto );
      } else {
        console.log( '### Failed to get the response. Response status code: ' + this.status );
      }
    }
  }
}

function showResource( resource, url, dto ) {
  let div_row = document.createElement("div");
  div_row.setAttribute("class", "row mt-4 mb-2");
  
  let div_col = document.createElement("div");
  div_col.setAttribute("class", "col h4");
  div_col.append( resource );
  div_row.append( div_col );
  data_div.append( div_row );
  
  
  // Collect the data to store them a session storage
  doctor = [];
  illness = [];
  for ( let i of dto ) {
    if ( resource === "Doctor" ) {
      doctor.push( i.id + "." + i.name );
    } else if ( resource === "Illness" ) {
      illness.push( i.id + "." + i.name );
    }
    
    let card = document.createElement("div");
    card.setAttribute("class", "card my-0 py-0");
    card.id = resource + "_" + i.id;
    
    let body = document.createElement( "div" );
    body.setAttribute("class", "card-body my-0 py-0");
  
    let row = document.createElement("div");
    row.setAttribute("class", "row my-0 py-0");
    
    let col1 = document.createElement("div");
    col1.setAttribute("class", "col-3 my-0 py-0");
  
    let span_remove = document.createElement("span");
    span_remove.setAttribute("style", "cursor: pointer" );
    span_remove.onclick = function(){ deleteResource( resource, url, i.id ) };
    let i_remove = document.createElement("i");
    i_remove.setAttribute("class", "far fa-window-close text-warning ml-0 pl-0 mr-2");
    span_remove.append( i_remove );
  
    let name = document.createTextNode( i.id + ": " + i.name );
    col1.append( span_remove, name );
    
    
    let col2 = document.createElement("div");
    col2.setAttribute("class", "col my-0 py-0");
    
    let details = document.createTextNode( i.details );
    col2.append( details );
    
    row.append( col1, col2 );
    body.append( row );
    card.append( body );
    data_div.append( card );
  }
  
  // Store the data in a session storage
  if ( resource === "Doctor" ) {
    storage.setItem( "doctor", JSON.stringify( doctor ) );
  } else if ( resource === "Illness" ) {
    storage.setItem( "illness", JSON.stringify( illness ) );
  }
}


function deleteResource( resource, url, id ) {
  console.log( "Removing " + resource + " by ID: " + id );
  
  let xhttp = new XMLHttpRequest();
  xhttp.open('GET', url + "delete" + resource + "/" + id );
  xhttp.send();
  
  xhttp.onreadystatechange = function () {
    if ( this.readyState === 4 ) {
      if ( this.status === 200 ) {
        console.log( "Deleted: " + xhttp.responseText + " " + resource );
      } else {
        console.log( xhttp.responseText + ' Response status code: ' + this.status );
      }
    }
  }
  
 // Hide this row
  document.getElementById( resource + "_" + id ).remove();
}