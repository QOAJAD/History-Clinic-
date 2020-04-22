<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>QOAJAD</title>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="{{ asset('css/app.css') }}">
    </head>
    <body>
        <div class="flex-center position-ref full-height">

            <div class="content">
                <div id="Login">
                    <div class="contenedor" id="title">
                        Iniciar Sesión
                    </div>
                    <div id="Iniciar" class="contenedor">
                        <div>                    
                        <input class=" m-b-md" type="text" name="login" placeholder="Usuario">
                        </div>
                        <div>                    
                            <input class="m-b-md" type="password" name="login" placeholder="Contraseña">
                        </div>
                        <div>
                            <button id="boton">Entrar</button>
                        </div>
                    </div>
                    
                </div>
                <div class="contenedor" id="imagen">
                    <div class="right-box"></div>
                    <button id="boton">Se supone que aquí esta la imagen</button>
                </div>
                
                
            </div>
        </div>
    </body>
</html>
