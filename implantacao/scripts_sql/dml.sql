insert into funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (1, true, 'Permite o cadastro de um novo usuário', 'Novo Usuário', false);

insert into funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (2, false, 'Permite o usuário fazer login no sistema', 'Login', null);

insert into funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (3, true, 'Permite gerar uma nova chave de criptografia para token de acesso', 'Gerar Nova Chave de Criptografia do Token', false);

insert into parametro (codigo, descricao, valor)
VALUES (1, 'Tempo para expiração do token', '60');

update funcionalidade
set autenticacao_necessaria = false
where codigo in (1, 3);