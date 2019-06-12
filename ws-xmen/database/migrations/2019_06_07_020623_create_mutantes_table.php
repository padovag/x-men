<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateMutantesTable extends Migration
{
    public function up()
    {
        Schema::create('mutantes', function (Blueprint $table) {
            $table->unsignedInteger('id')->autoIncrement();
            $table->string('nome')->unique();
            $table->text('habilidade');
            $table->mediumText('foto');
            $table->unsignedInteger('usuario_id');
            $table->foreign('usuario_id')->references('id')->on('usuarios');
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('mutantes');
    }
}
