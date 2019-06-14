<?php

namespace App\Http\Controllers;

use App\Usuario;
use Illuminate\Http\Request;

class UsuarioController extends ApiController {
    public function index() {
        try {
            $usuarios = Usuario::all();
            return $this->enviaRespostaSucesso(['usuarios' => $usuarios]);
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    public function store(Request $request) {
        try {
            $usuario = new Usuario();
            $usuario->nome = $request['nome'];
            $usuario->usuario = $request['usuario'];
            $usuario->email = $request['email'];
            $usuario->senha = $request['senha'];

            $salvo = $usuario->save();
            return $salvo ? $this->enviaRespostaSucesso(['usuario' => $usuario]) : $this->enviaRespostaErro('Usuário não pôde ser salvo');
        } catch (\Exception $exception) {
            return $this->enviaRespostaErro($exception->getMessage());
        }
    }

    public function auth(Request $request) {
        $usuario = $this->buscaPorUsuario($request['usuario']);
        if (empty($usuario)) {
            return $this->enviaRespostaSucesso(['autorizado' => false, 'mensagem' => 'Usuario não encontrado']);
        }

        $autorizado = $this->verificaSenhaUsuario($usuario, $request['senha']);
        if (!$autorizado) {
            return $this->enviaRespostaSucesso(['autorizado' => false, 'mensagem' =>'Senha incorreta']);
        }

        return $this->enviaRespostaSucesso(['autorizado' => $autorizado, 'usuario' => $usuario]);
    }

    public function buscaPorUsuario(string $usuario) {
        return Usuario::where('usuario', $usuario)->first();
    }

    private function verificaSenhaUsuario($usuario, $senha) {
        return ($senha == $usuario->senha);
    }
}
