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

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#">SER 598 demo !!!</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#about">About</a>
                    </li>
                    <li>
                        <a href="#post">Post</a>
                    </li>
                    <li>
                        <a href="#contact">Contact</a>
                    </li>
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


    <!-- Header -->
    <a name="about"></a>
    <div>
        <div class="container">

            <div class="row">
                <div class="intro-header">
                    <div>
                        <h1>Welcome!</h1>
                        <h3>To the demonstration of asynchronous processing in Java Servlet Specification !</h3>
                        <hr class="intro-divider">
                        <ul class="list-inline intro-social-buttons">
                            <li>
                                <a href="https://youtu.be/NBBx83WHSxY" class="btn btn-default btn-lg"><i class="fa fa-youtube fa-fw"></i> <span class="network-name">YouTube</span></a>
                            </li>
                            <li>
                                <a href="https://github.com/KallolChatterjee/SER598_AsynchronousServlet.git" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                            </li>
                            <li>
                                <a href="https://drive.google.com/folderview?id=0BzHEgLK9Q-ssRGQtRm9xb2FxRDQ&usp=sharing" class="btn btn-default btn-lg"><i class="fa fa-google fa-fw"></i> <span class="network-name">Google Docs</span></a>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->

	<a  name="post"></a>
	<div class="container">
    <div class="chat" style="overflow:auto;">
    <div class="divBorder" style="float:left; width:40%">
  <h1 class="btn btn-default btn-lg">POST BOOK !</h1>
        <form method="POST" action="chat">
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
                    <td><input type="submit" class="btn btn-default btn-lg" value="POST" /></td>
                </tr>
            </table>
        </form>
        </div>
    <div class="divBorder" style="float:right; width:40%">
    <div align="center">
        <h2 class="btn btn-default btn-lg"> Current Posts </h2>
        <br>
        <div id="content" class="chat">
            <% if (application.getAttribute("Syncmessages") != null) {%>
            <%= application.getAttribute("Syncmessages")%>
            <% }%>
            </div>
        </div>
    </div>
    
    </div>
        <div class="container">
            

        </div>
        <!-- /.container -->

    </div>
	<a  name="contact"></a>
    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-6">

                </div>

                <div class="col-lg-6">
                    <ul class="list-inline banner-social-buttons">
                        <br>
                        <br>
                        <li>
                            <a href="https://www.linkedin.com/in/kallolchatterjee" class="btn btn-default btn-lg" onclick="clickASU()"> <span class="network-name">Kallol Chatterjee</span></a>
                        </li>
						<br>
						<br>                        
                        <li>
                            <a href="https://www.linkedin.com/in/harish-malik-ab6557107" class="btn btn-default btn-lg"> <span class="network-name">Harish Malik</span></a>
                        </li>
                        <br>
                        <br>
                        <li>
                            <a href="https://www.linkedin.com/in/abhilashmalla" class="btn btn-default btn-lg"> <span class="network-name">Abhilas Malla</span></a>
                        </li>
                        <br>
                        <br>
                        <li>
                            <a href="https://www.linkedin.com/in/mihirrathwa" class="btn btn-default btn-lg" > <span class="network-name">Mihir Rathwa</span></a>
                        </li>
                        <br>
                        <br>
                    </ul>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.banner -->

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a href="#">Home</a>
                        </li>
                        <li>&sdot;</li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                        <li>&sdot;</li>
                        <li>
                            <a href="#chat">Chat</a>
                        </li>
                        <li>&sdot;</li>
                        <li>
                            <a href="#contact">Contact</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>

</body>

</html>
       