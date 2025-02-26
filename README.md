# TesteTinnova

#### Os exercicios 1,2,3 e 4 estao dentro do projeto ExerciciosAlgoritmos basta apenas importalos e executar a funcao main() para ver o funcionamento da resolucão. O exercicio 5 contem a solução no projeto "veiculos" e o frontend do mesmo esta dentro de "frontendveiculos".

# Documentação das Classes dos Exercícios

## Exercício 1: `Eleicao`

A classe `Eleicao` calcula o percentual de votos válidos, brancos e nulos em uma eleição.

### Propósito

O objetivo desta classe é demonstrar cálculos simples de percentuais em um contexto eleitoral. Ela simula uma eleição com um número fixo de eleitores e votos, calculando os percentuais de cada tipo de voto.

### Implementação

* **Atributos:**
    * `totalEleitores`: Número total de eleitores (constante, definido como 1000).
    * `votosValidos`: Número de votos válidos (constante, definido como 800).
    * `votosBrancos`: Número de votos brancos (constante, definido como 150).
    * `votosNulos`: Número de votos nulos (constante, definido como 50).
* **Construtor:**
    * Um construtor padrão sem argumentos.
* **Métodos:**
    * `calculaPercentualVotosValidos()`: Calcula e retorna o percentual de votos válidos em relação ao total de eleitores.
    * `calculaPercentualVotosBrancos()`: Calcula e retorna o percentual de votos brancos em relação ao total de eleitores.
    * `calculaPercentualVotosNulos()`: Calcula e retorna o percentual de votos nulos em relação ao total de eleitores.
* **Método `main()`:**
    * Cria uma instância da classe `Eleicao`.
    * Imprime os resultados dos cálculos de percentuais no console.

### Por que a implementação foi feita assim?

* Os atributos são `final` porque os valores são constantes e não devem ser alterados após a inicialização.
* Os métodos de cálculo utilizam `(double)` para garantir que a divisão seja feita em ponto flutuante, resultando em um percentual preciso.
* O método `main` é usado para demonstrar a funcionalidade da classe.

---

## Exercício 2: `BubbleSort`

A classe `BubbleSort` implementa o algoritmo de ordenação Bubble Sort para ordenar um vetor de inteiros.

### Propósito

O objetivo desta classe é demonstrar a implementação do algoritmo Bubble Sort, um algoritmo de ordenação simples que compara elementos adjacentes e os troca se estiverem na ordem errada.

### Implementação

* **Método `bubbleSort(int[] vetor)`:**
    * Recebe um vetor de inteiros como entrada.
    * Utiliza dois loops aninhados para comparar e trocar elementos adjacentes.
    * O loop externo itera sobre o vetor `n - 1` vezes, onde `n` é o tamanho do vetor.
    * O loop interno compara elementos adjacentes e os troca se estiverem na ordem errada.
* **Método `main()`:**
    * Cria um vetor de inteiros não ordenado.
    * Chama o método `bubbleSort()` para ordenar o vetor.
    * Imprime o vetor ordenado no console.

### Por que a implementação foi feita assim?

* O algoritmo Bubble Sort é implementado com dois loops aninhados para comparar e trocar elementos adjacentes.
* A variável `temp` é usada para realizar a troca de elementos sem perder os valores originais.
* O método `main` é usado para demonstrar a funcionalidade da classe.

---

## Exercício 3: `Fatorial`

A classe `Fatorial` calcula o fatorial de um número inteiro não negativo usando `BigInteger` para lidar com grandes resultados.

### Propósito

O objetivo desta classe é demonstrar o cálculo do fatorial de um número, utilizando `BigInteger` para evitar estouro de inteiros para números grandes.

### Implementação

* **Método `calcularFatorial(int n)`:**
    * Recebe um número inteiro `n` como entrada.
    * Verifica se `n` é negativo e lança uma exceção `IllegalArgumentException` se for.
    * Utiliza `BigInteger.ONE` como valor inicial do fatorial.
    * Multiplica o fatorial pelos números de 2 até `n` usando `BigInteger.multiply()`.
    * Retorna o resultado do fatorial como um `BigInteger`.
* **Método `main()`:**
    * Chama o método `calcularFatorial()` com o número 500.
    * Imprime o resultado do fatorial no console.

### Por que a implementação foi feita assim?

* `BigInteger` é usado para lidar com fatoriais de números grandes, que podem exceder a capacidade de um `int` ou `long`.
* A verificação de números negativos garante que a função lide com entradas inválidas.
* O método `main` é usado para demonstrar a funcionalidade da classe.

---

## Exercício 4: `SomaDosMultiplos`

