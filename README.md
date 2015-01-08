# Livro Algaworks - Estudo do fabiogm6
https://github.com/fabiogm6/P05_fgm_ebook-jsf-financeiro.git
Java EE 7 com JSF, PrimeFaces e CDI
por Thiago Faria
Edição de 24/12/2013

feito os exercicios do livro
funcionando ! Thanks para Algaworks ! Muito bom !

==> Este P05 
- separei as funcionalidades de cadastro e lista de tabelas/crud. Fiz outro projeto P06 que contém os outros exercicios do livro; 

- inclui funcionalidade de Paginação de DataTable do PrimeFaces com Lazy Loading conforme:
  https://github.com/fabiogm6/P05_fgm_ebook-jsf-financeiro.git


para ajudar peguei codigo do GitHub que só tem datatable simples:
https://github.com/algaworks/ebook-jsf-financeiro
e fui comparando com minha evolução. Depois deichei a minha evolução igual.
A maior diferenciação é que não usa 'library' algaworks no código e sim 'fgm'.

url local de acesso:
contexto para P05
http://localhost:8080/P05/Login.xhtml

Login: admin
pw: 123

para funcionar:

1) ir no persistente.xml e mudar o acesso para o MySQL (user e pw);

2) criar 2 tabelas no MySQL

    a) lancamento
    
    CREATE TABLE `lancamento` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `pessoa_id` bigint(20) NOT NULL,
      `descricao` varchar(80) NOT NULL,
      `tipo` varchar(255) NOT NULL,
      `valor` decimal(10,2) DEFAULT NULL,
      `data_pagamento` date DEFAULT NULL,
      `data_vencimento` date DEFAULT NULL,
      PRIMARY KEY (`id`)


    b) pessoa
    
    CREATE TABLE `pessoa` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `nome` varchar(60) NOT NULL,
      PRIMARY KEY (`id`)


