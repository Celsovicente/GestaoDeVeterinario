/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: EntradaModelo.java
Data: 12.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class EntradaModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo nome, telefone, provincia, municipio, comuna, veterinario;
    private DataModelo dataEntrada, dataRegistro;
    private boolean status;

    public EntradaModelo()
    {
        id = 0;
        nome = new StringBufferModelo("", 20);
        telefone = new StringBufferModelo("", 20);
        provincia = new StringBufferModelo("", 20);
        municipio = new StringBufferModelo("", 20);
        comuna = new StringBufferModelo("", 20);
        veterinario = new StringBufferModelo("", 20);
        dataEntrada = new DataModelo();
        dataRegistro = new DataModelo();
        status = false;
    }

    public EntradaModelo(int id, String nome, String telefone, String provincia, String municipio, String comuna,
    String veterinario, String dataEntrada, String dataRegistro, boolean status)
    {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 20);
        this.telefone = new StringBufferModelo(telefone, 20);
        this.provincia = new StringBufferModelo(provincia, 20);
        this.municipio = new StringBufferModelo(municipio, 20);
        this.comuna = new StringBufferModelo(comuna, 20);
        this.veterinario = new StringBufferModelo(veterinario, 20);
        this.dataEntrada = new DataModelo(dataEntrada);
        this.dataRegistro = new DataModelo(dataRegistro);
        this.status = status;
    }   

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome.toStringEliminatingSpaces();
    }

    public String getTelefone()
    {
        return telefone.toStringEliminatingSpaces();
    }

    public String getProvincia()
    {
        return provincia.toStringEliminatingSpaces();
    }

    public String getMunicipio()
    {
        return municipio.toStringEliminatingSpaces();
    }

    public String getComuna()
    {
        return comuna.toStringEliminatingSpaces();
    }

    public String getVeterinario()
    {
        return veterinario.toStringEliminatingSpaces();
    }

    public String getDataEntrada()
    {
        return dataEntrada.toString();
    }

    public String getDataRegistro()
    {
        return dataRegistro.toString();
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

    public void setNome(String nome)
    {
        this.nome = new StringBufferModelo(nome, 20);
    }

    public void setTelefone(String telefone)
    {
        this.telefone = new StringBufferModelo(telefone, 20);
    }

    public void setProvincia(String provincia)
    {
        this.provincia = new StringBufferModelo(provincia, 20);
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = new StringBufferModelo(municipio, 20);
    }

    public void setComuna(String comuna)
    {
        this.comuna = new StringBufferModelo(comuna, 20);
    }

    public void setVeterinario(String veterinario)
    {
        this.veterinario = new StringBufferModelo(veterinario, 20);
    }

    public void setDataEntrada(String dataEntrada)
    {
        this.dataEntrada = new DataModelo(dataEntrada);
    }

    public void setDataRegistro(String dataRegistro)
    {
        this.dataRegistro = new DataModelo(dataRegistro);
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
        dados += "Nome: " + getNome()+ "\n";
        dados += "Telefone: "+ getTelefone() + "\n";
        dados += "Provincia: " + getProvincia() + "\n";
        dados += "Municipio: " + getMunicipio() + "\n";
        dados += "Comuna: " + getComuna() +  "\n";
        dados += "Veterinario: " + getVeterinario() + "\n";
        dados += "Data de Entrada: "  + getDataEntrada() + "\n";
        dados += "Data de Registro: " + getDataRegistro() + "\n";
        dados += "Estado: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 120 * 2 + 4 + 12 * 2 + 1;
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
            nome.write(stream);
            telefone.write(stream);
            provincia.write(stream);
            municipio.write(stream);
            comuna.write(stream);
            veterinario.write(stream);
            dataEntrada.write(stream);
            dataRegistro.write(stream);
            stream.writeBoolean(status);
        }
        catch(IOException ex)
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
            nome.read(stream);
            telefone.read(stream);
            provincia.read(stream);
            municipio.read(stream);
            comuna.read(stream);
            veterinario.read(stream);
            dataEntrada.read(stream);
            dataRegistro.read(stream);
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        EntradaFile file = new EntradaFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        EntradaFile file = new EntradaFile();
        file.alterarDados(this);
    }
}