<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<form:form id="some-form" commandName="bupModel"
		class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2327;&#2366;&#2357;&#2366;&#2330;&#2375;
				&#2344;&#2366;&#2357;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="villageName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2360;&#2352;&#2381;&#2357;&#2375;
				&#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="serveNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2309;&#2349;&#2367;&#2351;&#2306;&#2340;&#2366;
				&#2344;&#2366;&#2357; </label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="architectName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2350;&#2366;&#2354;&#2325;&#2366;&#2330;&#2375;
				&#2344;&#2366;&#2357; </label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="ownerName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2348;&#2368;&#2346;&#2368;
				&#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="buildingPermissionNo"
					class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2327;&#2335;&#2381;&#2335;&#2366;
				&#2344;&#2306;&#2348;&#2352;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="gattaNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2347;&#2366;&#2312;&#2354;
				&#2344;&#2366;&#2357;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="fileName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2357;&#2352;&#2381;&#2359;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="year" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-3">
				<a href="./bup" class="btn btn-sm btn-primary" type="button"> <i
					class="ace-icon fa fa-check bigger-110"></i> Back
				</a>

			</div>
		</div>
	</form:form>
</div>



<div class="col-sm-8">
	<c:choose>
		<c:when test="${ sessionScope.print || sessionScope.download }">
			<embed src="./static/images/${ bupModel.getImagePath() }.pdf"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:when>
		<c:otherwise>
			<embed
				src="./static/images/${ bupModel.getImagePath() }.pdf#toolbar=0"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:otherwise>
	</c:choose>
</div>