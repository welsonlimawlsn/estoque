INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (1, TRUE, 'Permite o cadastro de um novo usuário', 'Criar Usuário', FALSE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (2, FALSE, 'Permite o usuário fazer login no sistema', 'Login', NULL);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (3, TRUE, 'Permite gerar uma nova chave de criptografia para token de acesso', 'Gerar Nova Chave de Criptografia do Token', FALSE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (4, TRUE, 'Permite consulta os grupos disponiveis', 'Consultar Grupos', TRUE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (5, TRUE, 'Permite criar um novo grupo de usuário', 'Criar Grupo', FALSE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (6, TRUE, 'Permite consultar as funcionalidades', 'Consultar funcionalidades', TRUE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (7, TRUE, 'Permite consultar os usuários', 'Consultar usuários', FALSE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (8, TRUE, 'Permite consultar detalhes de um usuário', 'Detalhes do usuário', FALSE);

INSERT INTO funcionalidade (codigo, autenticacao_necessaria, descricao, nome, funcionalidade_publica)
VALUES (9, TRUE, 'Permite emitir relatório da consulta de usuários', 'Emite relatório de usuários', FALSE);

insert into menu (id, caminho, descricao, nome, funcionalidade, menu_pai)
VALUES ('2', null, 'Funcionalidades para Usuário', 'Usuário', null, null);

insert into menu (id, caminho, descricao, nome, funcionalidade, menu_pai)
VALUES ('2_1', '/usuario/cadastro', 'Cadastro de Usuário', 'Cadastrar', 1, 2);

insert into menu (id, caminho, descricao, nome, funcionalidade, menu_pai)
VALUES ('2_2', '/usuario/consulta', 'Consulta de Usuário', 'Consultar', 7, 2);

insert into menu (id, caminho, descricao, nome, funcionalidade, menu_pai)
VALUES ('1', null, 'Funcionalidade para Grupos', 'Grupos', null, null);

insert into menu (id, caminho, descricao, nome, funcionalidade, menu_pai)
values ('1_1', '/grupo/novo', 'Crição de um novo grupo', 'Criar', 5, 1);

INSERT INTO parametro (codigo, descricao, valor)
VALUES (1, 'Tempo para expiração do token', '60');

INSERT INTO parametro (codigo, descricao, valor)
VALUES (3, 'Chave de criptografia token', '7I73r9EnGsJzJ6AmRTYTPohptWjLD+nE0oSZeSavPL8=');

INSERT INTO parametro (codigo, descricao, valor)
VALUES (4, 'Quantidade de itens padrão por página', '10');

INSERT INTO grupo (id, nome)
VALUES (1, 'Administrador');

INSERT INTO cliente (cpf, email, nome, senha, usuario, grupo_id)
VALUES ('01234567890', 'admin@gmail.com', 'Administrador', '$2a$10$kle86OWAygbZ8Z1Po6UAAuAd9TS1k4Wm2J/7sgM74OZKAY58.g0QS', 'admin',
        1);

INSERT INTO grupo_funcionalidade (id_grupo, codigo_funcionalidade)
SELECT 1, codigo
FROM funcionalidade
WHERE autenticacao_necessaria = TRUE
  AND funcionalidade_publica = FALSE
  AND (SELECT COUNT(*) FROM grupo_funcionalidade WHERE id_grupo = 1 AND codigo_funcionalidade = codigo) = 0;