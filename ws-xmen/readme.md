# API X-Men
Api criada para ser consumida pelo aplicativo Android.

## Setup
#### Repositório
* ```git clone https://github.com/padovag/x-men/edit/master/ws-xmen/readme.md```

#### Dependências
* Instale o composer
```php composer-setup.php --install-dir=/usr/local/bin --filename=composer```

* Instale o laravel
```composer global require laravel/installer```

* Instale o MySQL
```brew install mysql```

#### Preparar o ambiente 
* Rode as migrations ```php artisan migrate```
* Rode os seeders ```php artisan db:seed```

#### Levante a aplicação
* ```php artisan serve```

A aplicação vai começar a rodar em http://127.0.0.1:8000/
