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
					<c:when test="${ userMenu }">
						<li class="active"><a href="admin">User</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="admin">User</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${ masterMenu }">

						<li class="active" class="dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown" href="#">Master
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="GutNo">Gut No</a></li>
								<li><a href="TableNo">Table No</a></li>
								<li><a href="Village">Village</a></li>
								<li><a href="Court">Court</a></li>
								<li><a href="Hospital">Hospital</a></li>
								<li><a href="Rack">Rack</a></li>
								<li><a href="RecordType">Record Type</a></li>
								<li><a href="Department">Department</a></li>
							</ul></li>

					</c:when>
					<c:otherwise>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Master <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="GutNo">Gut No</a></li>
								<li><a href="TableNo">Table No</a></li>
								<li><a href="Village">Village</a></li>
								<li><a href="Court">Court</a></li>
								<li><a href="Hospital">Hospital</a></li>
								<li><a href="Rack">Rack</a></li>
								<li><a href="RecordType">Record Type</a></li>
								<li><a href="Department">Department</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${ subDepartmentMenu }">
						<li class="active" class="dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown" href="#">Sub-Department
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="ZoneNo">Zone No</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>

						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Sub-Department <span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="ZoneNo">Zone No</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${ upload }">
						<li class="active" class="dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown" href="#">Upload
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="uploadDataPropTax">Property Tax</a></li>
								<li><a href="uploadDataLaw">Law</a></li>
								<li><a href="uploadDataAudit">Audit</a></li>
								<li><a href="uploadDataBup">Building Permission</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Upload <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="uploadDataPropTax">Property Tax</a></li>
								<li><a href="uploadDataLaw">Law</a></li>
								<li><a href="uploadDataAudit">Audit</a></li>
								<li><a href="uploadDataBup">Building Permission</a></li>
							</ul></li>
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