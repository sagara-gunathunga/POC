<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Kallol Chatterjee">
    <title>Landing Page</title>
        
    <link href="css/style.css" rel="stylesheet">    

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
</head>

<body>




    <!-- /.intro-header -->

    <!-- Page Content -->

	<a  name="post"></a>
	<div class="container">
    <div class="chat" style="overflow:auto;">
    <div class="divBorder" style="float:left; width:40%">
    <h1 class="btn btn-default btn-lg">POST BOOK !</h1>
        <form>
            <table class="chat" align="center">
                <tr>
                    <td class="chatForm">Your name:</td>
                    <td><input type="text" id="name" name="name"/></td>
                </tr>
                <tr>
                    <td class="chatForm">Your post:</td>
                    <td><input type="text" id="message" name="message" /></td>
                </tr>
                <tr>
                <td></td>
                    <td><input type="button" onclick="postMessage();" class="btn btn-default btn-lg" value="POST" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="divBorder" style="float:right; width:40%">
    <div align="center">
        <h2 class="btn btn-default btn-lg"> Current Posts </h2>
        <br>
        <div id="content" class="chat">
            <% if (application.getAttribute("messages") != null) {%>
            <%= application.getAttribute("messages")%>
            <% }%>
        </div>
        </div>
    </div>
    
    </div>
        <script>
        function fileSelected() {
        	  var file = document.getElementById('fileToUpload').files[0];
        	  if (file) {
        	  var fileSize = 0;
        	  if (file.size > 1024 * 1024)
        	  fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        	  else
        	  fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        	  document.getElementById('fileDetails').style.display = 'block';
        	  document.getElementById('fileName').innerHTML = 'File Details : <br> Name: ' + file.name;
        	  document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
        	  document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
        	   }
        	  }	
        function postMessage() {
	            var xmlhttp = new XMLHttpRequest();
	            xmlhttp.open("POST", "chatAsync?t="+new Date(), false);
	            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	            var nameText = escape(document.getElementById("name").value);
	            var messageText = escape(document.getElementById("message").value);
	            document.getElementById("message").value = "";
	            xmlhttp.send("name="+nameText+"&message="+messageText);
        	}
            var messagesWaiting = false;
            function getMessages(){
                if(!messagesWaiting){
                    messagesWaiting = true;
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange=function(){
                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                            messagesWaiting = false;
                            var contentElement = document.getElementById("content");
                            contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                        }
                    }
                    xmlhttp.open("GET", "chatAsync?t="+new Date(), true);
                    xmlhttp.send();
                }
            }
            setInterval(getMessages, 100);
        </script>
               <div class="container">
            

        </div>
        <!-- /.container -->

    </div>


</body>

</html>
       