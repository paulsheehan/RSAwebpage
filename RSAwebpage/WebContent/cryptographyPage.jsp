<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Encrypt File</title>
    
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

  </head>
  <body>
     <!-- Page Content -->
    <div class="container">

        <hr class="featurette-divider">

        <!-- First Featurette -->
        <div class="featurette" id="about" align="center">
           <img class="featurette-image img-circle img-responsive pull-right" src="http://i.imgur.com/jYfCFPG.png">
            <h1 class="featurette-heading">
                <span class="text-muted">RSA File Encryption</span>
            </h1>
            <h2 class="lead">by Paul Sheehan</h2>
        </div>
  
		<div class="form-group" align="center">
		<h4> Enter text </h4>
			<form action="FrontController" method="post" >
						<input type="text" name="message" />
						<input type="hidden" name="action" value="UserSubmitsString" />
						<input type="submit" value="Enter" class="btn btn-primary btn-sm" />
			</form>
		</div>
		
		<h3 class="featurette-heading" align="center"> OR </h3>
		
		<div class="form-group" align="center">
		<h4> Submit a file </h4>
					<form action="FrontController" method="post" enctype="multipart/form-data">
						<input type="hidden" name="action" value="UserSubmitsFile" />
						<input type="file" name="file" class="btn btn-primary btn-sm"/>
						<input type="submit" value="Upload" class="btn btn-primary btn-sm" />
					</form>
		</div>
	</body>
<html>