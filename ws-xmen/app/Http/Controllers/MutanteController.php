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
            if (!$this->validaMutante($mutante)) {
                return $this->enviaRespostaErro("Mutante {$mutante->nome} já existente", 409);
            }
            $salvo = $mutante->save();

            return $salvo ? $this->enviaRespostaSucesso(['mutante' => $mutante]) : $this->enviaRespostaErro('Mutante não pôde ser salvo');
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    private function validaMutante(Mutante $mutante) {
        $mutante_existente = $this->buscaMutantePorNome($mutante->nome);
        return is_null($mutante_existente);
    }

    private function buscaMutantePorNome(string $nome) {
        return Mutante::where('nome', $nome)->first();
    }

    public function search(Request $request) {
        try {
            $mutantes = $this->buscaMutantePorHabilidade($request['habilidade']);
            return $this->enviaRespostaSucesso(['mutantes' => $mutantes]);
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    private function buscaMutantePorHabilidade($habilidade) {
        return Mutante::where('habilidade', 'like', "%{$habilidade}%")->get();
    }
}
