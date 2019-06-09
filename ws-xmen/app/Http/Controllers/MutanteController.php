<?php

namespace App\Http\Controllers;

use App\Mutante;
use Illuminate\Http\Request;

class MutanteController extends Controller
{
    public function index() {
        $mutantes = Mutante::all();
        return response()->json(['mutantes' => $mutantes]);
    }

    public function store(Request $request) {
        $mutante = new Mutante();
        $mutante->nome = $request['nome'];
        $mutante->habilidade = $request['habilidade'];
        $mutante->foto = $request['foto'];
        $mutante->usuario_id = $request['usuario_id'];
        $salvo = $mutante->save();
        $resposta = $salvo ? 'Mutante salvo com sucesso' : 'Não foi possível salvar o mutante';

        return response()->json(['mensagem' => $resposta, 'mutante_id' => $mutante->id]);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Mutante  $mutante
     * @return \Illuminate\Http\Response
     */
    public function show(Mutante $mutante)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Mutante  $mutante
     * @return \Illuminate\Http\Response
     */
    public function edit(Mutante $mutante)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Mutante  $mutante
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Mutante $mutante)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Mutante  $mutante
     * @return \Illuminate\Http\Response
     */
    public function destroy(Mutante $mutante)
    {
        //
    }
}
