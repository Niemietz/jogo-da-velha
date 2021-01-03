package niemietz.jogodavelha;

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
            }            
        } while (vitorioso == -1);

        if (vitorioso > -1) {
            if (vitorioso == x) {
                System.out.println("Vitória do X!");
            } else {
                System.out.println("Vitória do O!");
            }
        }
    }

    /**
     * @param scanner O Scanner(System.in) nativo do Java
     * @param campo A matriz 3 x 3 do campo do jogo da velha
     * @param condicoesVitoria Todas os combinações de espaços para a vitória no jogo
     * @param vez Vez atual do jogador X ou O
     * @return Vez do próximo jogador
     */
    private static int jogar(Scanner scanner, int[][] campo, int vez) {
        int proximoJogador = -1;
        
        int[] INPUT_USUARIO = buscarInputJogador(scanner, vez);
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
     * @param vez Vez atual do jogador X ou O
     * @return Espaço linha x coluna que o jogador escolheu
     */
    private static int[] buscarInputJogador(Scanner scanner, int vez) {
        int[] result = new int[2];
        
        String jogador = "X";
        if (vez == o) {
            jogador = "O";
        }
        
        //exibirJogoDesenhado(); // TODO
        
        System.out.println("Jogador " + jogador + ", informe a linha que deseja? (1, 2 ou 3)");
        String input1 = scanner.nextLine();
        System.out.println("Jogador " + jogador + ", informe a coluna que deseja? (1, 2 ou 3)");
        String input2 = scanner.nextLine();
        
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
        // TODO Implementar a função que desenhar com System.out.println() o campo do jogo
    }
}