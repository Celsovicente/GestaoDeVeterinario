/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: SaidaModelo.java
Data: 13.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class SaidaModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo observacao;
    private DataModelo dataObsevacao;
    private boolean status;

    public SaidaModelo()
    {
        id = 0;
        observacao = new StringBufferModelo("", 20);
        dataObsevacao = new DataModelo();
        status = false;
    }

    public SaidaModelo(int id, String observacao, String dataObsevacao, boolean status)
    {
        this.id = id;
        this.observacao = new StringBufferModelo(observacao, 20);
        this.dataObsevacao = new DataModelo(dataObsevacao);
        this.status = status;
    }   

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getObservacao()
    {
        return observacao.toStringEliminatingSpaces();
    }

    public String getDataObservacao()
    {
        return dataObsevacao.toString();
    }

    public boolean getStatus()
    {
        return status;
    }

    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setObsevacao(String observacao)
    {
        this.observacao = new StringBufferModelo(observacao, 20);
    }

    public void setDataObsercao(String dataObsevacao)
    {
        this.dataObsevacao = new DataModelo(dataObsevacao);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
    // metodo toString
    public String toString()
    {
        String dados = "Dados do Animal Modelo: \n\n";
        dados += "Id: " + getId() + "\n";
        dados += "Observacao: " + getObservacao() + "\n";
        dados += "Data de Observacao: " + getDataObservacao() + "\n";
        dados += "Estado: " + getStatus() + "\n";
 
        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 20 * 2 + 4 + 12 + 1;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    // metodo write
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.writeInt(id);
            observacao.write(stream);
            dataObsevacao.write(stream);
            stream.writeBoolean(status);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no Ficheiro");
        }
    }

    // metodo read
    public void read(RandomAccessFile stream)
    {
        try
        {
            id = stream.readInt();
            observacao.read(stream);
            dataObsevacao.read(stream);
            status = stream.readBoolean();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        SaidaFile file = new SaidaFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        SaidaFile file = new SaidaFile();
        file.alterarDados(this);
    }
}