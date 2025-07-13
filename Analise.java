/*------------------------------------
Tema: Gestão de uma Clínica Veterinária
Nome: Vicência da Cunha
Número: 31179
Ficheiro: Analise.java
Data: 25/06/2025
------------------------------------*/

/*
1. Objetivo:
Desenvolver um sistema simples e funcional para gestão de uma clínica veterinária,
focado no registo de animais, controle de entradas (com dados do tutor e do veterinário)
e saídas clínicas, garantindo persistência e uso padronizado de tabelas auxiliares.

2. Visão [Interfaces Gráficas]:
- ApresentacaoVisao
- LoginVisao
- MenuPrincipalVisao
- AnimalVisao
- EntradaVisao
- SaidaVisao

3. Entidades Fortes e seus Atributos (Modelo):

- AnimalModelo
    int id
    String nome
    String especie                  
    String raca                     
    String genero                   
    String dataNascimento
    String cartaoVacina            
    boolean status

- EntradaModelo
    int id
    AnimalModelo animal  FK                 
    String nomeTutor
    String telefoneTutor
    String provincia                
    String municipio                
    String comuna                   
    String veterinario  
    String dataEntrada
    String dataRegistro
    boolean status

- SaidaModelo
    int id
    EntradaModelo entrada  FK                
    String observacoes             
    String dataSaida
    boolean status

4. Ficheiros | Persistência de Dados:
- AnimalFile.dat
- EntradaFile.dat
- SaidaFile.dat

5. Tabelas de Apoio (Entidades Fracas):
- Especies.tab
- Racas.tab 
- CartaoVacina.tab
- Veterinarios.tab
- Provincias.tab
- Municipios.tab 
- Comunas.tab 

6. Listagens e Pesquisas:
**Animais**
- Listagem geral
- Pesquisa por Id
- Pesquisa por Nome

**Entradas**
- Listagem geral
- Pesquisa por Nome do Tutor
- Pesquisa por Data de Entrada

**Saídas**
- Listagem geral
- Pesquisa por Data de Saída
- Pesquisa por Observações

7. Diversos:
7.1 - Linguagem: Java com Swing  
7.2 - IDE: Notepad++
*/
