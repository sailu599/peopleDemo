<html>
    <head>
        <title>
            login page
        </title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./javaScript/authenticate.js"></script>
    </head>
    <body>
        <div style="margin-top: 200px;margin-left: 40%;">
        <h5>login</h5>
        <form id="loginForm">
            <div class="row">
                <label class="col col-3">userName</label>
                <input class="col col-3" type="text" name="userName">
            </div>
            <br>
            <div class="row">
                <label class="col col-3">password</label>
                <input class="col col-3" type="password" name="password">
            </div>
            <input type="submit" onclick="authenticate(event)">
        </form>
        </div>
    </body>
</html>