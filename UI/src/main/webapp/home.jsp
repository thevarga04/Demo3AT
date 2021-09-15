<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.css" integrity="sha512-drnvWxqfgcU6sLzAJttJv7LKdjWn0nxWCSbEAtxJ/YYaZMyoNLovG7lPqZRdhgL1gAUfa+V7tbin8y+2llC1cw==" crossorigin="anonymous" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.css" integrity="sha512-f73UKwzP1Oia45eqHpHwzJtFLpvULbhVpEJfaWczo/ZCV5NWSnK4vLDnjTaMps28ocZ05RbI83k2RlQH92zy7A==" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.js" integrity="sha512-14GPUgKFTeCsgj5WWZpTNQ525GYlOK3DMTqrjsly3TDIDnOUbZ5sWyfI6HqsWUmMmaCoa6q7FHrbq9xdqNhmYg==" crossorigin="anonymous"></script>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>UI</title>
  
  <%-- The UI --%>
  <script src="<c:url value="/resources/js/home.js" />"></script>
  
  
</head>
<body>

<%-- Page Content --%>

<div class="container-fluid">
  <div class="row">&nbsp;</div>
  <div class="row">
    <div class="col">
      <div class="card col-12 mt-2">
        
        <%-- Container to generate the data to --%>
        <div id="data_div" class="card-body">
        </div>
      
      </div>
    </div>
  </div>
</div>


</body>
</html>