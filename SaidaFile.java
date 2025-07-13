/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: SaidaFile.java
Data: 13.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class SaidaFile extends ObjectsFile
{
    public SaidaFile()
    {
        super("SaidaFile.dat", new SaidaModelo());
    }  

    public void salvarDados(SaidaModelo modelo)
    {
        try
        {
            // colocar o file pointer no final do ficheiro
            stream.seek(stream.length());

            // escrever no modelo
            modelo.write(stream);

            incrementarProximoCodigo();
            JOptionPane.showMessageDialog(null,  "Dados Salvos com Sucessso");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao Salvar os Dados");
        }
    }

    public void alterarDados(SaidaModelo modelo_novo)
	{
		SaidaModelo modelo_antigo = new SaidaModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public void eliminarDados(SaidaModelo modelo_novo)
	{
		SaidaModelo modelo_antigo = new SaidaModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write(stream);
						return;
					}							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public static void listarSaidas()
    {
        SaidaFile file = new SaidaFile();
        SaidaModelo modelo = new SaidaModelo();

        // Cabeçalhos da tabela
        String[] colunas = {"Id", "Observacao", "Data de Observacao"};

        // lista para aramazenar temporariamente os dados
        java.util.List<Object[]>linhas = new java.util.ArrayList<>();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                {
                    Object[] linha = 
                    {
                        modelo.getId(),
                        modelo.getObservacao(),
                        modelo.getDataObservacao()
                    };
                    linhas.add(linha);
                }
            }

            // Converter lista para array de objetos para JTable
            Object[][] dados = new Object[linhas.size()][colunas.length];
            
            for(int i = 0; i < linhas.size(); i++)
            {
                dados[i] = linhas.get(i);
            }

             JTable tabela = new JTable(dados, colunas);
            JScrollPane scroll = new JScrollPane(tabela);
            tabela.setFillsViewportHeight(true);

            JOptionPane.showMessageDialog(null, scroll,
                "Gestao de Clinica Veterinaria", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void pesquisarPorDataObeservacao(String dataProcurado)
    {
        SaidaFile file = new SaidaFile();
        SaidaModelo modelo = new SaidaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDataObservacao().equalsIgnoreCase(dataProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, data nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return;
    }

    public static void pesquisarPorObservacao(String observacaoProcurado)
    {
        SaidaFile file = new SaidaFile();
        SaidaModelo modelo = new SaidaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getObservacao().equalsIgnoreCase(observacaoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, observaco nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // metodo de pesquisa para edicao
    public static SaidaModelo getPesquisaPorDataObservacao(String dataProcurada)
    {
        SaidaFile file = new SaidaFile();
        SaidaModelo modelo = new SaidaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDataObservacao().equalsIgnoreCase(dataProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, data nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    // getPesquisarPorDiagnostico
    public static SaidaModelo getPesquisarPorObservaco(String observacaoProcurado)
    {
        SaidaFile file = new SaidaFile();
        SaidaModelo modelo = new SaidaModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getObservacao().equalsIgnoreCase(observacaoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, observacao nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }
}