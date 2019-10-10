# Java Básico

> Apresentação das principais estruturas e funcionalidades da linguagem Java

## Introdução
Este material tem por objetivo apresentar através de exemplos, as principais funcionalidades e estruturas da linguagem Java.
Os exercícios presumem um conhecimento prévio em alguma outra linguagem orientada a objetos. 

## IDE - Como iniciar um ambiente de desenvolvimento
As principais IDE's do mercado para programação em JAVA atualmente são _NetBeans_, _Eclipse_ e _IntelliJ IDEA_.
Como parte desse treinamento, focaremos em uma delas: _IntelliJ IDEA_.

### IntelliJ IDEA
Existem várias versões dessa IDE, variando em quantidade de recursos e preços.

A que vamos usar é a versão _Community_ que é gratuita e possui todos recursos necessários para o treinamento.

Possui versão para _Linux_, _Mac_ e _Windows_.
Link para download: https://www.jetbrains.com/idea/download/#section=windows

## Pacotes
Os _packages_, em java, são agrupamentos de códigos dentro de um determinado domínio ou organização que o desenvolvedor julgue relevante. Por convenção, um packagem sempre inicia com o domínio da aplicação e então um nome que o identifique, sempre separando contextos por ponto ("."). 

Exemplo: `br.com.cwi.crescer.java.aula01`;

Para definir o package de um classe, usa-se a seguinte sintaxe no topo do arquivo java:

Exemplo: `package br.com.cwi.crescer.java.aula01`

Logo após definir o package da classe, são listadas todos os pacotes e ou classes dos quais a classe depende. Isso é feito usando o `import`.

Exemplo: `import br.com.cwi.crescer.java.utils.*`

## Classes

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/basic)

```
package br.com.cwi.crescer.java;

@AnnotationClassLevel(param = "param")
public class MyClass extends BaseClass implements InterfaceA, InterfaceB<GenericType> {

    public static final String name = "MY_CLASS";

    private String readonlyId;
    private String value;

    public MyClass() {
        super();
    }
    
    public MyClass(String id) {
        super();
        this.readonlyId = id;
    }

    public void bar() {

    }

    public String foobar(final Integer foo) {
        final Integer variable = 1;
        return null;
    }

    public getReadonlyId() {
        return this.readonlyId;
    }

    public getValue() {
        return this.value;
    }

    public setValue(final String value) {
        this.value = value;
    }

    

}
```

## Getters e Setters
Os métodos get e set são uma convenção bem antiga da linguagem Java. Por convenção, o objetivos destes métodos é ler e escrever os atributos da classe. O padrão de nome destes métodos é seguinte: get ou set + o nome do atributo com letra maiúscula. Por exemplo getIdade e setIdade. Como existe essa convenção, qualquer IDE Java tem a funcionalidade de gerar estes métodos automáticamente.

Em muitos livros e cursos esses métodos são ligados ao tema encapsulamento. Temos que ter cuidado com isso, pois na verdade ao criar getters e setters para todos os atributos da classe, estamos deixando os mesmos expostos, como se fossem públicos.

## Modificadores de acesso

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/acessors)

Dominar os modificadores de acesso, é imprescindível para termos uma das características mais importantes de um sistema OO: encapsulamento.
Eles nos permitem controlar onde as classes, métodos, e variáveis que criamos irão ser utilizada. A linguagem Java possui quatro modificadores de acesso, são eles:

#### public
É o modificador mais simples de compreender. Elementos declarados como `public`, podem ser utilizados em qualquer parte da aplicação.

#### private
Private é o nível de acesso mais restritivo.

Só existe um caso que uma classe pode ser declarada como `private`, [Inner Classes](https://www.tutorialspoint.com/java/java_innerclasses.htm). Como isso não é muito utilizado, não vou entrar em detalhes.

Métodos e variáveis declarados como `private`, só podem ser utilizados dentro da própria classe.

Ao definir um construtor como `private`, não é mais possível instanciar a classe de fora dela. Isso é muito útil, quando temos métodos estáticos de criação de objetos daquela classe, pois impossibilitamos que alguém instâncie a classe utilizando o operador new.

#### default
O nível padrão permite acesso de classes que estão no mesmo pacote.

É importante utilizar este modificador para limitar o acesso sempre que possível.

#### protected
Este nível permite acesso de classes que estão no mesmo pacote, ou que são sub-classes.

Classes não podem ser `protected`, mas construtores sim.

## Static

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/staticc)

