package provafinal;

import java.util.List;
import java.util.Hashtable;
import java.util.Map;

/**
 * Ira analisar a menor media de cada demanda de um curso especifico.
 * 
 * @author Daniel Santos de Sena (danielsan.2000 at hotmail.com)
 * @since Fev 2021.
 */
public class RelacaoCursoDemanda {
    
    public Map <String,Float> demandaMedia = new Hashtable<>(); //Map que ira armazenar Nome da Demanda e Menor Media respectiva
    
    /**
     * Construtor da RelacaoCursoDemanda - Ira receber duas listas e relaciona-las dentro do Map demandaMedia, contendo nome da Demanda e a menor media da demanda respectiva.
     * @param dadosDemanda Contem lista de String das demandas do curso selecionado
     * @param dadosMedia Contem lista de Float das medias do curso selecionado
     */
    RelacaoCursoDemanda(List<String> dadosDemanda, List<Float> dadosMedia) {
        
        String rolDemanda [] = dadosDemanda.toArray(new String[0]); //Transforma as listas recebidas em vetores
        Float rolMedia [] = dadosMedia.toArray(new Float[0]);
        
        /*
        Sera feito a analise da menor media de cada demanda.
        Sera adicionado ao Map demandaMedia a primeira ocorrencia caso nao exista ou sera alterado o valor caso menor.
        */
        for (int i = 0; i< rolDemanda.length; i++){
            if (demandaMedia.containsKey(rolDemanda[i])){ //Caso a demanda ja exista no Map
                if (rolMedia[i] < demandaMedia.get(rolDemanda[i])){ //Se a media for menor que a existente, o valor do map sera alterado.
                    demandaMedia.put(rolDemanda[i], rolMedia[i]);
                }
            } else { //Primeira ocorrencia da Demanda nao havera verificacao, apenas inclusao ao Map
                demandaMedia.put(rolDemanda[i], rolMedia[i]);
            }
        }
    }      
}
