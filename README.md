
---------------------------------------Solução-------------------------------------------------------

- Compilar BexsApplicationConsole.java
- javac BexsApplicationConsole.java
- Executar programa:
   * java BexsApplicationConsole input-routes.csv (Para executar essa função o JRE deverá está instalado e configurado no PATH. Além disso, a aplicação BexsApplication deverá ter sido iniciada, pois utilizo o serviço REST para inserção e consulta da melhor rota)

- Serviço REST -  iniciar BexsApplication
- POST http://localhost:8080/routes/list 
   * Inseri uma lista de rotas

[
{
    "origin": "GRU",
    "destine": "BRC",
    "cost": 10
},
{
    "origin": "BRC",
    "destine": "SCL",
    "cost": 5
},
{
    "origin": "GRU",
    "destine": "CDG",
    "cost": 75
},
{
    "origin": "GRU",
    "destine": "SCL",
    "cost": 20
},
{
    "origin": "GRU",
    "destine": "ORL",
    "cost": 56
},
{
    "origin": "ORL",
    "destine": "CDG",
    "cost": 5
},
{
    "origin": "SCL",
    "destine": "ORL",
    "cost": 20
},
{
    "origin": "GRU",
    "destine": "CDG",
    "cost": 50
}
]

- Consulta list rotas
   * GET http://localhost:8080/routes/
   * ou  http://localhost:8080/routes/route/GRU/CDG

- Consultar melhor route na lista

   * GET http://localhost:8080/routes/route/GRU/CDG
	

- Estrutura de pastas

paulodiniz@linux:~/IdeaProjects/bexs/src/main/java/br/com/bexs/travel/route$ ll
total 52
drwxrwxrwx 8 paulodiniz paulodiniz 4096 abr 14 14:07 ./
drwxrwxrwx 3 paulodiniz paulodiniz 4096 abr  2 22:31 ../
-rwxrwxrwx 1 paulodiniz paulodiniz  918 abr  2 23:34 BexsApplicationConsole.class*
-rw-rw-r-- 1 paulodiniz paulodiniz 3130 abr 14 14:07 BexsApplicationConsole.java
-rwxrwxrwx 1 paulodiniz paulodiniz  313 abr  2 20:37 BexsApplication.java*
drwxrwxrwx 2 paulodiniz paulodiniz 4096 abr 10 16:06 controller/
drwxrwxrwx 2 paulodiniz paulodiniz 4096 abr  2 20:52 dto/
drwxrwxr-x 2 paulodiniz paulodiniz 4096 abr 14 14:17 engine/
-rwxrwxrwx 1 paulodiniz paulodiniz   75 abr  2 23:27 input-routes_bck.csv*
-rwxrwxrwx 1 paulodiniz paulodiniz   11 abr  3 01:30 input-routes.csv*
drwxrwxrwx 2 paulodiniz paulodiniz 4096 abr 10 16:42 model/
drwxrwxrwx 2 paulodiniz paulodiniz 4096 abr  2 20:49 repository/
drwxrwxrwx 2 paulodiniz paulodiniz 4096 abr  8 18:50 service/
paulodiniz@linux:~/IdeaProjects/bexs/src/main/java/br/com/bexs/travel/route$ 


_____________________________________________________________________________________________________________________________________________


# Rota de Viagem #

Um turista deseja viajar pelo mundo pagando o menor preço possível independentemente do número de conexões necessárias.
Vamos construir um programa que facilite ao nosso turista, escolher a melhor rota para sua viagem.

Para isso precisamos inserir as rotas através de um arquivo de entrada.

## Input Example ##
```csv
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,5
SCL,ORL,20
```

## Explicando ## 
Caso desejemos viajar de **GRU** para **CDG** existem as seguintes rotas:

1. GRU - BRC - SCL - ORL - CDG ao custo de **$40**
2. GRU - ORL - CGD ao custo de **$64**
3. GRU - CDG ao custo de **$75**
4. GRU - SCL - ORL - CDG ao custo de **$48**
5. GRU - BRC - CDG ao custo de **$45**

O melhor preço é da rota **4** logo, o output da consulta deve ser **CDG - SCL - ORL - CDG**.

### Execução do programa ###
A inicializacao do teste se dará por linha de comando onde o primeiro argumento é o arquivo com a lista de rotas inicial.

```shell
$ mysolution input-routes.csv
```

Duas interfaces de consulta devem ser implementadas:
- Interface de console deverá receber um input com a rota no formato "DE-PARA" e imprimir a melhor rota e seu respectivo valor.
  Exemplo:
  ```shell
  please enter the route: GRU-CGD
  best route: GRU - BRC - SCL - ORL - CDG > $40
  please enter the route: BRC-CDG
  best route: BRC - ORL > $30
  ```

- Interface Rest
    A interface Rest deverá suportar:
    - Registro de novas rotas. Essas novas rotas devem ser persistidas no arquivo csv utilizado como input(input-routes.csv),
    - Consulta de melhor rota entre dois pontos.

Também será necessária a implementação de 2 endpoints Rest, um para registro de rotas e outro para consula de melhor rota.

## Recomendações ##
Para uma melhor fluides da nossa conversa, atente-se aos seguintes pontos:

* Envie apenas o código fonte,
* Estruture sua aplicação seguindo as boas práticas de desenvolvimento,
* Evite o uso de frameworks ou bibliotecas externas à linguagem. Utilize apenas o que for necessário para a exposição do serviço,
* Implemente testes unitários seguindo as boas praticas de mercado,
* Documentação
  Em um arquivo Texto ou Markdown descreva:
  * Como executar a aplicação,
  * Estrutura dos arquivos/pacotes,
  * Explique as decisões de design adotadas para a solução,
  * Descreva sua APÌ Rest de forma simplificada.


