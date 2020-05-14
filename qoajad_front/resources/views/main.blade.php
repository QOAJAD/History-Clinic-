<!DOCTYPE html>
<html lang="en">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>QOAJAD</title>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="{{ asset('css/main.css') }}">
    </head>

    <body>         
        <section id="HomePage">

            <!--- MENÚ DE ARRIBA --->
            <header>
                <ul>         
                    <li><a href="#">Inicio</a></li>
                    <li><a href="#">Citas</a>
                        <ul>
                            <li><a href="#">Crear cita</a></li>
                            <li><a href="#">Ver citas</a></li>
                            <li><a href="#">Modificar citas</a></li>
                        </ul>           
                    </li>

                    <li><a href="#">Historia</a></li>    
                </ul>
            </header>

            <!--- MENÚ LATERAL -->
            <nav>
                <H1>QOAJAD</H1>
                <div>LOGO</div>
                <div>Nombre</div>
                <div>Configuración</div>
                <div>Calendario</div>
            </nav>

            <!--- CONTENIDO PRINCIPAL -->

            <main>
                @yield('content')
            </main>
        </section>
    </body>

</html>