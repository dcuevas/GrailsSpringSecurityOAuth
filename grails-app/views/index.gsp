<%@ page import="com.refactoringyourself.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Grails OAuth with Spring Security Example</title>
	</head>

	<body>
		<div class="row-fluid">

            <sec:ifNotLoggedIn>
                <section id="main" class="span8">
                    <div class="page-header">
                        <h2>Sign in using social account</h2>
                    </div>

                        <div class="span4">

                            <ul class="nav nav-list">
                                <li><oauth:connect provider="google"><img src="images/google16.png"> Sign in with Google</oauth:connect></li>
                                <li><oauth:connect provider="facebook"><img src="images/facebook16.png"> Sign in with Facebook</oauth:connect></li>
                                <li><oauth:connect provider="twitter"><img src="images/twitter16.png"> Sign in with Twitter</oauth:connect></li>
                            </ul>
                        </div>
                </section>
            </sec:ifNotLoggedIn>

            <sec:ifLoggedIn>
                <g:set var="loggedUser" value="${User.get(sec.loggedInUserInfo(field:"id") as Long)}"/>

                <div class="page-header">
                    <h1>Welcome ${loggedUser.username}!</h1>
                </div>

                <div>
                    <g:if test="${loggedUser.avatarUrl}">
                        <img src="${loggedUser.avatarUrl}"/>
                    </g:if>
                    <g:link controller="logout">Logout</g:link>
                </div>
            </sec:ifLoggedIn>


            <div class="span5" style="margin-top: 20px">
                <g:link controller="secure" action="index">Secure Controller</g:link>
            </div>

		</div>

	</body>
</html>
