package provafinal;

import java.util.Arrays;
import java.util.List;

/**
 * Ira contar quantos estados tem por curso
 * 
 * @author Daniel Santos de Sena (danielsan.2000 at hotmail.com)
 * @since Fev 2021
 */
public class RelacaoEstadosCurso {
    public String eixoX [];
    public Integer eixoY [];
    public int qtdEstados;
    public int qtdCandidatos;
    
    /**
     * Recebe uma lista com os estados do curso especifico e cria dois vetores, eixoX e eixoY.
     * O primeiro contendo o nome de cada estado por elemento e o segundo a quantidade daquele estado.
     * Ex.: eixoX[0]==SE; eixoY[0]==5; eixoX[1]==BA; eixoY[1]==2. O estado SE apareceu 5 vezes, já o BA apareceu 2.
     * 
     * @param dadosEstado lista contendo os Estados do curso selecionado
     */
    RelacaoEstadosCurso (List<String> dadosEstado){
        
        String rol [] = dadosEstado.toArray(new String[0]); // Transforma a Lista recebida em um vetor
        Arrays.sort(rol);
        
        int n = rol.length;
        qtdCandidatos = n;
        
        //Ira analisar quantos estados diferentes existe
        qtdEstados = 1;
        for (int i=0; i<n-1;i++){
            if (!(rol[i].equals(rol[i+1]))){
                qtdEstados += 1;
            } 
        }
        
        //Atribuicao do tamanho dos eixos para a quantidade de estados
        eixoX = new String[qtdEstados];
        eixoY = new Integer[qtdEstados];
        
        //Iniciando e zerando os valores dos eixos
        for (int i=0;i<qtdEstados; i++){
            eixoX[i] = "";
            eixoY[i] = 0;
        }
        
        //Contagem dos Estados
        int ind = 0; //Valor que sera usado para controle do indice do eixoX
        int cont; //Valor que acompanha a quantidade de estados da lista recebida
        boolean novo; //Controle para verificar se o estado ja existe no eixoX ou nao
        
        eixoX[0] = rol[0];
        for (cont=0; cont<n;cont++){
            novo = false;
            while (novo == false){
                if (ind == (eixoX.length-1)){ //Se o estado for o ultimo da lista, apenas ira adicionar +1 ate finalizar a lista
                    while (cont<=n){ //Ate chegar no ultimo elemento da lista recebida
                        eixoY[ind] += 1;
                        cont += 1;
                    }
                    novo = true; //saida do while e finalizacao da contagem
                } else {
                    if (eixoX[ind].equals(rol[cont])){ //Se o estado for igual ao proximo da lista apenas adicionar +1 a contagem do estado
                        eixoY[ind] +=1;
                        cont += 1;
                    } else {
                        eixoX[ind+1] = rol[cont]; //Caso for um novo estado na anlise, cria um elemento e inicia a contagem com 1
                        eixoY[ind+1] = 1;
                        ind += 1;
                        novo = true;
                    }
                }
            }
        }
    } 
}