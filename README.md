# Readme - Analisador SISU
### O que faz este programa?
Este programa analisa a lista de aprovação do SISU. Ele mostrará quantos candidatos são de estados diferentes em determinado curso e também irá encontrar a menor média por demanda no curso selecionado. Então esses dados serão exibidos em um gráfico de barra cada um.
### Sobre o autor e o programa
Este programa foi produzido por Daniel Santos de Sena como projeto final do curso de Programação Orientada a Objetos do Curso de Ciência da Computação na Universidade Federal de Sergipe.


# Como utilizar?

### 1 - Arquivo txt do SISU
É necessário ter um arquivo txt da lista dos aprovados no SISU.
##### 1.1 Baixando o PDF do SISU
- Para isso, você acessa o site da Universidade que deseja analisar os dados e então baixa o PDF contendo a lista dos aprovados no SISU de qualquer ano.
##### 1.2 Convertendo PDF para TXT
É necessário fazer a conversão do PDF para TXT, pois o programa aceita apenas em TXT.
- Você pode utilizar qualquer conversor de PDF para TXT.
- Opção de Conversor online: https://convertio.co/pt/pdf-txt/


### 2 - Abrindo o arquivo
- Com o arquivo txt em seus arquivos locais. Abra o programa.
- No menu. Clique no botão "Arquivo".
- Clique em "Abrir novo arquivo".
- Selecione o arquivo da lista do SISU em formato txt.
- Com o arquivo carregado. Será exibido todos cursos daquela lista do SISU.

### 3 - Iniciando a análise e exibindo os gráficos
Após fazer a etapa 2 contida neste readme:
- Clique no curso que deseja visualizar os dados.
- Após isso, será exibido os gráficos.

## Outros

### Botão Limpar tudo

Este botão se encontra no menu "Arquivo".
Quando clicado, irá resetar os dados. deixando o programa como se tivesse acabado de iniciar.


### Status
No canto inferior esquerdo há um texto contendo o status do programa naquele momento. Será mostrando mensagens quando:
- Um arquivo for aberto ou não; 
- Quando clicar na lista dos cursos vazia; 
- Quando o botão "limpar tudo" for utilizado.

## Fechar o programa
Para fechar o programa, clique em
- Arquivo
- Fechar programa

Após isso o programa se encerrará.

# O que significa cada gráfico?
### Gráfico 1 - Quantos Candidatos com estados Diferentes por Curso (Gráfico da esquerda)
- Este gráfico irá analisar quantos Candidatos de diferentes estados entraram naquele curso selecionado.

### Gráfico 2 - Média Minima por Demanda no curso (Gráfico da direita)
- Este gráfico irá analisar a menor média(nota de corte) de cada demanda existente na aprovação daquele curso. Ou seja, irá mostrar a nota do ultimo candidato em cada demanda.

# Linguagem e ferramentas utilizadas
Este programa foi feito utilizando a linguagem de programação Java e JavaFx atráves da IDE NetBeans juntamento com o software Scene Builder.

