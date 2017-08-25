<?
require "conn.php";
if ($stmt = $conn->prepare("SELECT increment
    FROM test)
    {
      $stmt->execute();
      $stmt->bind_result($count);
      $stmt->fetch();
      $stmt->close();
    }
echo $count;
$count++;
echo $count;
