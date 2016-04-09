<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booths Algorithm</title>
</head>
<body>
    <center>
        <div id="boothsAlgorithm">
            <fieldset title="Booths Algorithm">
                <legend>Booths Algorithm</legend>
                <form action="BoothServlet" method="post">
                    Number 1 : <input type="number" name="num1"> <br> <br>
                    Number 2 : <input type="number" name="num2"> <br>
                    <br> <input type="submit" value="Multiply">
                </form>
            </fieldset>
        </div>
        
        <br>
        
        <%
            String r = (String) session.getAttribute("multiplyResponse");
            if(r!=null){
                out.print(r);
            }
        %>
        
        
    </center>
</body>
</html>