<?php 
  session_start();
  if(!isset($_SESSION[$_GET['id']])) {
    header("Location:signup.php");
    exit();
  }
  include ("connection.php");
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
      a {
        color:black !important;
      }
      #background {
        background-image:url("background.jpg");
        background-repeat:no-repeat;
        width:100%;
        background-size:cover;
      }
      .topRow {
        margin-top:70px;
      }
      .marginBottom {
        margin-bottom:20px;
      }
      #siderBar {
        margin-top:100px;
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
            <li class="active"><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/home.php?id=".$_GET['id']."'";?>>Home</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/post.php?id=".$_GET['id']."'";?>>Post</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/manage.php?id=".$_GET['id']."'";?>>Manage</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/signup.php?logOut=1&id=".$_GET['id']."'";?>>Log Out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container myContainer" id="background">
      <div class="row">
        <div class="col-md-8 col-md-offset-1 topRow pull-left">
          <?php
            $infoID = $_SESSION[$_GET['id']];
            $query = "SELECT Post_ID, Post_Title, Post_Des, Post_Date FROM Post WHERE Info_ID = '".$infoID."'ORDER BY Post_Date DESC";
            $result = mysqli_query($link, $query);
            while($row = mysqli_fetch_array($result)) {
              echo '<div>';
                echo '<h1><a href="viewpost.php?id='.$_GET['id'].'&postId='.$row['Post_ID'].'">'.$row['Post_Title'].'</a></h1>';
                echo '<p>Posted on '.date('jS M Y H:i:s e', strtotime($row['Post_Date'])).'</p>';
                echo '<p>'.$row['Post_Des'].'</p>';                
                echo '<p><a href="viewpost.php?id='.$_GET['id'].'&postId='.$row['Post_ID'].'">Read More</a></p>';                
              echo '</div>';
            }
          ?>
        </div>
        <div class="col-md-2 pull-right" id="siderBar">
          <h1>Achives</h1>
          <ul>
            <?php
              $infoID = $_SESSION[$_GET['id']];
              $query = "SELECT Info_ID, Post_Date, MONTH(Post_Date) AS Month, YEAR(Post_Date) AS Year FROM Post GROUP BY MONTH(Post_Date), YEAR(Post_Date) HAVING Info_ID = '".$infoID."'ORDER BY Post_Date DESC";
              $result = mysqli_query($link, $query);
              while($row = mysqli_fetch_array($result)) {
                $month = date("F", mktime(0, 0, 0, $row['Month']));
                $year = Date("Y", strtotime($row['Year']));
                echo '<li><a href="archives.php?month='.$row['Month'].'&year='.$row['Year'].'&id='.$_GET['id'].'">'.$month." ".$year.'</a></li>';
              }
            ?>
          </ul>
        </div>
      </div>
    </div>
    <script>
      $(".myContainer").css("min-height",$(window).height());
    </script>
  </body>
</html>

