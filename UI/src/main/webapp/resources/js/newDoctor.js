let data_div;                         // Container to generate the data: Legend, test, buttons
let urlSave       = "http://localhost:38001/api/saveDoctor";


document.addEventListener('DOMContentLoaded', function() {
  data_div = document.getElementById( 'data_div' );
  
  createDoctorUI();
  pageButtons();
}, false);

function createDoctorUI() {
  let div_row = document.createElement("div");
  div_row.setAttribute("class", "row my-2");
  
  let div_col = document.createElement("div");
  div_col.setAttribute("class", "col h4");
  div_col.append( "New Doctor" );
  div_row.append( div_col );
  data_div.append( div_row );
  
  
  let name_input = document.createElement( "input" );
  name_input.setAttribute( "class", "form-control my-2" );
  name_input.id = "name";
  name_input.name = "name";
  name_input.placeholder = "Name";
  name_input.autofocus = true;
  name_input.maxLength = 32;
  name_input.type = "text";
  data_div.append( name_input );
  
  let textarea = document.createElement("textarea");
  textarea.id = "details";
  textarea.name = "details";
  textarea.maxLength = 4096;
  textarea.setAttribute("class", "form-control text-input" );
  textarea.rows = 6;
  textarea.placeholder = "Insert some text describing the doctor";
  data_div.append( textarea );
}


function pageButtons() {
  let row = document.createElement("div");
  row.setAttribute("class", "row justify-content-between mt-5");
  
  let col_save = document.createElement("div");
  col_save.setAttribute("class", "col");
  
  let save_button = document.createElement("button");
  save_button.setAttribute("type", "button");
  save_button.name = "Save";
  save_button.setAttribute("class", "btn btn-primary mx-4");
  save_button.onclick = function(){ SaveDoctor() };
  let save_button_text = document.createTextNode("Save");
  save_button.append( save_button_text );
  col_save.append( save_button );
  
  
  let col_cancel = document.createElement("div");
  col_cancel.setAttribute("class", "col");
  
  let cancel_link = document.createElement("a");
  cancel_link.setAttribute("class", "btn btn-outline-warning mr-4" );
  cancel_link.title = "Cancel";
  cancel_link.href = "/home";
  let cancel_link_text = document.createTextNode("Cancel");
  cancel_link.append( cancel_link_text );
  col_cancel.append( cancel_link );
  row.append( col_save, col_cancel );
  data_div.append( row );
}

function SaveDoctor() {
  console.log( "Saving ..." );
  
  let form = document.getElementById( "form" );
  let formData = new FormData( form );
  
  // DEBUG :: Semantic Validation
  console.log( JSON.stringify( Object.fromEntries( formData ) ) );
  
  // @RequestBody
  let newDoctor = {
    "name" : formData.get( "name" ),
    "details" : formData.get( "details" )
  };
  
  let xhttp = new XMLHttpRequest();
  xhttp.open( 'POST', urlSave );
  xhttp.setRequestHeader( 'Content-Type', 'application/json' );
  xhttp.send( JSON.stringify( newDoctor ) );
  
  xhttp.onreadystatechange = function () {
    if ( this.readyState === 4 )
      if ( this.status === 200 ) {
        console.log( "Saved! " );
        location.href = "/home";
      } else {
        console.log( '### Response status code: ' + this.status );
        console.log( this.responseText )
      }
  }
}