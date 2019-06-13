<?php

namespace App\Http\Controllers;

use App\Mutante;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

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
            $this->salvaNoStorage($mutante);

            return $salvo ? $this->enviaRespostaSucesso(['mutante' => $mutante]) : $this->enviaRespostaErro('Mutante não pôde ser salvo');
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    private function validaMutante(Mutante $mutante) {
        $mutante_existente = $this->buscaMutantePorNome($mutante->nome);
        return is_null($mutante_existente);
    }

    public function searchName(Request $request) {
        try {
            $mutante = $this->buscaMutantePorNome($request['nome']);
            return $this->enviaRespostaSucesso(['mutante' => $mutante]);
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
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

    /**
     * @param Mutante $mutante
     */
    public function salvaNoStorage(Mutante $mutante): void
    {
        $image = $mutante->foto;
        $image = str_replace('data:image/png;base64,', '', $image);
        $image = trim($image);
        $image = str_replace(' ', '+', $image);
        $imageName = 'mutante' . $mutante->id . '.' . 'png';
        Storage::disk('public')->put($imageName, base64_decode($image));
    }
}
