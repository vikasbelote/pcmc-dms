<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<form:form id="some-form" commandName="lawModel"
		class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2325;&#2375;&#2360; &#2344;&#2306;&#2348;&#2352; </label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="caseNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2325;&#2379;&#2352;&#2381;&#2335; &#2344;&#2366;&#2357;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="courtName" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2327;&#2335;&#2381;&#2335;&#2366; &#2344;&#2306;&#2348;&#2352;</label>
			<div class="col-sm-9" style="text-align: left;">
				<form:input path="gattaNo" class="col-sm-5 form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1">&#2347;&#2366;&#2312;&#2354; &#2344;&#2366;&#2357;</label>
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
				<a href="./law" class="btn btn-sm btn-primary"
					type="button"> <i class="ace-icon fa fa-check bigger-110"></i>
					Back
				</a>

			</div>
		</div>
	</form:form>
</div>


<div class="col-sm-8">
	<c:choose>
		<c:when test="${ sessionScope.print || sessionScope.download }">
			<embed src="./static/images/${ lawModel.getImagePath() }.pdf"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:when>
		<c:otherwise>
			<embed
				src="./static/images/${ lawModel.getImagePath() }.pdf#toolbar=0"
				width="800px" height="600px" style="border: solid 2px black" />
		</c:otherwise>
	</c:choose>
</div>