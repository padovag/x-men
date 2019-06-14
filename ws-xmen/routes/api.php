<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::get('/login', 'UsuarioController@auth');
Route::get('/usuarios', 'UsuarioController@index');
Route::post('/usuarios', 'UsuarioController@store');
Route::get('/mutantes', 'MutanteController@index');
Route::post('/mutantes', 'MutanteController@store');
Route::get('/pesquisar', 'MutanteController@search');
Route::get('/buscaMutantePorNome', 'MutanteController@searchName');
Route::get('/deletar', 'MutanteController@delete');
