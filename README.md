# Sudoku

Este projeto é uma implementação do clássico jogo **Sudoku**, desenvolvido como parte do desafio prático da formação **Java Fundamentals** na plataforma [DIO](https://dio.me).


## 🚀 Finalidade
Este repositório tem fins estritamente **educativos** e seu objetivo principal é servir como registro do meu progresso na trilha de `Java` e como entrega oficial do desafio proposto no curso
para consolidar o aprendizado dos fundamentos da linguagem.

O Sudoku foi construído para demonstrar o domínio de conceitos essenciais, como:
*   Manipulação de arrays multidimensionais (matrizes).
*   Estruturas de repetição e condicionais.
*   Lógica de validação de dados.
*   Organização de código e boas práticas.


## 📂 Estrutura do Projeto
A organização dos pacotes segue boas práticas e as convenções do Java:

```dotenv
src
└── main
    └── java
        └── br.dev.fscarmo.sudoku
            ├── controllers  # Lógica de controle dos elementos visuais
            ├── game         # Lógica/regras do jogo e entidades do Sudoku
            └── ui           # Utilitários de manipulação da interface e interação com o usuário
```


## 🖼️ Demonstração

### Início

Ao apertar `play`, o contador será iniciado e o jogador já pode começar a inserir os números, digitando o palpite de 1 a 9 e apertando a tecla `ENTER`.
###
![Play](.github/assets/Sudoku%20(1).png)

O jogador poderá pausar o jogo a qualquer momento. Nesse caso, o contador será interrompido até que o jogador apertar `play` novamente. Os campos serão desabilitados e os palpites corretos serão ocultados, para evitar trapaças com o tempo 😏.
###
![Pause](.github/assets/Sudoku%20(2).png)

Se o jogador inserir um número inválido que não esteja no range de 1 a 9, o algorítimo irá detectar e mostrar uma mensagem de erro.
###
![Número inválido](.github/assets/Sudoku%20(4).png)

Se o jogador inserir um número válido de 1 a 9, mas o palpite estiver incorreto, o algorítimo irá detectar e mostrar uma mensagem informativa.
###
![Número incorreto](.github/assets/Sudoku%20(3).png)

Após 10 erros, será considerado que o jogador falhou e o jogo será reiniciado.
###
![Número incorreto](.github/assets/Sudoku%20(3).png)
