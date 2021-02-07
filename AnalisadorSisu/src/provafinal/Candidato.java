package provafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


/**
 * Ira criar um candidato com as variaveis Curso, Estados, Demanda e Media; Tambem ira criar dicionarios com as relacoes entre essas variaveis.
 * 
 * @author Daniel Santos de Sena (danielsan.2000 at hotmail.com)
 * @since Fev 2021
 */
public class Candidato {
    public String curso;
    public String estado;
    public String demanda;
    public float media;
    
    /**
     * Construtor do Candidato - Irá analisar linha a linha do txt do SISU, pegar apenas as que possuem 2 espacos ou mais.
     * Entao ira criar variáveis contendo os valores de cada candidato necessarios para o funcionamento do programa, como Nome do Curso, Estado, Demanda, Media.
     * 
     * @param linha Linha do arquivo txt do SISU
     * @throws Exception Sera tratada nas outras classes
     */
    Candidato (String linha) throws Exception {
        Scanner varredor = new Scanner (linha).useDelimiter("\\s{2,}"); //Scanner ira pegar apenas as linhas com 2 espaços ou mais
        varredor.useLocale(new Locale ("pt","br")); //Setando localização do Scanner para brasileiro, para evitar problemas de pontuacao
        
        varredor.next(); //Numero de Inscricao
        
        varredor.next(); //Nome do Candidato que fez a prova
        
        curso = varredor.next(); //Nome do Curso
        
        varredor.next(); //Polo do Curso
        
        demanda = varredor.next(); //Demanda(ampla concorrencia e cotas em geral)
        
        media = varredor.nextFloat(); //Media Final
        
        varredor.next(); //Colocaçao
        
        estado = varredor.next(); //Estado do Participante
        
        varredor.close();
    }

    /**
     * Ira analisar o arquivo txt contendo a relacao dos aprovados no SISU e retornara
     * um ArraList de Map, cada valor do ArrayList tera uma relacao especifica.
     * 
     * @param f Arquivo em txt contendo a relacao dos aprovados no SISU
     * @return analises - ArrayList de Map
     * (Contendo: analises[0] == (Cursos,Estados); analises[1] == (Cursos,Demanda); analises[2] == (Cursos,Media).
     * 
     */
    public static ArrayList<Map> parserListaSisu (File f) {
        ArrayList<Map> analises = new ArrayList<>(); //Novo ArrayList que ira conter os Maps
        
        //Criacao dos Maps para armazenar os dados relacionados
        Map<String,List<String>> dicCursoEstado = new Hashtable(); //Curso e Estado
        Map<String,List<String>> dicCursoDemanda = new Hashtable(); //Curso e Demanda
        Map<String,List<Float>> dicCursoMedia = new Hashtable(); // Curso e Media
        
        Scanner leitor;
        try {
            leitor = new Scanner (f);
            while (leitor.hasNext()){
                String linha = leitor.nextLine(); //leitura da proxima linha do arquivo
                try {
                    Candidato c = new Candidato(linha); //Cria um novo candidato c , tendo a linha como parametro, para trata-la e selecionar apenas os registros esperados
                    
                    //Curso-Estado
                    if (dicCursoEstado.containsKey(c.curso.toUpperCase())){ //Caso o curso ja tenha no dicionario, apenas adicione o estado na lista
                        dicCursoEstado.get(c.curso.toUpperCase()).add(c.estado);
                    } else { //Caso o curso ainda nao esteja no dicionario, cria um novo elemento(curso) e adiciona os estados respectivos
                        dicCursoEstado.put(c.curso.toUpperCase(), new ArrayList<String>());
                        dicCursoEstado.get(c.curso.toUpperCase()).add(c.estado);
                    }
                    //Curso-Demanda
                    if (dicCursoDemanda.containsKey(c.curso.toUpperCase())){ //Caso o curso ja tenha no dicionario, apenas adicione a demanda na lista
                        dicCursoDemanda.get(c.curso.toUpperCase()).add(c.demanda);
                    } else { //Caso o curso ainda nao esteja no dicionario, cria um novo elemento(curso) e adiciona as demandas respectivas
                        dicCursoDemanda.put(c.curso.toUpperCase(), new ArrayList<String>());
                        dicCursoDemanda.get(c.curso.toUpperCase()).add(c.demanda);
                    }
                    //Curso-Media
                    if (dicCursoMedia.containsKey(c.curso.toUpperCase())){ //Caso o curso ja tenha no dicionario, apenas adicione a media na lista
                        dicCursoMedia.get(c.curso.toUpperCase()).add(c.media);
                    } else { //Caso o curso ainda nao esteja no dicionario, cria um novo elemento(curso) e adiciona as medias respectivas
                        dicCursoMedia.put(c.curso.toUpperCase(), new ArrayList<Float>());
                        dicCursoMedia.get(c.curso.toUpperCase()).add(c.media);
                    }
                    
                }
                catch (Exception e) { //Caso a linha nao tenha nada, sera ignorado.
                }
            }
            leitor.close();
            
        //Caso o arquivo nao seja encontrado, sera ignorado no programa
        } catch (FileNotFoundException ex) { 
            System.out.println("Arquivo nao encontrado");
        }
        
        //Adicao dos Maps com as analises ao ArrayList
        analises.add(dicCursoEstado); //analises[0] == dicCursoEstado - Keys: Cursos; Valores: Estados
        analises.add(dicCursoDemanda); //analises[1] == dicCursoDemanda - Keys: Cursos; Valores: Demanda
        analises.add(dicCursoMedia); //analises[2] == dicCursoMedia - Keys: Cursos; Valores: Medias
        return (analises);
    }
}