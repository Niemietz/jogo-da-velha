// JOGO DA VELHA (by. Renan Niemietz Cardoso)
// 03/01/2021
// =============================================
//
//      POSIÇÕES NO CAMPO
//       [LINHA][COLUNA]
//
//           |        |
//    [0][0] | [0][1] | [0][2]
//           |        | 
//    -------+--------+--------
//           |        |
//    [1][0] | [1][1] | [1][2]
//           |        |
//    -------+--------+--------
//           |        |
//    [2][0] | [2][1] | [2][2]
//           |        |

int[][] campo = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};   // Cria a matriz do campo
int x = 1;  // DEFINE O VALOR BINÁRIO DO USUÁRIO X EM UMA VARIÁVEL
int o = 0;  // DEFINE O VALOR BINÁRIO DO USUÁRIO O EM UMA VARIÁVEL
int vez = x;    // DEFINE A VARIÁVEL QUE ARMAZENA O JOGADOR DA VEZ
int vitorioso = -1;   // DEFINE A VARIÁVEL QUE ARMAZENA O VENCEDOR

// DEFINE TODAS AS POSIÇÕES ONDE DEVEM HAVER 3 VALORES IGUAIS PARA SE TER UMA VITÓRIA
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

// AGUARDA O USUÁRIO "X" CLICAR EM UM ESPAÇO DO CAMPO E PEGA O ÍNDICE DELE
// EX.: [0][2] SALVO COMO int[] INPUT_USUARIO = { 0, 2}

int indice1 = INPUT_USUARIO[0]; // ARMAZENA EM UMA VARIÁVEL O 1º ÍNDICE DO ESPAÇO NO CAMPO INFORMADO PELO USUÁRIO
int indice2 = INPUT_USUARIO[1]; // ARMAZENA EM UMA VARIÁVEL O 2º ÍNDICE DO ESPAÇO NO CAMPO INFORMADO PELO USUÁRIO

// VERIFICA SE O ESPAÇO QUE O USUÁRIO ESCOLHEU FOR NULO, OU SEJA, VAZIO, PREENCHA COM O VALOR BINÁRIO DESTE USUÁRIO
if (campo[indice1][indice2] == null) {
    campo[indice1][indice2] = vez;

    // FAZ UMA VARREDURA DE CADA CONDIÇÃO DE VITÓRIA, OU SEJA, AS POSIÇÕES ONDE OS 3 ESPAÇOS DEVEM SER IGUAIS PARA SE GANHAR O JOGO
    for (int i = 0; i < condicoesVitoria.length - 1; i++) {
        int valor1 = null;
        int valor2 = null;
        int valor3 = null;
        // FAZ UMA VARREDURA DE CADA ESPAÇO PREENCHIDO PARA SE OBTER A VITÓRIA
        for (int j = 0; j < condicoesVitoria[i].length - 1; j++) {        
            indice1 = condicoesVitoria[i][j][0];    // ARMAZENA O 1º ÍNDICE DO ESPAÇO (X DE 3) DE VITÓRIA
            indice2 = condicoesVitoria[i][j][1];    // ARMAZENA O 2º ÍNDICE DO ESPAÇO (Y DE 3) DE VITÓRIA
    
            // PREENCHE O VALOR 1, 2 E 3 DE CADA ESPAÇO DE CONDIÇÃO DE VITÓRIA
            if (valor1 == null) {
                valor1 = campo[indice1][indice2];
            } else if (valor2 == null) {
                valor2 = campo[indice1][indice2];
            } else if (valor3 == null) {
                valor3 = campo[indice1][indice2];
            }
        }
        // VERIFICA SE OS 3 VALORES DOS 3 ESPAÇOS DE VITÓRIA SÃO IGUAIS, SE FOREM, DEFINA O VENCEDOR
        if (valor1 == valor2 == valor3) {
            vitorioso = valor1;
            break;
        }
    }
    
    // SE O VENCEDOR FOR DEFINIDO, FINALIZE O JOGO E INFORME O VENCEDOR
    if (vitorioso > -1) {
        if (vitorioso == x) {
            System.out.println("Vitória do X!");
        } else {
            System.out.println("Vitória do O!");
        }
    } else {    // SENÃO, MUDE A VARIÁVEL QUE DEFINE A VEZ AO PRÓXIMO JOGADOR, E CONTINUE O JOGO (VOLTE AO INÍCIO DO CÓDIGO)
        if (vez == x) {
            vez = o;
        } else {
            vez = x;
        }
    }
} else {     // SENÃO, INFORME O USUÁRIO PARA PREENCHER OUTRO CAMPO
    if (vez == x) {
        System.out.println("O já preencheu este espaço, escolha outro espaço!");
    } else {
        System.out.println("X já preencheu este espaço, escolha outro espaço!");
    }
}