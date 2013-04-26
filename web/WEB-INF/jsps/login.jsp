<%@include file="../templates/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="navigation">
    <ul>
        <li><a href="<c:url value="assign.htm"/>">todays meals</a></li>
        <li><a class="current" href="<c:url value="login.htm"/>">login</a></li>
    </ul>
</div>
<div id="content">
    <h2>Login Form : </h2>
    <form:form method="post" action="/foodspring/loginUser">
    <table border="0">
        <tr>
            <td>Username* :</td>
            <td><form:input type="text" path="username"/></td>
            <td><form:errors path="username" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password* :</td>
            <td><form:input type="password" path="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
    </table>
    <input type="submit" value="login">
    </form:form>

    <%@include file="../templates/footer.jsp" %>

