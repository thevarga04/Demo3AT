<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.css" integrity="sha512-drnvWxqfgcU6sLzAJttJv7LKdjWn0nxWCSbEAtxJ/YYaZMyoNLovG7lPqZRdhgL1gAUfa+V7tbin8y+2llC1cw==" crossorigin="anonymous" />


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>New Doctor</title>
  
  <%-- The UI --%>
  <script src="<c:url value="/resources/js/newDoctor.js" />"></script>
  <link href="<c:url value="/resources/css/ui.css" />" rel="stylesheet">
  
</head>
<body>

<%-- Page Content --%>

<div class="container-fluid">
  <div class="row">&nbsp;</div>
  <div class="row">
    <div class="col">
      <div class="card col-12 mt-2">
  
        <form id="form" method="post">
          <%-- Container to generate the data to --%>
          <div id="data_div" class="card-body"></div>
        </form>
        
      </div>
    </div>
  </div>
</div>


</body>
</html>