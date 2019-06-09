<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Usuario extends Model
{
    public $fillable = ['nome','usuario','email','senha'];
    public $table = 'usuarios';
}
