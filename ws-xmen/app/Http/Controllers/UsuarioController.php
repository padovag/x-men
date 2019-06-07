<?php

namespace App\Http\Controllers;

use App\Usuario;
use Illuminate\Http\Request;

class UsuarioController extends Controller {
    public function index() {
        $usuarios = Usuario::all();
        return response()->json(['usuarios' => $usuarios]);

    }

    public function store(Request $request) {
        $usuario = new Usuario();
        $usuario->nome = $request['nome'];
        $usuario->usuario = $request['usuario'];
        $usuario->email = $request['email'];
        $usuario->senha = $request['senha'];

        $saved = $usuario->save();
        $response = $saved ? 'Usuario salvo com sucesso' : 'Usuario não pode ser salvo';

        return response()->json(['message' => $response, 'id' => $usuario->id]);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Usuario  $usuario
     * @return \Illuminate\Http\Response
     */
    public function show(Usuario $usuario)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Usuario  $usuario
     * @return \Illuminate\Http\Response
     */
    public function edit(Usuario $usuario)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Usuario  $usuario
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Usuario $usuario)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Usuario  $usuario
     * @return \Illuminate\Http\Response
     */
    public function destroy(Usuario $usuario)
    {
        //
    }
}
