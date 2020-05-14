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
        <link rel="stylesheet" href="{{ asset('css/citas.css') }}">
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
                <h1>QOAJAD</h1>
                <div>LOGO</div>
                <div>Nombre</div>
                <div>Configuración</div>
                <div id="cal">
                    <div class="header"> 
                        <span class="left button" id="prev"> &lang; </span> 
                        <span class="left hook"></span> 
                        <span class="month-year" id="label"> June 2020 </span> 
                        <span class="right hook"></span> 
                        <span class="right button" id="next"> &rang; </span>
                    </div> 
                    <table id="days"> 
                        <td>Dom</td> 
                        <td>Lun</td> 
                        <td>Mar</td> 
                        <td>Mie</td> 
                        <td>Jue</td> 
                        <td>Vier</td> 
                        <td>Sab</td>


                    </table> 
                    <div id="cal-frame"> 
                        <table class="curr"> 
                        <tbody> 
                            <tr><td class="nil"></td><td class="nil"></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td></tr> 
                            <tr><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td class="today">11</td><td>12</td></tr> 
                            <tr><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td></tr> 
                            <tr><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td></tr> 
                            <tr><td>27</td><td>28</td><td>29</td><td>30</td><td class="nil"></td><td class="nil"></td><td class="nil"></td></tr> 
                        </tbody> 
                    </table>

                    </div> 
                </div>
            </nav>

            <!--- CONTENIDO PRINCIPAL -->

            <main>
                @yield('content')
            </main>
        </section>
    </body>

</html>