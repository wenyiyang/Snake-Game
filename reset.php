<?php include ("resetform.php");?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Secret Dairy</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <style type="text/css">
      #background {
        background-image:url("background.jpg");
        background-repeat:no-repeat;
        width:100%;
        background-size:cover;
      }
      #topRow {
        margin-top:150px;
        text-align:center;
        border:1px black solid;
      }
      .marginTop {
        margin-top:20px;
      }
      .marginBottom {
        margin-bottom:10px;
      }
    </style>
  </head>
  <body>
    <div class="container myContainer" id="background">
      <div class="row">
        <div class="col-md-6 col-md-offset-3" id="topRow">
          <h3>Reset Your Password</h3>
          <?php
            if($error) {
              $error = addslashes($error);
              echo "<div class='alert alert-danger'>$error</div>";
            }
          ?>
          <form method="post">
            <div class="form-group marginTop">
              <label for="password">Password</label>
              <input id="password" name="password" type="password" class="form-control" placeholder="Password"></input>
            </div>
            <div class="form-group marginTop">
              <label for="confirm-password">Confirm Password</label>
              <input id="confirm-password" name="confirm-password" type="password" class="form-control" placeholder="Comfirm Password"></input>
            </div >
            
            <input id="submit" name="submit" value="Reset" type="submit" class="btn btn-success marginBottom"></input>
          </form>
        </div>
      </div>
    </div>
    <script>
      $(".myContainer").css("min-height",$(window).height());
    </script>
  </body>
</html>

