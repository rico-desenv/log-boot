 
# Cadastro de Logs desenvolvido com Angular Typescript e Spring APi

## log-boot
Crud e Upload de logs

## Features
Listagem de registros com funcionalidades de inserção, alteração, exclusão e filtro por ip.
Assim como upload de arquivo log, processado assíncrono.

## Installation
### Pre Requisites
IDE (Eclipse recomendado)<br>
Spring Framework<br>
Node.js<br>
Npm Package Manager<br>
Angular 9 <br>
Material Design <br>
Bootstrap <br>

### Comands
Importar do projeto na IDE via maven.<br>
Após instalar pacote Node ( com npm ) https://nodejs.org/en/download/ , <br>
executar npm install -g @angular/cli. <br>
instalar pacotes angular  <br>
ng add @angular/material <br>
npm install bootstrap <br>

## How to use?
Rodar projeto via Spring Boot. ( Utiliza porta 8281 ) <br>
No Config.properties encontra-se diretório e nome  <br>
do arquivo a ser carregado <br>
Na pasta \src\main\resources\front, encontra-se o projeto angular.<br>
Executar ng serve, e no browser http://localhost:4200/logs  <br>
No environment.ts encontra-se o endpoint da api <br>
O upload grava o arquivo na pasta /log

## API Usage
## API Serviço Spring URL: 
http://localhost:8281/Log/listar?ip=192.10.1.1

## Endpoint
 GET: [/listar/ip]
 
##License
Released under the Apache-2.0 license.
