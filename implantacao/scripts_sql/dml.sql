insert into funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (1, true, 'Permite o cadastro de um novo usuário', 'Novo Usuário', false);

update funcionalidade set autenticacao_necessaria = false where codigo = 1;