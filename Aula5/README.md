# Cliente
## POST - /cliente
* Para cadastrar um cliente deve ser informado o nome e o CPF.
* Não é possível cadastrar dois clientes com o mesmo CPF. 

## GET - /cliente
* Retorna uma lista de clientes.

## GET - /cliente/{id}
* Retorna o cliente que tenha o id passado.

# Filme
## POST - /filme
* Para cadastrar um filme deve ser informado o título do filme e a categoria.
* Não é possível cadastrar mais de um filme com o mesmo título.
* Todas categorias contém um valor de locação e um prazo de entrega.
```
Categoria(valor, prazo)
```
### Categorias:
1. *DOURADA (10.00, 2)*
2. *PRATA (7.00, 3)*
3. *BRONZE (3.00, 5)*

## GET - /filme
* Retorna uma lista de filmes

## GET - /filme{id}
* Retorna o filme que tenha o id passado.

# Fita
## POST - /fita
* Para cadastrar uma fita deve ser informado o título do filme a que pertence.
* Não é possível cadastrar fitas cujo título não corresponda a nenhum filme
* Todas fitas cadastradas recebem uma situação de DISPONIVEL
### Situações das fitas:
1. *DISPONIVEL*
2. *LOCADA*

## GET - /fita
* Retorna uma lista de fitas

## GET - /fita{id}
* Retorna fita que tenha o id passado.

# Pedido
## POST - /pedido/abrirPedido
* Para poder locar filmes deve ser aberto um pedido novo.
* Para abrir um pedido deve ser informado o CPF do cliente que está abrindo o pedido.
* Todos pedidos abertos recebem um situação de *EM_USO*.
* Não é possível abrir dois pedidos com o mesmo CPF caso um deles conste em um pedido que tenha a situação de *EM_USO* ou *ATIVO*

## PUT - /pedido/fecharPedido/{id}
* Para fechar um pedido deve ser informa o id do pedido.
* O valor total do pedido será calculado após o mesmo ser fechado.
* Pedidos fechados recebem uma situação de *ATIVO*.

## GET - /pedido
* Retorna uma lista de pedidos.

## GET - /pedido/{id}
* Retorna o pedido que tenha o id passado.

## GET - /pedido/cliente/{cpf}
* Retorna os pedidos do cliente que tenha o CPF passado.

# Locação
## POST - /locacao
* Para fazer uma locação deve ser informado o id do pedido e o título do filme que está sendo locado.
* Não é possível locar uma fita que tenha a situação de *LOCADA*.
* A primeira fita que for encontrado com situação de *DISPONIVEL* terá sua situação mudada para *LOCADA*.

## PUT - /locacao/devolver
* Para devolver um filme deve ser informado o CPF do cliente e o título do filme que está sendo devolvido.
* Quando todos filmes foram devovidos o pedido terá sua situação mudada para *ENCERRADO*

## GET - /locacao
* Retorna uma lista de locações.

## GET - /locacao/{id}
* Retorna a locação que tenha o id passado.



    