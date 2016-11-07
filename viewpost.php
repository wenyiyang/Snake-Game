<?php 
  session_start();
  if(!isset($_SESSION[$_GET['id']])) {
    header("Location:signup.php");
    exit();
  }
  include ("connection.php");
  $query = "SELECT Post_ID, Post_Title, Post_Con, Post_Date FROM Post WHERE Post_ID = '".$_GET['postId']."'";
  $result = mysqli_query($link, $query);
  $row = mysqli_fetch_array($result);
  if($row['Post_ID'] == ''){
    $id = $_GET['id'];
    header("Location: home.php?id=$id");
    exit();
  }
?>
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
        margin-top:70px;
      }
      .marginBottom {
        margin-bottom:20px;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/home.php?id=".$_GET['id']."'";?>>Secret Diary</a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">
          <ul class="nav navbar-nav">
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/home.php?id=".$_GET['id']."'";?>>Home</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/post.php?id=".$_GET['id']."'";?>>Post</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/manage.php?id=".$_GET['id']."'";?>>Manage</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/signup.php?logOut=1&id=".$_GET['id']."'";?>>Log Out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container myContainer" id="background">
      <div class="row">
        <div class="col-md-8 col-md-offset-2" id="topRow">
          <?php
            echo '<div>';
              echo '<h1>'.$row['Post_Title'].'</h1>';
              echo '<p>Posted on '.date('jS M Y', strtotime($row['Post_Date'])).'</p>';
              echo '<p>'.$row['Post_Con'].'</p>';                
            echo '</div>';
          ?>
        </div>
      </div>
    </div>
    <script>
      $(".myContainer").css("min-height",$(window).height());
    </script>
  </body>
</html>