A classe `SomaDosMultiplos` calcula a soma dos múltiplos de 3 ou 5 até um número inteiro `x`.

### Propósito

O objetivo desta classe é demonstrar o cálculo da soma de números que são múltiplos de 3 ou 5 dentro de um intervalo especificado.

### Implementação

* **Método `calcularSomaMultiplos(int x)`:**
    * Recebe um número inteiro `x` como entrada.
    * Inicializa uma variável `soma` com 0.
    * Itera de 1 até `x`.
    * Verifica se cada número `i` é múltiplo de 3 ou 5.
    * Se for, adiciona `i` à variável `soma`.
    * Retorna a `soma` total.

### Por que a implementação foi feita assim?

* A iteração é feita de 1 até `x` para verificar todos os números no intervalo.
* O operador `%` é usado para verificar se um número é múltiplo de 3 ou 5.
* A variável `soma` acumula os múltiplos encontrados.

# Exercicio 5

# Documentação do Backend

## Visão Geral

Este backend foi desenvolvido para gerenciar informações de veículos dentro de um sistema completo. A implementação foi realizada utilizando a **Arquitetura Hexagonal**, também conhecida como Arquitetura de Portos e Adaptadores, tendo o Spring Boot como seu framework principal. O sistema oferece a capacidade de executar operações CRUD (Criar, Ler, Atualizar, Deletar) além de fornecer dados estatísticos sobre os veículos.

---

## Tecnologias Utilizadas

### 1. **Spring Boot**

O Spring Boot foi escolhido para simplificar tanto a configuração quanto o desenvolvimento da aplicação Java. Ele automatiza a configuração de diversos componentes, como o servidor web Tomcat, o gerenciamento de dependências e a integração com o banco de dados. Essa automatização permite um desenvolvimento mais ágil e reduz os custos operacionais.

### 2. **Spring Data JPA**

Para facilitar a interação com o banco de dados relacional, foi utilizado o Spring Data JPA. Essa tecnologia abstrai a camada de acesso aos dados, simplificando a persistência de objetos Java em tabelas do banco de dados através de repositórios.

### 3. **H2 Database**

O H2 Database foi selecionado como banco de dados em memória para as fases de desenvolvimento e teste. Essa escolha permite que a aplicação seja executada sem a necessidade de um banco de dados externo, facilitando a portabilidade e a execução em diferentes ambientes.

### 4. **JUnit e Mockito**

As bibliotecas JUnit e Mockito foram essenciais para a realização de testes unitários. O JUnit é usado para estruturar e executar os testes, enquanto o Mockito é empregado para simular dependências e comportamentos de outros componentes, como o repositório, garantindo testes isolados e rápidos.

### 5. **Arquitetura Hexagonal**

A Arquitetura Hexagonal foi adotada para aumentar a flexibilidade e testabilidade do sistema. Ela promove a separação da lógica de negócio das tecnologias externas, como bancos de dados e frameworks de interface, permitindo que a aplicação seja adaptável a novas tecnologias sem afetar seu núcleo.

---

## Arquitetura do Sistema

### Estrutura do Projeto

A estrutura do projeto foi organizada seguindo os princípios da Arquitetura Hexagonal, com as seguintes camadas principais:

1.  **Core (Domínio)**: Esta camada abriga as entidades de domínio, as regras de negócio e as interfaces (ports) que definem como o sistema interage com o mundo externo.
2.  **Aplicação**: Aqui reside a lógica de aplicação, onde os casos de uso são implementados através de serviços que orquestram as operações do domínio.
3.  **Interface (Adapters)**: Esta camada serve como ponto de contato com o exterior, englobando os controllers REST para interação com o usuário, os repositórios para acesso ao banco de dados e outros adaptadores para sistemas externos.
4.  **Infraestrutura**: Nesta camada, encontram-se as implementações técnicas, como a configuração do banco de dados e a integração com frameworks, que dão suporte ao funcionamento do sistema.

### Camadas da Arquitetura

#### 1. **Domínio (Core)**

* **Entidades de Domínio**: Representam os conceitos centrais do sistema, como a entidade `Veiculo`. Elas encapsulam os dados e as regras de negócio associadas, assegurando a integridade e a validade das informações.

#### 2. **Aplicação (Use Cases)**

* **Serviços de Aplicação**: Implementam os casos de uso do sistema, utilizando as entidades de domínio e as interfaces definidas no domínio. Esta camada contém a lógica de negócio que orquestra as operações e interações entre as entidades.

#### 3. **Interface (Adapters)**

