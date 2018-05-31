# SML-Java
Exercicio 7.36 do livro Java: Como Programar. O exercicio consiste em criar uma simulação de um computador e criar também uma linguagem de programação para usar nesse computador.

Vamos criar um computador chamado Simpletron. Como seu nome sugere, é uma máquina simples, mas poderosa. O Simpletron executa
programas escritos na única linguagem que ele entende diretamente: Simpletron Machine Language (SML).

  O Simpletron contém um acumulador — um registrador especial em que as informações são colocadas antes de o Simpletron utilizar
essas informações em cálculos ou examiná-las de várias maneiras. Todas as informações no Simpletron são tratadas em termos de
palavras.

  Uma palavra é um número decimal, de quatro dígitos com sinal, como +3364, -1293, +0007 e -0001. O Simpletron é equipado com
  uma
memória de 100 palavras e elas são referenciadas por seus números posicionais 00, 01, …, 99.

  Antes de executar um programa de SML, devemos carregar, ou colocar, o programa na memória. A primeira instrução (ou expressão)
de cada programa de SML sempre é colocada na posição 00. O simulador começará a executar nessa posição.

  Cada instrução escrita em SML ocupa uma palavra da memória do Simpletron (motivo pelo qual as instruções são números decimais
de quatro dígitos com sinal). Suporemos que o sinal de uma instrução de SML é sempre mais, mas o sinal de uma palavra de dados
pode ser mais ou menos. Cada localização na memória de Simpletron pode conter uma instrução, um valor de dados utilizado por um
programa ou uma área de memória não utilizada (e portanto indefinida). Os primeiros dois dígitos de cada instrução do SML são os
códigos de operação que especificam a operação a ser realizada. Os códigos de operação de SML são resumidos na Figura 7.33.
  
	Os últimos dois dígitos de uma instrução de SML são os operandos — o endereço da posição da memória 
  contendo a palavra à 
  qual a operação se aplica. Vamos considerar vários programas simples de SML.

	Operações:
		
	Operações de entrada e sáida:
	
	final int READ = 10 -> Lê uma palavra do teclado para um posição específica da memória.
	final int WRITE = 11 -> Escreve na tela uma palavra de uma posição específica da memória.
  
  Operações de carregamento/armazenamento:
  
  final int LOAD = 20 -> Carrega uma palavra de uma posição específica na memória para o acumulador.
  final int STORE = 21 -> Armazena uma palavra do acumulador para uma posição específica na memória.
  
  Operações aritmeticas:
  
  final int ADD = 30 -> Adiciona uma palavra de uma posição específica na memória à palavra no acumulador
  (deixa o resultado no acumulador).
  final int SUBTRACT = 31 -> Subtrai uma palavra de uma posição específica na memória da palavra no acumulador
  (deixa o resultado no acumulador).
  final int DIVIDE = 32 -> Divide uma palavra de uma posição específica na memória da palavra no acumulador
  (deixa o resultado no acumulador).
  final int MULTIPLY = 33 -> Multiplica uma palavra de uma posição específica na memória pela palavra no
  acumulador (deixa o resultado no acumulador).
  
  Operações de transferência aritmetica:
  
  final int BRANCH = 40 -> Desvia para uma posição específica na memória.
  final int BRANCHNEG = 41 ->Desvia para uma posição específica na memória se o acumulador for negativo.
  final int BRANCHZERO = 42 -> Desvia para uma posição específica na memória se o acumulador for zero.
  final int HALT = 43 -> Pare. O programa completou sua tarefa.
