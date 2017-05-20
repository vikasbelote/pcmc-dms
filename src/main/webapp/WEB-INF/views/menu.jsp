<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="custom-bootstrap-menu" class="navbar navbar-default "
	role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><%=session.getAttribute("deptName")%></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-menubuilder">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
					class="icon-bar"></span><span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse navbar-menubuilder">
			<ul class="nav navbar-nav navbar-left">
				<c:choose>
					<c:when test="${show}">
						<li><a href="./${SEARCH_URL}"
							style="background-color: rgba(196, 192, 133, 1);">Search</a></li>
						<c:if test="${ sessionScope.update }">
							<li><a href="./${UPLOAD_URL}">Upload</a></li>
						</c:if>
						

					</c:when>
					<c:otherwise>
						<li><a href="./${SEARCH_URL}">Search</a></li>
						
						<c:if test="${ sessionScope.update }">
							<li><a href="./${UPLOAD_URL}"
							style="background-color: rgba(196, 192, 133, 1);">Upload</a></li>
						</c:if>
						
						
					</c:otherwise>
				</c:choose>



			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">About Us</a></li>
				<li><a href="./logout">Log out</a></li>
			</ul>
		</div>
	</div>
</div>