* **Controller**: Atua como a camada de adaptação externa, expondo a API REST para os clientes. Os controllers mapeiam as requisições HTTP para os casos de uso da aplicação, traduzindo as interações do mundo externo para o formato compreendido pelo sistema.

#### 4. **Infraestrutura**

* **Repositórios**: Esta camada contém a implementação do acesso aos dados, utilizando o Spring Data JPA para simplificar a interação com o banco de dados e a persistência das informações.

---

## Padrões e Boas Práticas Adotadas

1.  **Arquitetura Hexagonal**

    A adoção da arquitetura hexagonal garante uma clara separação de responsabilidades, facilitando a testabilidade do núcleo da aplicação e permitindo a substituição de tecnologias externas sem impactar a lógica de negócio.

2.  **Injeção de Dependência**

    O Spring Boot gerencia as dependências automaticamente, promovendo a flexibilidade e a facilidade de configuração da aplicação.

3.  **Uso de DTOs (Data Transfer Objects)**

    A utilização de DTOs para transferência de dados entre as camadas permite um controle maior sobre a representação dos dados e separa a lógica de negócio da representação dos mesmos.

4.  **Exceções Customizadas**

    A criação de exceções específicas, como `VeiculoNotFoundException`, proporciona mensagens de erro claras e facilita a depuração e o tratamento de erros.

5.  **Testes Unitários**

    A prática de testes unitários assegura que os casos de uso funcionem corretamente, utilizando JUnit e Mockito para garantir a qualidade e a confiabilidade do código.

---

## API - Endpoints

1.  **Gerei um arquivo .json que esta na raiz desse diretorio contendo todas os endpoints desenvolvidos, assim podendo repassar a collection atualizada**

# Frontend:

Esta página HTML foi criada testar algumas funcionalidades especificas do codigo backend que foram desenvolvidas:

## 0. Execução:

* **Execução:**
    * Para utilizar o frontend basta estar com o backend executando e abrir o arquivo index.html com qualquer navegador.

## 1. Seção de Cadastro de Veículos:

* **Função:**
    * Permite adicionar novos veículos ao sistema através de um formulário.
    * Coleta informações como nome do veículo, marca, ano, descrição, cor e se o veículo foi vendido.
* **O que faz:**
    * Apresenta um formulário com campos para cada atributo do veículo.
    * Ao clicar em "Cadastrar", os dados são enviados para o endpoint `POST /veiculos` em formato JSON.
    * Exibe mensagens de sucesso ou erro após a tentativa de cadastro.

## 2. Seção de Listagem de Veículos:

* **Função:**
    * Exibe uma lista de veículos cadastrados no sistema.
    * Permite visualizar detalhes de um veículo específico.
* **O que faz:**
    * Ao clicar em "Listar Todos", faz uma requisição `GET /veiculos` para obter a lista de veículos.
    * Exibe os veículos em uma tabela com ID, nome e marca.
    * Um botão "Detalhar" abre um modal com informações completas do veículo.
    * O modal pode ser fechado clicando no "x" ou fora da área do modal.

## 3. Seção de Excluir Veículo:

* **Função:**
    * Permite remover um veículo do sistema usando seu ID.
* **O que faz:**
    * Solicita o ID do veículo a ser excluído.
    * Ao clicar em "Excluir", faz uma requisição `DELETE /veiculos/{id}`.
    * Exibe mensagens de sucesso ou erro após a tentativa de exclusão.

## Funcionalidades Gerais:

* **Requisições AJAX:**
    * Todas as interações com o backend são feitas usando requisições AJAX, permitindo atualizações dinâmicas da página sem recarregamento.
* **Manipulação de JSON:**
    * Os dados são enviados e recebidos no formato JSON, facilitando a comunicação com o backend.
* **Interface Simples:**
    * A interface é projetada para ser clara e fácil de usar, com botões e formulários intuitivos.
* **Modal de Detalhes:**
    * Um modal é usado para exibir detalhes completos de um veículo, melhorando a experiência do usuário.

## Observações:

* **Endpoint:**
    * O endpoint base usado é `http://localhost:8080/veiculos`.
* **Dependência:**
    * A página depende da biblioteca jQuery para manipulação de DOM e requisições AJAX.
* **Estilos:**
    * Estilos CSS básicos são usados para formatar a página e melhorar a legibilidade.
* **Checkbox Vendido:**
    * O campo "Vendido" é um checkbox que envia um valor booleano para o backend.
* **Mensagens de Status:**
    * Mensagens de status informam o usuário sobre o resultado das operações (sucesso ou erro).
* **Limpeza de Campos:**
    * Após o cadastro ou exclusão, os campos são limpos para facilitar novas operações.
