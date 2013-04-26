<%@include file="../templates/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="navigation">
    <ul>
        <li><a href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a href="<c:url value="assignmeal.htm"/>">assign meal</a></li>
        <li><a class="current" href="<c:url value="addfood.htm"/>">add food</a></li>
        <li><a href="<c:url value="foodlist.htm"/>">show foodlist</a></li>
        <li><a href="<c:url value="mealform.htm"/>">add meal</a></li>
        <li><a href="<c:url value="meallist.htm"/>">show meallist</a></li>
        <li><a href="<c:url value="logout.htm"/>">logout</a></li>
    </ul>
</div>
<div id="content">

    <h2>Add food form : </h2>
    <form:form method="post" action="/addFood">
    <table border="0">
        <tr>
            <td>Food Name* :</td>
            <td>
                <form:input path="name"/>
                &nbsp;&nbsp;<form:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Food Description :</td>
            <td><form:textarea rows="3" cols="50" path="description"></form:textarea></td>
        </tr>
    </table>
    <input type="submit" value="add food">
    </form:form>

<%@include file="../templates/footer.jsp" %>