<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2 class="header blue smaller lighter" style="text-align: center;">Uploading Krida Vibhag
	Data files to server</h2>
<br />
<a href="${DOWNLOAD_URL}" style="align: left">Download Template</a>
<form class="form-horizontal" id="myform" method="post"
	action="${UPLOAD_URL}" enctype="multipart/form-data">


	<input type="file" name="file" style="padding-left: 567px;" />


	<button type="submit" style="margin-left: 600px; margin-top: 20px;"
		class="btn btn-sm btn-primary">Submit</button>
	<button type="reset" style="margin-top: 20px;" class="btn btn-sm">Reset</button>
</form>


<div style="text-align: center;">
	<h2>${message}</h2>
</div>

