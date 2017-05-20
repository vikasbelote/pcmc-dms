<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="model" class="form-horizontal"
		action="${ postUrl }">

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">Zone No</label>
			<div class="col-sm-3">
				<form:hidden path="subDeptId" />
				<form:input path="subDeptValue" class="col-sm-5 form-control" />
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
							<c:when test="${model.department.deptId == dept.deptId}">
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

		<div class="col-md-offset-3 col-md-3">
			<button class="btn btn-sm btn-primary" type="submit">
				<i class="ace-icon fa fa-check bigger-110"></i> Save
			</button>
		</div>
	</form:form>
	<br /> <br /> <br />

	<c:choose>
		<c:when test="${ masterList.size() > 0 }">
			<table id="simple-table"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th><strong>Row Id</strong></th>
						<th><strong>Zone No</strong></th>
						<th><strong>Department</strong></th>
						<th>Edit</th>
						<!-- <th>Delete</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ masterList }" var="item">
						<tr>
							<td>${ item.subDeptId  }</td>
							<td>${ item.subDeptValue  }</td>
							<td>${ item.department.deptName  }</td>
							<td><a href="./edit${ postUrl }?id=${ item.subDeptId }">Edit</a></td>
							<%-- <td><a href="./delete${ postUrl }?id=${ item.subDeptId }">Delete</a></td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div style="text-align: center;">
				<h2>There is no data present for this master.</h2>
			</div>

		</c:otherwise>
	</c:choose>

</div>










