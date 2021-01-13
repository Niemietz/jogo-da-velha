package com.niemietz;

// JOGO DA VELHA (by. Renan Niemietz Cardoso)
// 03/01/2021
// =============================================
//
//      POSIÇÕES NO CAMPO
//       [LINHA][COLUNA]
//
//           |        |
//    [1][1] | [1][2] | [1][3]
//           |        |
//    -------+--------+--------
//           |        |
//    [2][1] | [2][2] | [2][3]
//           |        |
//    -------+--------+--------
//           |        |
//    [3][1] | [3][2] | [3][3]
//           |        |

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Renan Niemietz Cardoso
 * @since 03/01/2021
 * @version 1.0
 * © Copyright. All Rights Reserverd.
 */
public class JogoDaVelha {

    static int x = 1;
    static int o = 0;

    /**
     * @param args Os argumentos da linha de comando
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jogadas = 0;
        int vitorioso = -1;
        int[][] campo = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        int vez = x;

        int[][][] condicoesVitoria = {
                {{0, 0}, {1, 1}, {2, 2}},
                {{0, 2}, {1, 1}, {2, 0}},
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}}
        };

        do {
            int proximoJogador = jogar(scanner, campo, vez);

            if (proximoJogador > -1) {
                vitorioso = verificarVitoria(campo, condicoesVitoria);
                vez = proximoJogador;
                jogadas++;
            }
        } while (vitorioso == -1 && jogadas < 9);

        if (vitorioso > -1) {
            exibirJogoDesenhado(campo);
            if (vitorioso == x) {
                System.out.println("Vitória do X!");
            } else {
                System.out.println("Vitória do O!");
            }
        } else if (jogadas == 9) {
            exibirJogoDesenhado(campo);
            System.out.println("Xiii, deu velha! :/");
        }
    }

    /**
     * @param scanner O Scanner(System.in) nativo do Java
     * @param campo A matriz 3 x 3 do campo do jogo da velha
     * @param vez Vez atual do jogador X ou O
     * @return Vez do próximo jogador
     */
    private static int jogar(Scanner scanner, int[][] campo, int vez) {
        int proximoJogador = -1;

        int[] INPUT_USUARIO = buscarInputJogador(scanner, campo, vez);
        int indice1 = INPUT_USUARIO[0];
        int indice2 = INPUT_USUARIO[1];
        int espaco = campo[indice1][indice2];

        if (espaco == -1) {
            campo[indice1][indice2] = vez;

            if (vez == x) {
                proximoJogador = o;
            } else {
                proximoJogador = x;
            }
        } else if (espaco == x) {
            System.out.println("X já preencheu este espaço, escolha outro espaço!");
            System.out.println("");
        } else if (espaco == o) {
            System.out.println("O já preencheu este espaço, escolha outro espaço!");
            System.out.println("");
        }

        return proximoJogador;
    }

    /**
     * @param scanner O Scanner(System.in) nativo do Java
     * @param campo A matriz 3x3 do campo do jogo da velha
     * @param vez Vez atual do jogador X ou O
     * @return Espaço linha x coluna que o jogador escolheu
     */
    private static int[] buscarInputJogador(Scanner scanner, int[][] campo, int vez) {
        int[] result = new int[2];

        String jogador = "X";
        if (vez == o) {
            jogador = "O";
        }

        exibirJogoDesenhado(campo);

        Pattern pattern = Pattern.compile("[+]?[1-3]{1}/*");
        String input1;
        do {
            System.out.println("Jogador " + jogador + ", informe a linha que deseja? (1, 2 ou 3)");
            input1 = scanner.nextLine();
        } while (!pattern.matcher(input1).matches());

        String input2;
        do {
            System.out.println("Jogador " + jogador + ", informe a coluna que deseja? (1, 2 ou 3)");
            input2 = scanner.nextLine();
        } while (!pattern.matcher(input2).matches());

        int linha = Integer.parseInt(input1) - 1;
        int coluna = Integer.parseInt(input2) - 1;

        result[0] = linha;
        result[1] = coluna;

        return result;
    }

    /**
     * @param campo A matriz 3x3 do campo do jogo da velha
     * @param condicoesVitoria Todas os combinações de espaços para a vitória no jogo
     * @return Usuário vitorioso (0 ou 1) ou -1
     */
    private static int verificarVitoria(int[][] campo, int[][][] condicoesVitoria) {
        int result = -1;

        for (int i = 0; i < condicoesVitoria.length; i++) {
            int valor1 = -2;
            int valor2 = -2;
            int valor3 = -2;

            for (int j = 0; j < condicoesVitoria[i].length; j++) {
                int indice1 = condicoesVitoria[i][j][0];
                int indice2 = condicoesVitoria[i][j][1];

                if (valor1 == -2) {
                    valor1 = campo[indice1][indice2];
                } else if (valor2 == -2) {
                    valor2 = campo[indice1][indice2];
                } else if (valor3 == -2) {
                    valor3 = campo[indice1][indice2];
                }
            }

            if (valor1 > -1 && valor1 == valor2 && valor2 == valor3) {
                result = valor1;
                break;
            }
        }

        return result;
    }

    /**
     * @param campo A matriz 3x3 do campo do jogo da velha
     */
    private static void exibirJogoDesenhado(int[][] campo) {
        int[] espacos = {
            campo[0][0], campo[0][1], campo[0][2],
            campo[1][0], campo[1][1], campo[1][2],
            campo[2][0], campo[2][1], campo[2][2]
        };

        System.out.println("           |       |          ");
        int[] espacosLinha1 = {0, 1, 2};
        desenharLinha(espacosLinha1, espacos);
        System.out.println("           |       |          ");
        System.out.println("    -------+-------+--------   ");
        System.out.println("           |       |          ");
        int[] espacosLinha2 = {3, 4, 5};
        desenharLinha(espacosLinha2, espacos);
        System.out.println("           |       |          ");
        System.out.println("    -------+-------+--------   ");
        System.out.println("           |       |          ");
        int[] espacosLinha3 = {6, 7, 8};
        desenharLinha(espacosLinha3, espacos);
        System.out.println("           |       |          ");
    }

    /**
     * @param espacosLinha Array com os valores dos espaços de uma linha do campo
     * @param espacos Array com todos os valores dos espaços do campo da esquerda p/ direita, linha por linha
     */
    private static void desenharLinha(int[] espacosLinha, int[] espacos) {
        System.out.print("    ");
        desenharEspaco(espacosLinha[0], espacos);
        System.out.print("|");
        desenharEspaco(espacosLinha[1], espacos);
        System.out.print("|");
        desenharEspaco(espacosLinha[2], espacos);
        System.out.print("    \n");
    }

    /**
     * @param espaco Índice do array com os valores dos espaços de uma linha do campo
     * @param espacos Array com todos os valores dos espaços do campo da esquerda p/ direita, linha por linha
     */
    private static void desenharEspaco(int espaco, int[] espacos) {
        if (espacos[espaco] == x) {
            System.out.print("   X   ");
        } else if (espacos[espaco] == o) {
            System.out.print("   O   ");
        } else {
            System.out.print("       ");
        }
    }
}