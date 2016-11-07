<?php
  session_start();
  include ("connection.php");
  $email = $_GET["token"];
  if($_SESSION[$email] != "true") {
    header("Location:signup.php?status=expired&email=$email");
    exit();
  }
  if($_POST["submit"] == "Reset") {
    if($_POST["password"]) {
      $password = $_POST["password"];
      $confirmpassword = $_POST["confirm-password"];
      if(strlen($password) < 8) {
        $error .= "The length of password should not be less than 8.<br />";
      }
      if(!preg_match("/[A-Z]+/", $password)) {
        $error .= "The password should include at least one capital letter.<br />";
      }
      if($password != $confirmpassword) {
        $error .= "These two are not matched.<br />";
      }
    }
    else {
      $error .= "Please enter your password.<br />";
    }
    if($error) {
      $error = $error;
    }
    else {
      $query = "UPDATE Info SET Info_Password = '".md5(md5($_GET["token"]).($_POST["password"]))."' WHERE Info_Email = '".mysqli_real_escape_string($link, $_GET["token"])."'";
      $result = mysqli_query($link, $query);
      if($result) {
        $_SESSION[$email] = "false";
        header("Location:signup.php");
        exit();
      }
      else {
        $error .= "It failed!";
      }
    }
  }
?>