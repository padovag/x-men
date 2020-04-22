<?php

namespace App\Http\Controllers;

use App\Mutante;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;
use DB;
use Carbon\Carbon;

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

    public function deletaMutante(Request $request){
         try {
            $status = $this->buscaMutanteDeletar($request['id']);
            if($status == 1){
                return $this->enviaRespostaSucesso(['Mutante deletado' => $request['id']]); 
            }else{
                return $this->enviaRespostaErro('Ocorreu um erro ao deletar o mutante');
            }
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    public function atualizaMutante(Request $request){
         try {
            $status = $this->buscaMutanteAtualizar($request['id'],$request['nome'], $request['habilidade'], $request['foto']);
            if($status == 1){
                return $this->enviaRespostaSucesso(['Mutante atualizado' => $request['id']]);
            }else{
                return $this->enviaRespostaErro('Ocorreu um erro ao atualzar o mutante');
            }
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

    private function buscaMutanteDeletar($id){
        //Alterar nome da Tabela 
        return DB::table('mutantes')->where('id', '=', $id)->delete();
    }

    private function buscaMutanteAtualizar($id, $nome, $habilidade, $foto){
        //Alterar nome da Tabela 
        $status = DB::table('mutantes')
            ->where('id', $id)
            ->update(['nome' => $nome, 'habilidade' => $habilidade, 'foto' => $foto, 'updated_at' => Carbon::now()->toDateTimeString()]);

            return $status;
    }

    /**
     * @param Mutante $mutante
     */
    private function salvaNoStorage(Mutante $mutante): void{
        $image = $mutante->foto;
        $image = str_replace('data:image/png;base64,', '', $image);
        $image = trim($image);
        $image = str_replace(' ', '+', $image);
        $imageName = 'mutante' . $mutante->id . '.' . 'png';
        Storage::disk('public')->put($imageName, base64_decode($image));
    }

    public function delete(Request $request){
        try {
            $mutante = $this->buscaMutantePorId($request['id']);
            if (!$mutante) {
                return $this->enviaRespostaErro('O mutante enviado não existe', 200);
            }
            $deletado = $mutante->delete();
            if(!$deletado) {
                return $this->enviaRespostaErro('Ocorreu um erro ao deletar o mutante', 200);
            }
            return $this->enviaRespostaSucesso(['mutante_id' => $request['id']]);
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    private function buscaMutantePorId($id){
        return Mutante::where('id', $id)->first();
    }
}
