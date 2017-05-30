<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12">
	<form:form id="some-form" commandName="bupModel"
		class="form-horizontal" action="bup">


		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2327;&#2366;&#2357;&#2366;&#2330;&#2375; &#2344;&#2366;&#2357;</label>
			<div class="col-sm-3">
				<form:select path="villageName" class="col-sm-5 form-control">
					<form:option value="" label="Choose Village" />
					<form:options items="${villageList}" itemLabel="villageName" itemValue="villageName" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2360;&#2352;&#2381;&#2357;&#2375; &#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-3">
				<form:input path="serveNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">CTS &#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-3">
				<form:input path="ctsNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2309;&#2349;&#2367;&#2351;&#2306;&#2340;&#2366; &#2344;&#2366;&#2357; </label>
			<div class="col-sm-3">
				<form:input path="architectName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2350;&#2366;&#2354;&#2325;&#2366;&#2330;&#2375; &#2344;&#2366;&#2357; </label>
			<div class="col-sm-3">
				<form:input path="ownerName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2348;&#2368;&#2346;&#2368; &#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-3">
				<form:input path="buildingPermissionNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2327;&#2335;&#2381;&#2335;&#2366; &#2344;&#2306;&#2348;&#2352;</label>
			<div class="col-sm-3">
				<form:input path="gattaNo" class="col-sm-5 form-control" />
			</div>
		</div> --%>
		<%-- <div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2347;&#2366;&#2312;&#2354; &#2344;&#2366;&#2357;</label>
			<div class="col-sm-3">
				<form:input path="fileName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1">&#2357;&#2352;&#2381;&#2359;</label>
			<div class="col-sm-3">
				<form:input path="year" class="col-sm-5 form-control" />
			</div>
		</div> --%>
		<div class="col-md-offset-3 col-md-3">
			<button class="btn btn-sm btn-primary" type="submit">
				<i class="ace-icon fa fa-check bigger-110"></i> Search
			</button>
			<button class="btn btn-sm" type="reset">
				<i class="ace-icon fa fa-undo bigger-110"></i> Reset
			</button>
		</div>
	</form:form>
	<br /> <br /> <br />
	<c:choose>
		<c:when test="${ showDeptTable }">
			<c:choose>
				<c:when test="${ searchList.size() > 0 }">
					<table id="simple-table"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><strong>&#2344;&#2306;&#2348;&#2352;</strong></th>
								<th><strong>&#2327;&#2366;&#2357;&#2366;&#2330;&#2375; &#2344;&#2366;&#2357;</strong></th>
								<th><strong>&#2360;&#2352;&#2381;&#2357;&#2375; &#2344;&#2306;&#2348;&#2352; </strong></th>
								<th><strong>CTS &#2344;&#2306;&#2348;&#2352; </strong></th>
								<th><strong>&#2309;&#2349;&#2367;&#2351;&#2306;&#2340;&#2366; &#2344;&#2366;&#2357; </strong></th>
								<th><strong>&#2350;&#2366;&#2354;&#2325;&#2366;&#2330;&#2375; &#2344;&#2366;&#2357; </strong></th>
								<th><strong>&#2348;&#2368;&#2346;&#2368; &#2344;&#2306;&#2348;&#2352; </strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ searchList }" var="item">
								<tr>
									<td>${ item[0]  }</td>
									<td>${ item[1]  }</td>
									<td>${ item[2] }</td>
									<td>${ item[3] }</td>
									<td>${ item[4] }</td>
									<td>${ item[5] }</td>
									<td>${ item[6] }</td>
									<td><a href="./viewBup?id=${ item[0] }">View</a></td>
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
		</c:when>
	</c:choose>
</div>