A palavra `static` serve para dizer que determinado atributo ou método, é da classe em si e não precisa de uma instância para ser utilizado. Por exemplo no caso de um método `static`, podemos invocá-lo sem ter criado uma instância da classe. Basta utilizar diretamente o nome da classe com o nome do método, como `Calculadora.calcular()`. Atributos `static` podem ser acessados da mesma forma (de acordo com seu modificador de acesso). Além disso o valor deste atriubuto é compartilhado entre todas as instâncias daquela classe. 

## Final

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/finall)

O modificador `final` tem por objetivo impedir mudanças. Ele pode ser utilizado em classes, métodos e atributos. Seu funcionamento muda de acordo com o contexto.
*Classe* - Se uma classe é marcada como `final` ela não pode ser estendida.
*Método* - Um método `final` não pode ser sobreescrito
*Atributo* - Um atributo `final` não pode ser reatribuído. Aqui cabe um ponto de atenção. O atributo ser `final` é diferente de ser imutável, afinal a referência ainda pode ser manipulada. Por exemplo, um atributo do tipo `List` declarado como final, ainda pode ter elementos adicionados. O que não é possível é atribuir uma nova referência de `List` para aquela variável.

## Primitivos VS Wrappers

### Primitivos

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/primitives)
 
Primitivos:

- boolean: 1 bit
- char: 16 bits
- byte: 8 bits
- short: 16 bits
- int: 32 bits
- long: 64 bits
- float: 32 bits
- double: 64 bits

### Wrappers

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/wrappers)

Wrapper é um nome adicional ao Pattern Decorator. 

Você cria uma nova classe, que "envolve" a classe original e adiciona funcionalidade. O verbo envolver em inglês é "wrap". (Basicamente, um decorator permite que você adicione funcionalidade a um objeto (ou tipo primitivo) dinamicamente. )

O java possui 8 wrappers para tipos primitivos que adicionam a funcionalidade de tratar tipos primitivos como classes:

- Boolean
- Character
- Byte
- Short
- Integer
- Long
- Float
- Double

### Autoboxing e unboxing

A partir da versão 5 do Java, a conversão de primitivo para wrapper e vice-versa é automática (autoboxing e unboxing), dessa forma é possível fazer `5 + Integer.valueOf(5)`.
 
 
## Equals e Hashcode

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/equals)

O método equals é muito utilizado na linguagem Java. Ele é um método da classe Object, pai de todas as classes, que recebe outro objeto, compara com o corrente e retorna um `boolean` indicando se eles são iguais. A implementação padrão de `equals` herdada por todas classes, retorna verdadeiro, somente se os objetos possuirem a mesma referência em memória. É muito comum que tenhamos que sobreescrever `equals` em nossas classes com regras de comparação específicas. Por exemplo, em uma classe Pessoa temos diversos atrivutos. Porém podemos sobreescrever `equals` para que ele retorne verdadeiro caso o atributo cpf seja igual, afinal sabemos que se dois objetos Pessoa possuem o mesmo cpf, eles são a mesma Pessoa.

Já o método `hashCode` é utilizado em estruturas que precisam de uma reprentação do objeto em formato de hash, como `HashMap`. Este método também possui uma implementação padrão na classe Object. 

Para simplificação ([se quiser entender melhor os motivos, aqui existe uma explicação mais detalhada](http://www.xyzws.com/javafaq/why-always-override-hashcode-if-overriding-equals/20)) sempre que implementar o método hashCode implemente o método equals também. Isso evitará problemas no uso de estruturas que fazem uso dos hashs gerados por `hashCode`.

## Herança

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/basic/inheritance)

Em Java, escrevemos `class MyClass extends BaseClass implements InterfaceA, InterfaceB`

## Interfaces

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/basic)

> A interface é um recurso muito utilizado em Java, bem como na maioria das linguagens orientadas a objeto, para “obrigar” a um determinado grupo de classes a ter métodos ou propriedades em comum para existir em um determinado contexto, contudo os métodos podem ser implementados em cada classe de uma maneira diferente. [DevMedia](https://www.devmedia.com.br/entendendo-interfaces-em-java/25502)

## Polimorfismo

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/basic/polymorphism)

