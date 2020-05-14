<!DOCTYPE html>
<html lang="en">

<head>


    <!-- Title Page-->
    <title>Login</title>

    <!-- Icons font CSS-->
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">


    <!-- Main CSS-->
    <link rel="stylesheet" href="{{ asset('css/login.css') }}">
</head>

<body>
    <div class="page-wrapper p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">Iniciar Sesi&#243;n</h2>
                    <form method="POST">

                        <div class="row row-space">
                            <div class="input-group">
                                <input class="input--style-2" type="text" placeholder="Usuario" name="Usuario">
                            </div>
                        </div>

                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                     <input class="input--style-2" type="password" placeholder="Contrase&ntilde;a" name="Contraseña">
                                </div>
                            </div>
                        </div>
                        <div class="registro">
                        Si no tienes cuenta, haz click en <a href="resources/views/registro.blade.php">Registrar</a>
                        </div>
                        <div class="p-t-30">
                            <button class="btn btn--radius" type="submit">Ingresar</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


</body>

</html>