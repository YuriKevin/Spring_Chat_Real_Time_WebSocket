# Spring Boot Chat em tempo real com WebSocket

##
Back-end em Java com o framework Spring Boot de um chat em tempo real utilizando WebSocket. Esta aplicação é complementada por um front-end em Angular que está em outro repositório do perfil:[ https://github.com/YuriKevin/Spring_Chat_WebSocket](https://github.com/YuriKevin/Angular_Chat_Real_Time_WebSocket)https://github.com/YuriKevin/Angular_Chat_Real_Time_WebSocket  <br>
Funcionamento do sistema: https://youtu.be/-J83J0FT1GU  <br>

## O que o back-end traz?
- É uma API RESTful organizada em camadas: modelo, serviço e controlador;
- Se comunica e modela o banco de dados (MySQL) a partir das entidades da camada de modelo;
- Recebe requisições HTTP GET, POST PUT e DELETE através de endpoints nos controladores;
- Permite a abertura de canais bidirecionais e persistentes de comunicação em tempo real com o cliente a partir de WebSockets;
- Recebe mensagens (sockets) de um usuário remetente e entrega para o usuário destinatário em tempo real;
- Guarda as informações do usuário e seus contatos;
- Impede a criação de usuários duplicados com o mesmo telefone;
- Permite gerar contatos anônimos caso um usuário mande mensagem para outro que não possui o remetente em sua agenda;
- Possibilita o login de usuários e retorna uma mensagem caso o telefone ou senha sejam incompatíveis;
- Verifica antes de realizar as operações na camada de serviço se o telefone está no formato necessário (11 digitos numéricos).
- Salva imagens na base 64 string no atributo foto do usuário;
