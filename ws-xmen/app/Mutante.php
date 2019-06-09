<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Mutante extends Model {
    public $fillable = ['nome', 'habilidade', 'foto', 'usuario_id'];
    public $table = 'mutantes';
}
