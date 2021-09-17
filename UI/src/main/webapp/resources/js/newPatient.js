let data_div;                         // Container to generate the data: Legend, test, buttons
let urlSave       = "http://localhost:38001/api/savePatient";


document.addEventListener('DOMContentLoaded', function() {
  data_div = document.getElementById( 'data_div' );
  
  createPatientUI();
  pageButtons();
}, false);

function createPatientUI() {
  let row_head = document.createElement("div");
  row_head.setAttribute("class", "row my-2");
  
  let col_head = document.createElement("div");
  col_head.setAttribute("class", "col h4");
  col_head.append( "New Patient" );
  row_head.append( col_head );
  data_div.append( row_head );
  
  
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
  textarea.rows = 3;
  textarea.placeholder = "Insert some text describing the patient";
  data_div.append( textarea );
  
  
  // Choose a Doctor (if any)
  let row_doctor = document.createElement("div");
  row_doctor.setAttribute("class", "row my-0 py-0 mt-3");
  
  let col_doctor = document.createElement("div");
  col_doctor.setAttribute("class", "col h5 my-0 py-0");
  col_doctor.append( "Doctor" );
  row_doctor.append( col_doctor );
  data_div.append( row_doctor );
  
  let doctor = JSON.parse( sessionStorage.getItem( "doctor" ) );
  let doctor_select = document.createElement( "select" );
  doctor_select.setAttribute( "class", "input-heap mt-1 mx-0 px-2" ); // no: custom-select
  doctor_select.id = "doctor";
  doctor_select.name = "doctor";
  doctor_select.title = "Select a Doctor";
  
  for( let d of doctor ) {
    let doctor_option = document.createElement( "option" );
    doctor_option.value = d;
    doctor_option.append( d );
    doctor_select.append( doctor_option );
  }
  data_div.append( doctor_select );
  
  
  // Select (multiple) illnesses (if any)
  let row_illness = document.createElement("div");
  row_illness.setAttribute("class", "row my-0 py-0 mt-3");
  
  let col_illness = document.createElement("div");
  col_illness.setAttribute("class", "col h5 my-0 py-0");
  col_illness.append( "Illnesses" );
  row_illness.append( col_illness );
  data_div.append( row_illness );
  
  let illness = JSON.parse( sessionStorage.getItem( "illness" ) );
  let illness_select = document.createElement( "select" );
  illness_select.setAttribute( "class", "input-heap mt-1 mx-0 px-2" ); // no: custom-select
  illness_select.id = "illnesses";
  illness_select.name = "illnesses";
  illness_select.title = "Select (multiple) Illnesses";
  illness_select.multiple = true;
  illness_select.setAttribute( "size", illness.length === 0 ? 1 : "" + Math.min( 5, illness.length ) );
  
  for( let i of illness ) {
    let illness_option = document.createElement( "option" );
    illness_option.id = i;
    illness_option.name = i;
    illness_option.value = i;
    illness_option.append( i );
    illness_select.append( illness_option );
  }
  data_div.append( illness_select );
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
  save_button.onclick = function(){ SavePatient() };
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

function SavePatient() {
  console.log( "Saving ..." );
  
  let form = document.getElementById( "form" );
  let formData = new FormData( form );
  
  let doctor = formData.get( "doctor" );
  doctor = doctor.substr( 0, doctor.indexOf( '.' ) );
  
  let selectedOptions = document.getElementById( "illnesses" ).selectedOptions;
  let illnesses = [];
  for( let option of selectedOptions ) {
    illnesses.push( option.value.substr( 0, option.value.indexOf( '.' ) ) );
  }
  
  // @RequestBody
  let newPatient = {
    "name"        : formData.get( "name" ),
    "details"     : formData.get( "details" ),
    "doctor"      : doctor,
    "illnesses"   : illnesses
  };
  
  // DEBUG :: Semantic Validation
  console.log( newPatient );
  // console.log( JSON.stringify( newPatient ) );
  
  
  let xhttp = new XMLHttpRequest();
  xhttp.open( 'POST', urlSave );
  xhttp.setRequestHeader( 'Content-Type', 'application/json' );
  xhttp.send( JSON.stringify( newPatient ) );
  
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