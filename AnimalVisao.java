/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: AnimalVisao.java
Data: 27.06.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class AnimalVisao extends JFrame 
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public AnimalVisao(boolean alterar, AnimalModelo modelo)
    {
        super("Animal Visao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {

        private JTextField idJTF, nomeJTF, dataNascimentoJTF;  
        private JComboBox cartaoVacinaJCB ,generoJCB, especieJCB, racaJCB;
        private JTextFieldData txtData;
        private JComboBoxTabela2_Tabela3 especieRaca;
        private String[] arrayGenero = {"Macho", "Femea"};
        private AnimalFile file;

        // construtor sem parametros
        public PainelCentro()
        {
            setLayout(new GridLayout(7, 2));
            especieRaca = new JComboBoxTabela2_Tabela3("Especies.tab", "Racas.tab");
            file = new AnimalFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // 3º linha            
            add(new JLabel("Especie"));
            add(especieJCB = especieRaca.getComboBoxFather());

            // 4º linha
            add(new JLabel("Raca"));
            add(racaJCB = especieRaca.getComboBoxSun());

            // 5º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(arrayGenero));

            // 6º linha
            add(new JLabel("Data de Nascimento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 7º linha
            add(new JLabel("Cartao de Vacina"));
            add(cartaoVacinaJCB = new JComboBox());
        }

        // construtor com parametros
        public PainelCentro(AnimalModelo modelo)
        {
            setLayout(new GridLayout(7, 2));
            especieRaca = new JComboBoxTabela2_Tabela3("Especies.tab", "Racas.tab");
            file = new AnimalFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());
            nomeJTF.setText(modelo.getNome());

            // 3º linha
            add(new JLabel("Especie"));
            add(especieJCB = especieRaca.getComboBoxFather());
            especieJCB.setSelectedItem(modelo.getEspecie());

            // 4º linha
            add(new JLabel("Raca"));
            add(racaJCB = especieRaca.getComboBoxSun());
            racaJCB.setSelectedItem(modelo.getRaca());

            // 5º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(arrayGenero));
            generoJCB.setSelectedItem(modelo.getGenero());

            // 6º linha
            add(new JLabel("Data de Nascimento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataNascimento());

            // 7º linha
            add(new JLabel("Cartao de Vacina"));
            add(cartaoVacinaJCB = new JComboBox());
            cartaoVacinaJCB.setSelectedItem(modelo.getCartaoVacina());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome()
        {
            return nomeJTF.getText().trim();
        }

        public String getEspecie()
        {
            return String.valueOf(especieJCB.getSelectedItem());      
        }

        public String getRaca()
        {
            return String.valueOf(racaJCB.getSelectedItem());
        }

        public String getGenero()
        {
            return String.valueOf(generoJCB.getSelectedItem());
        }

        public String getDataNascimento()
        {
            return txtData.getDTestField().getText();
        }
        
        public String getCartaoVacina()
        {
            return String.valueOf(cartaoVacinaJCB.getSelectedItem());
        }   
        
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" + id);
        }   

        public void setNome(String nome)
        {
            nomeJTF.setText(nome);
        }

        public void setEspecie(String especie)
        {
            especieJCB.setSelectedItem(especie);      
        }

        public void setRaca(String raca)
        {
            racaJCB.setSelectedItem(raca);
        }

        public void setGenero(String genero)
        {
           generoJCB.setSelectedItem(genero);
        }


        public void setDataNascimento(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setCartaoVacina(String cartaoVacina)
        {
            cartaoVacinaJCB.setSelectedItem(cartaoVacina);
        }

        // metodo salvar
        public void salvar()
        {
            AnimalModelo modelo = new AnimalModelo(
            getId(),
            getNome(),
            getEspecie(),
            getGenero(),
            getRaca(),
            getDataNascimento(),
            getCartaoVacina(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // metodo alterar
        public void alterar()
        {
            AnimalModelo modelo = new AnimalModelo(
            getId(),
            getNome(),
            getEspecie(),
            getGenero(),
            getRaca(),
            getDataNascimento(),
            getCartaoVacina(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
            dispose();
        }

    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton salvarJBT, cancelarJBT;
        
        public PainelSul()
        {
           setLayout(new FlowLayout());

            add(salvarJBT = new JButton("Salvar", new ImageIcon("image/save24.png")));
            add(cancelarJBT = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            salvarJBT.setBackground(Color.GREEN);
            cancelarJBT.setBackground(Color.RED);

            salvarJBT.setForeground(Color.WHITE);
            cancelarJBT.setForeground(Color.WHITE);
            
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == salvarJBT)
            {
                if(editar)
                    centro.alterar();
                else    
                    centro.salvar();
            }
            else
                dispose();
        }
    }

    public void definirTema() 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
                {
                    if ("Nimbus".equals(info.getName())) 
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new AnimalVisao(false, new AnimalModelo());
    }
}