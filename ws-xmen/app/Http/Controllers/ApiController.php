<?php

namespace App\Http\Controllers;

class ApiController extends Controller {
    public function enviaRespostaSucesso($dataset = null, $status = 200) {
        $dataset['resposta'] = 'Operação realizada com sucesso';
        return response()->json($dataset, $status);
    }

    public function enviaRespostaErro($erro, $status_code = 503) {
        $dataset = [
            'mensagem' => 'A operação não foi bem sucedida',
            'erro' => $erro
        ];

        return response()->json($dataset, $status_code);
    }
}