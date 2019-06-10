<?php

namespace App\Http\Controllers;

use App\Mutante;
use Illuminate\Http\Request;

class MutanteController extends ApiController {
    public function index() {
        try {
            $mutantes = Mutante::all();
            return $this->enviaRespostaSucesso(['mutantes' => $mutantes]);
        } catch (\Exception $exception) {
            $this->enviaRespostaErro($exception->getMessage());
        }
    }

    public function store(Request $request) {
        try {
            $mutante = new Mutante();
            $mutante->nome = $request['nome'];
            $mutante->habilidade = $request['habilidade'];
            $mutante->foto = $request['foto'];
            $mutante->usuario_id = $request['usuario_id'];
            $salvo = $mutante->save();

            return $salvo ? $this->enviaRespostaSucesso(['mutante' => $mutante]) : $this->enviaRespostaErro('Mutante não pôde ser salvo');
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }
}
