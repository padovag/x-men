# Trabalho 2 Android
## WS Mutantes
O Instituto Internacional de Mutantes solicitou o desenvolvimento de um aplicativo CRUD
de mutantes. O aplicativo tem por objetivo cadastrar todos os mutantes encontrados, bem como
permitir consulta rápida dos mesmos.

Para este trabalho o Instituto pede que seja desenvolvido o backend de um sistema Web
com acesso a banco de dados, o banco de dados, o aplicativo Android e serviços Web para que
o aplicativo acesse o servidor.

## Requisitos do Sistema


* **_SplashScreen_**:

**Somente aplicativo**. Tela de apresentação do sistema que permite ao desenvolvedor
mostrar sua marca, deve ser mostrada por apenas 3 segundos.

* **_Autenticação_**:

**BD** – campos login e senha. Cadastre previamente 3 usuários. Novos usuários serão
cadastrados durante apresentação, diretamente no BD.
**Sistema Web** – serviços de autenticação que recebam do app os campos login e senha e
consultem o BD para identificação do usuário. Resposta positiva ou negativa deve ser fornecida
ao app.
**App** – deve fornecer uma maneira para capturar informações do usuário de Login e Senha,
consumir o serviço Web do sistema e apresentar em um AlertDialog a mensagem de “Login ou
Senha incorretos” em caso de identificação negativa.

* **_Activity Dashboard_**

**Somente aplicativo.** A activity dashboard é a activity principa. Ela possui uma view com
botões que devem levar o usuário a cadastrar novos mutantes, listar todos, pesquisar mutantes
por habilidades, e sair.

* **_Cadastro de novos mutantes_**

**BD** – guardar as informações nome do mutante, habilidades do mutante, foto do mutante
e nome do usuário que cadastrou o mutante. 
**Sistema Web** – fornecer os serviços Web para cadastro do mutante, bem como verificar se
aquele mutante já existe na base (em caso positivo, não permitir duplicidade). Retorno do serviço
deve informar se o usuário foi cadastrado corretamente ou se houve erro e qual erro ocorreu.
**App** – deve permitir ao usuário cadastrar apenas novos mutantes, não permitindo o
cadastro de um mutante de mesmo nome. Além do nome, o usuário deve cadastrar habilidades
conhecidas daquele mutante (mínimo 1, máximo 3) e associar uma foto a este mutante (acessar
a biblioteca de fotos do Android). A informação do usuário é dada por seu Login. Por fim, deve
permitir que o usuário possa acessar a ação de upload da informação, bem como apresentar um
AlertDialog informando o resultado desta operação (dado pelo retorno do serviço Web).

* **_Activity Listar Todos_**

**Sistema Web** – fornece os serviços Web para execução da pesquisa de todos os mutantes
na base de dados do servidor, edição e exclusão de um determinado mutante.
**App** – deve mostrar em uma lista a foto em tamanho pequeno, bem como o nome, dos
mutantes recebidos pelo serviço Web e atualmente cadastrados na base do servidor. Ao clicar
em um mutante, uma nova activity é mostrada trazendo maiores informações daquele mutante,
todos os campos cadastrados devem ser mostrados (exceto id), inclusive a foto. A activity de
detalhes deve ainda permitir que o usuário edite detalhes do mutante, atualizando estas
informações na base, ou excluindo este mutante da base.

* **_Activity Pesquisar_**

**Sistema Web** – fornece os serviços Web para pesquisa de mutantes por habilidade (apenas
uma habilidade por vez). Retorna o nome dos mutantes com aquela habilidade (usar Like na
statement de SQL).
**App** – deve permitir que o usuário pesquise mutantes por uma certa habilidade. A lista de
resposta, com apenas os nomes dos mutantes, deve ser mostrada na tela.

* **_Sair_**

**Somente app**. Fecha o aplicativo.

### Observações
* Qualquer validação necessária fica a cargo dos desenvolvedores, sendo item de desconto
caso alguma validação não seja realizada e apresente um problema para a lógica ou
execução do aplicativo.
* O cadastro de um mutante deve obedecer a todas as restrições necessárias, inclusive o de 
retorno de mensagem de Cadastro com Sucesso ou Erro ao Cadastrar.
* Cadastre previamente pelo menos 10 mutantes.
o Utilize o site https://pt.wikipedia.org/wiki/Categoria:Personagens_de_X-Men para
pegar dados de mutantes.
* O valor deste trabalho é de 50% da nota final;
* Pode ser realizado em grupos de 2 ou 3;
* A data da entrega e apresentação/defesa do trabalho está no moodle, e será realizada
durante a aula. Caso uma equipe não compareça na data marcada, automaticamente
receberá a nota 0. Caso um integrante não compareça na apresentação, o mesmo
receberá nota 0.
* Não será permitido o desenvolvimento de funcionalidades ou correção de bugs no dia da
defesa.
* Não será tolerado qualquer tipo de cópia, acarretando em nota 0 para todos os integrantes
envolvidos.
* Usar a API 19 (4.4 - KitKat) do Android e simular em smartphone com tela de 4 polegadas,
como o Nexus S.
* Envie um link compartilhando o projeto na nuvem ou link no github para
brawerman@ufpr.br, com o título WS Mutantes. Inclua nome completo e matrícula no
dos integrantes corpo do email.

### Critérios de avaliação
* Activity SplashScreen com funcionamento incorreto – perda de 0,5 ponto;
* Activity Dashboard com funcionamento incorreto – perda de 0,5 ponto;
* Activity de Autenticação, bem como serviços Web de autenticação, funcionando
corretamente – 0,5 ponto;
* Activity de Cadastro, bem como serviços Web de cadastro, com mutante sendo
registrado corretamente e nomes repetidos não sendo permitidos – 1,0 ponto;
* Activity Listar todos, bem como serviços Web relacionados a esta função, com lista
completa de mutantes cadastrados (foto e nome) e levando corretamente a tela de
detalhes – 1,0 ponto;
o Activity de Detalhes do mutante mostrando as informações corretamente,
permitindo edição e atualização de dados, além da exclusão de um mutante, e
serviços Web relacionados – 1,5 pontos.
* Activity Pesquisar com funcionamento correto, montando a lista conforme resultado
pesquisado – 1,0 ponto.
* Decréscimo de 1,0 ponto caso o grupo não consiga implementar as funções relacionadas
a foto, sendo elas acesso à biblioteca de fotos do Android, cadastro da foto no servidor
(pode ser gravada a foto no servidor e o local da foto no BD ou gravar a foto direto no
BD), e a foto do mutante não aparecer na lista de pesquisa todos.
* Botão sair não fechando o aplicativo – perda de 0,5 pontos.
