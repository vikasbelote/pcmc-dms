
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="loginModel"
		class="form-horizontal" action="admin">

		<form:hidden path="loginId" />

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">User Name</label>
			<div class="col-sm-3">
				<form:input path="userName" class="col-sm-5 form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Password</label>
			<div class="col-sm-3">
				<form:input path="password" class="col-sm-5 form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Department</label>
			<div class="col-sm-3">
				<select id="departmentId" name="departmentForm"
					class="col-sm-5 form-control">
					<option value="-1">Choose Department</option>
					<c:forEach var="dept" items="${deptList}">
						<c:choose>
							<c:when test="${loginModel.department.deptId == dept.deptId}">
								<option selected="selected" value="${dept.deptId}">${dept.deptName}</option>
							</c:when>
							<c:otherwise>
								<option value="${dept.deptId}">${dept.deptName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">User Right</label>
			<div class="col-sm-6">
				<form:checkbox path="isPrint" /> Print &nbsp;&nbsp;
				<form:checkbox path="isDownload" /> Download &nbsp;&nbsp;
				<form:checkbox path="isUpdate" /> Update &nbsp;&nbsp;
				<form:checkbox path="isMandPwd" /> Mandatory Password &nbsp;&nbsp;
			</div>
		</div>

		<div class="col-md-offset-3 col-md-3">
<!-- 			<button class="btn btn-sm btn-success" type="submit" name="btn" value="search">
				<i class="ace-icon fa fa-check bigger-110"></i> Search
			</button> -->
			<button class="btn btn-sm btn-primary" type="submit" name="btn" value="save">
				<i class="ace-icon fa fa-check bigger-110"></i> Save
			</button>
			<button class="btn btn-sm" type="reset">
				<i class="ace-icon fa fa-undo bigger-110"></i> Reset
			</button>
		</div>
	</form:form>
	<br /> <br /> <br />


	<c:choose>
		<c:when test="${ userList.size() > 0 }">
			<table id="simple-table"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th><strong>User Name</strong></th>
						<th><strong>Password</strong></th>
						<th><strong>Department</strong></th>
						<th><strong>Print</strong></th>
						<th><strong>Download</strong></th>
						<th><strong>Update</strong></th>
						<th><strong>Mandatory Password</strong></th>
						<th><strong>Edit</strong></th>
						<th><strong>Delete</strong></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ userList }" var="item">
						<tr>
							<td>${ item.userName  }</td>
							<td>${ item.password  }</td>
							<td>${ item.department.deptName }</td>
							<td>${ item.isPrint }</td>
							<td>${ item.isDownload }</td>
							<td>${ item.isUpdate }</td>
							<td>${ item.isMandPwd }</td>
							<td><a href="./editUser?id=${ item.loginId }"> Edit </a></td>
							<td><a href="./deleteUser?id=${ item.loginId }">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div style="text-align: center;">
				<h2>There is no data present for this search criteria.</h2>
			</div>

		</c:otherwise>
	</c:choose>

</div>
