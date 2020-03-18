<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Laravel</title>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="{{ asset('css/app.css') }}">
    </head>
    <body>
        <div class="flex-center position-ref full-height">

            <div class="content">
                <div class="title m-b-md">
                    Iniciar Sesión
                </div>
                <div>                    
                    <input class=" m-b-md" type="text" name="login" placeholder="Usuario">
                </div>
                <div>                    
                    <input class="m-b-md" type="password" name="login" placeholder="Contraseña">
                </div>
                <div>
                    <button>Entrar</button>
                </div>
                
            </div>
        </div>
    </body>
</html>