## Sobreescrita

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/override)

Em Java, entendemos por sobrescrita, o recurso que uma classe filha tem de substituir os métodos originais da classe pai por seus próprios métodos, alterando assim, o comportamente esperado da classe base. 

Para indicar que um método é uma sobrescrita, usa-se a annotation `@Override`.
Se não quiser que um método seja sobrescrito, precisa utilizar o modificador `final`: `public final void foo() {}`.

## Sobrecarga

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/overload)

Chama-se sobrecarga quando um classe possui dois ou mais métodos com o mesmo nome, mas diferente assinatura. Nesse caso, quando um dos métodos for chamado, ele será identificado pelos parâmetros informados para que se saiba qual dos métodos executar.

Exemplo:
```
public void somar(int a, int b);
public void somar(int a, int b, int c);
```

Não existe sobrecarga de oepradores.

## Enum

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/enums)

> São tipos de campos que consistem em um conjunto fixo de constantes (static final), sendo como uma lista de valores pré-definidos. [DevMedia](https://www.devmedia.com.br/tipos-enum-no-java/25729)

## Datas

[EXEMPLO](src/test/java/br/com/cwi/treinamento/java/dates)

A linguagem Java tem boas bibliotecas para se trabalhar com datas. Até a versão 7 da linguagem, as classes Date e Calendar dos pacotes básicos eram as mais utilizadas. Porém existe uma biblioteca muito famosa no mundo Java chamada JodaTime, que deixava o trabalho com datas muito mais simples. Na versão 8 do Java, a biblioteca JodaTime foi praticamente importada para dentro da linguagem.

Diversas operações com datas estão disponíveis no core da linguagem. Problemas como calculos de datas em diferentes fuso horários, podem ser resolvidos com os métodos padrão.

## Collections

[EXEMPLO](src/test/java/br/com/cwi/treinamento/java/collections)

Existem diversos tipos de coleção na linguagem Java. Elas nos auxiliam em situações onde precisamos guardar diversos dados em uma estrutura organizada. 

### List
Em Java temos array como um tipo primitivo. Mas geralmente utilizamos a interface List por fornecer métodos que tornam a manipulação de listas mais fácil. Existem diversas implementações da interface List, mas no geral a mais utilizada é a classe ArrayList. Outra implementação que merece destaque é a LinkedList, que pode ser utilizada para melhor performance quando muitas manipulações são feitas na lista.
[Aqui](http://www.javacreed.com/comparing-the-performance-of-various-list-implementations/) pode ser visto alguns testes de performance com as diferentes implementações de List, para conhecimento. Mas no dia-a-dia a menos que você utilize listas muito grandes, ou tenha uma questão muito critica de performance, ArrayList irá atender tranquilamente.

### Sort
É muito comum termos a necessidade de ordenar listas de dados. Para isso podemos utilizar a classe Collections e seu método sort, que recebe uma lista e a ordena. Para que isso funcione corretamente o tipo de objeto que está na lista, deve implementar a interface Comparable, que irá fazer com que especifiquemos qual é o critério que deve ser utilizado durante a ordenação. Strings e números, já são ordenados de forma natural.

### Map
A interface Map é utilizada quando precisamos de uma estrutura de chave valor. As chaves em um map são únicas (utilizando o método equals). Caso um novo objeto seja inserido com a mesma chave, o conteúdo anterior da chave é substituído. Podemos ter tipos complexos como listas e até outros maps no campo valor. 

As três implementações da interface Map são:
Hashmap - Não garante nenhum tipo de ordenação dos elementos. Inclusive a ordem pode ser alterada conforme novos elementos são inseridos.
LinkedHashMap - Mantem a ordem em que os elementos foram inseridos
TreeMap - Ordena os elementos conforme sua ordem natural, determinada pelo método compareTo. 

## Annotations

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/annotations)

As anotações nos permitem adicionar metadados nas classes. Seu uso geralmente está associando com *Reflection* assunto que não será aprofundado neste treinamento. Com as anotações podemos adicionar comportamentos a classes, atributos e métodos dinâmicamente. Muitos frameworks da linguagem Java, utilizam anotações para simplicar a utilização de funcionalidades.

## Generics

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/generics)

