<?php include ("account.php");?>
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
        margin-top:100px;
        text-align:center;
      }
      #topRow h1 {
        font-size:300%;
      }
      .bold {
        font-weight:bold;
      }
      .marginTop {
        margin-top:20px;
      }
      #reset {
        margin-top:10px;
      }
      #reset_email {
        margin-top:10px;
      }
    </style>
  </head>
  <body>
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
         <div class="modal-content">
           <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
             <h2 class="modal-title" id="myModalLabel">Forgot Password?</h2>
             <p class="marginTop bold">Please enter the email address you used to sign in.</p>
             <div id="infoBox">
             </div>
           </div>
           <div class="modal-body">
             <form method="post">
               <div class="form group">
                 <lable for="reset_email" class="bold">Email Address: </lable>
                 <input id="reset_email" type="email" class="form-control"></input>
               </div>
                 <button id="reset" type="submit" class="btn btn-success" value="reset">continue</button>
             </form>
           </div>
        </div>
      </div>
    </div>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="signup.php">Secret Diary</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <form class="navbar-form navbar-right" method="post">
            <div class="form-group">
              <input id="email_Log_In" name="Email_Log_In" value="<?php echo addslashes($_POST["Email_Log_In"]);?>" type="email" class="form-control" placeholder="Email"></input>
            </div>
            <div class="form-group">
              <input id="password_Log_In" name="Password_Log_In" value="<?php echo addslashes($_POST["Password_Log_In"]);?>" type="password" class="form-control" placeholder="Password"></input>
            </div>
            <input id="submit" name="submit" value="Log In" type="submit" class="btn btn-success"></input>
            <button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal">Forgot Password?</button>
          </form>
        </div>
      </div>
    </nav>
    <div class="container myContainer" id="background">
      <div class="row">
        <div class="col-md-6 col-md-offset-3" id="topRow">
          <h1>Secret Diary</h1>
          <p class="lead">Create your own private diary here, wherever you go.</p>
          <p class="bold marginTop">Interested? Sign Up Below!</p>
          <?php
            if($error) {
              $error = addslashes($error);
              echo "<div class='alert alert-danger'>$error</div>";
            }
            if($massage) {
              $massage = addslashes($massage);
              echo "<div class='alert alert-success'>$massage</div>";
            }
            if($errormassage) {
              $errormassage = addslashes($errormassage);
              echo "<div class='alert alert-danger'>$errormassage</div>";
            }
          ?>
          <form method="post">
            <div class="form-group marginTop">
              <label for="email">Email</label>
              <input id="email" name="Email" value="<?php echo addslashes($_POST["Email"]);?>" type="email" class="form-control" placeholder="Email"></input>
            </div>
            <div class="form-group marginTop">
              <label for="password">Password</label>
              <input id="password" name="Password" value="<?php echo addslashes($_POST["Password"]);?>" type="password" class="form-control" placeholder="Password"></input>
            </div>
            <input id="submit" name="submit" value="Sign Up" type="submit" class="btn btn-success marginTop"></input>
          </form>
        </div>
      </div>
    </div>
    <script>
      $(".myContainer").css("min-height",$(window).height());
      // Send password recovery info to backend, receive resulting data, and interact with resulting data.
      $("#reset").click(function() {
        var success = "The reset link has been sent. Please check your email.<br />"
        var email = $("#reset_email").val();
        var submit = $("#reset").val();
        $.post("account.php",{submit:submit,Email_Reset:email},function(data) {
          $("#infoBox").html(data);
          if(data == success) {
            setTimeout(function(){window.location.reload(true); }, 3000);
            clearTimeout();
          }
        });
        return false;
      });
    </script>
  </body>
</html>