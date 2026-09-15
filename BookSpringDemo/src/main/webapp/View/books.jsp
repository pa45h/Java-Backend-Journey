<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book App</title>
</head>
<body>

<h2>Add Book</h2>
<form action="add" method="post">
    Title: <input type="text" name="title"/><br><br>
    Author: <input type="text" name="author"/><br><br>
    <button type="submit">Add Book</button>
</form>

<h2>Book List</h2>
<ul>
    <c:forEach var="b" items="${books}">
        <li>${b.title} - ${b.author}</li>
    </c:forEach>
</ul>

</body>
</html>