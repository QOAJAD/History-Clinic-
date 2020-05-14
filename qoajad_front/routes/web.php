<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/


Route::get('/', function () {
    return view('Login');
});


Route::get('resources/views/registro.blade.php', function() {
	return view('registro');
});

Route::get('resources/views/login.blade.php', function() {
	return view('Login');
});

// Route::get('/name/{name}/lastname/{lastname?}', function($name, $lastname = NULL){
//     return 'HELLO ' .$name . $lastname;
// });


// Route::get('http://18.221.11.31:9090/authentication/authenticate/{username}/{password}', 
// function($username = 'juan.2114@hotmail.com', $password = 'password'){
//     return $username. $password;
// });
