<?php 
  session_start();
  if(!isset($_SESSION[$_GET['id']])) {
    header("Location:signup.php");
    exit();
  }
  include ("connection.php");
  if(isset($_POST["submit"])) {
    $_POST = array_map("stripslashes", $_POST);
    extract($_POST);
    if($postTitle ==''){
      $error['title'] = 'Please enter the title.';
    }
    if($postDesc ==''){
      $error['desc'] = 'Please enter the description.';
    }
    if($postCont ==''){
      $error['cont'] = 'Please enter the content.';
    }
    if(!isset($error)){
      $postDate = date("Y-m-d H:i:s");
      $infoID = $_SESSION[$_GET['id']];
      $query = "INSERT INTO Post (Post_Title,Post_Des,Post_Con,Post_Date,Info_ID) VALUES ('".$postTitle."','".$postDesc."','".$postCont."','".$postDate."','".$infoID."')";
      $result = mysqli_query($link, $query);
      if($result) {
        $id = $_GET['id'];
        header("Location: home.php?action=added&id=$id");
        exit();
      }
    } 
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
    <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
    <script>
      tinymce.init({
        selector: "textarea",
        plugins: [
          "advlist autolink lists link image charmap print preview anchor",
          "searchreplace visualblocks code fullscreen",
          "insertdatetime media table contextmenu paste"
        ],
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
      });
    </script>
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
            <li class="active"><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/post.php?id=".$_GET['id']."'";?>>Post</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/manage.php?id=".$_GET['id']."'";?>>Manage</a></li>
            <li><a href=<?php echo "'"."http://wenyiyang.net/web-application/secret-diary/signup.php?logOut=1&id=".$_GET['id']."'";?>>Log Out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container myContainer" id="background">
      <div class="row">
        <div class="col-md-6 col-md-offset-3" id="topRow">
          <form method='post'>
            <div class="form-group">
              <label for="title">Title</label><br />
              <?php
                if(isset($error['title'])) {
                  $error1 = addslashes($error['title']);
                  echo "<div class='alert alert-danger'>$error1</div>";
                }
              ?>
              <input id="title" type='text' name='postTitle' value="<?php if(isset($error)){ echo $_POST['postTitle'];}?>" class="form-control">
            </div>
            <div class="form-group">
              <label for="des">Description</label><br />
              <?php
                if(isset($error['desc'])) {
                  $error2 = addslashes($error['desc']);
                  echo "<div class='alert alert-danger'>$error2</div>";
                }
              ?>
              <textarea id="des" name='postDesc' cols='60' rows='10' class="form-control"><?php if(isset($error)){ echo $_POST['postDesc'];}?></textarea>
            </div>
            <div class="form-group">
              <label for="con">Content</label><br />
              <?php
                if(isset($error['cont'])) {
                  $error3 = addslashes($error['cont']);
                  echo "<div class='alert alert-danger'>$error3</div>";
                }
              ?>
              <textarea id="con" name='postCont' cols='60' rows='10' class="form-control"><?php if(isset($error)){ echo $_POST['postCont'];}?></textarea>
            </div>
            <input id="submitbutton" type='submit' name='submit' value='Submit' class="btn btn-success marginBottom">
          </form>
        </div>
      </div>
    </div>
    <script>
      $(".myContainer").css("min-height",$(window).height());
    </script>
  </body>
</html>