Generics é uma funcionalidade introduzida na versão 5 do Java. Seu objetivo é fornecer ao desenvolvedor a capacidade de escrever código que seja reutilizável e ao mesmo tempo com a segurança da vericação de tipos em tempo de compilação. As APIs da própria linguagem utilizam largamente estas funcionalidades, como é o caso das conhecidas interfaces List e Map por exemplo.

Como podemos ver na classe List, uma classe genérica possui um comportamento que independe do tipo que ela recebe. Com o mecanismo de generics, não precisamos criar uma classe List para cada tipo em nosso sistema.

Temos que ter atenção em um ponto. Os tipos genéricos só existem em tempo de compilação. Em tempo de execução eles são removidos. Isso traz algumas implicações como no exemplo abaixo, onde não podemos ter uma sobrecarga de método onde a única diferenção é um tipo genérico nos parâmetros.

```
public void doSomething(List<String> params) {}

public void doSomething(List<Integer> params) {}
```

## Lambda

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/lambda)

A versão 8 do Java trouxe grandes mudanças para a linguagem. A maior delas foi a introdução de programação de estilo funcional utilizando lambdas. As funções lambdas acabam sendo avaliadas como uma interface funcional, e são largamente utilizadas em cima de coleções como `List`. Abaixo seguem algumas destas interfaces.

### Predicate
Esta interface recebe um valor e retorna um boolean. É utilizada principalmente para realizar filtros.

### Consumer
Está interface recebe um valor e não retorna nada. Utilizada por exemplo em loops for, onde uma ação é executada em cima de cada valor.

### Function
Esta interface recebe um valor e retorna outro. É utilizada por exemplo para montar um map de um determinado conjunto de valores.

Estas são as interfaces que vimos neste treinamento. Mas existem outras interfaces interessantes no pacote `java.util.function`. Se possível estude esta api para tirar melhor proveito destas funcionalidades.

## Lombok

[EXEMPLO](src/main/java/br/com/cwi/treinamento/java/lombok)

O projeto Lombok visa reduzir a quantidade de _boilerplate_ e códigos repetidos usando anotações para gerar códigos automaticamente.

https://projectlombok.org/setup/intellij

## Maven

O Maven é uma ferramenta de gerenciamento, construção e implantação de projetos muito interessante, que te ajuda no processo de gerenciamento de dependências e no de build, geração de relatórios e de documentação.

A unidade básica de configuração do Maven é um arquivo chamado pom.xml, que deve ficar na raiz do seu projeto. Ele é um arquivo conhecido como Project Object Model: lá você declara a estrutura, dependências e características do seu projeto.

```
<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>br.com.cwi</groupId>
  <artifactId>crescer.java</artifactId>
  <version>1.0.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.16.14</version>
    </dependency>
  </dependencies>
</project>

```

[Caelum](http://blog.caelum.com.br/processo-de-build-com-o-maven/)

## Tests



# Exercícios

## Shape Calculator

Criar um sistema que calcule a área e perímetro de diferentes formas geométricas. Para cada modelo de forma geométrica haverá um input diferente e deverá ser possível obter a área e perímetro daquele modelo com base nos valores de entrada. Todos os dados de entrada e saída devem ser Double.

- Circle: radius
- Square: size
- Rectangle: base, height
- Triangle: sideA, sideB, sideC

## Fruit Basket

- Criar um sistema para uma fruteira. Existem diversos produtos na fruteira, cada um com seu nome, data de validade e preço por quilo, ou valor unitário. Deve ser possível criar uma cesta de compras, na qual eu possa adicionar esses produtos, informando a quantidade (inteiro) ou peso (decimal) dependendo do item. Deve, ainda, ser possível obter o valor total da cesta e obter uma coleção de string simbolizando o extrato das compras:
```
[
 "ITEM : VALOR * QUANTIDADE = VALOR TOTAL",
 "Maçã : 3.99 * 0.75 = 2.99",
 "Alface : 1.00 * 2.00 = 2.00",
 "Total : 4.99"
]

Produtos:
"Maçã" | quilo | "3.99" | 5 dias
"Mamão" | quilo | "2.89" | 2 dias
"Banana" | quilo | "2.48" | 12 horas
"Alface" | unidade | "0.99"| 1 dia

```