/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: AnimalModelo.java
Data: 12.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class AnimalModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo nome, especie, raca, genero, cartaoVacina;
    private DataModelo dataNascimento;
    private boolean status;

    public AnimalModelo()
    {
        id = 0;
        nome = new StringBufferModelo("", 20);
        especie = new StringBufferModelo("", 20);
        raca = new StringBufferModelo("", 20);
        genero = new StringBufferModelo("", 10);
        dataNascimento = new DataModelo();
        cartaoVacina = new StringBufferModelo("", 20);
        status = false;
    }

    public AnimalModelo(int id, String nome, String especie, String raca, String genero, String dataNascimento,
    String cartaoVacina, boolean status)
    {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 20);
        this.especie = new StringBufferModelo(especie, 20);
        this.raca = new StringBufferModelo(raca, 20);
        this.genero = new StringBufferModelo(genero, 10);
        this.dataNascimento = new DataModelo(dataNascimento);
        this.cartaoVacina = new StringBufferModelo(cartaoVacina, 20);
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

    public String getEspecie()
    {
        return especie.toStringEliminatingSpaces();
    }

    public String getRaca()
    {
        return raca.toStringEliminatingSpaces();
    }

    public String getGenero()
    {
        return genero.toStringEliminatingSpaces();
    }

    public String getDataNascimento()
    {
        return dataNascimento.toString();
    }

    public String getCartaoVacina()
    {
        return cartaoVacina.toStringEliminatingSpaces();
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

    public void setEspecie(String especie)
    {
        this.especie = new StringBufferModelo(especie, 20);
    }

    public void setRaca(String raca)
    {
        this.raca = new StringBufferModelo(raca, 20);
    }

    public void setGenero(String genero)
    {
        this.genero = new StringBufferModelo(genero, 10);
    }

    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = new DataModelo(dataNascimento);
    }

    public void setCartaoVacina(String cartaoVacina)
    {
        this.cartaoVacina = new StringBufferModelo(cartaoVacina, 20);
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
        dados += "Nome: " + getNome() + "\n";
        dados += "Especie: " + getEspecie() + "\n";
        dados += "Raca: " + getRaca() + "\n";
        dados += "Genero: " + getGenero() + "\n";
        dados += "Data de Nascimento: " + getDataNascimento() + "\n";
        dados += "Cartao de Vacina: " + getCartaoVacina() + "\n";
        dados += "Estado: " + getStatus() + "\n";
 
        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 90 * 2 + 4 + 12 + 1;
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
            especie.write(stream);
            raca.write(stream);   
            genero.write(stream);
            dataNascimento.write(stream);
            cartaoVacina.write(stream);
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
            nome.read(stream);
            especie.read(stream);
            raca.read(stream);        
            genero.read(stream);
            dataNascimento.read(stream);
            cartaoVacina.read(stream);
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
        AnimalFile file = new AnimalFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        AnimalFile file = new AnimalFile();
        file.alterarDados(this);
    }
}