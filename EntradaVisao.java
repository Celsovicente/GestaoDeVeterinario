/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: EntradaVisao.java
Data: 12.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class EntradaVisao extends JFrame 
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public EntradaVisao(boolean alterar, EntradaModelo modelo)
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
        private JTextField idJTF, nomeTutorJTF, telefoneJTF, dataEntradaJTF, dataRegistroJTF;  
        private JComboBox provinciaJCB, municipioJCB, comunaJCB, veterinarioJCB;
        private JTextFieldData txtData, txtData2;
        private JComboBoxTabela3_Tabela3 provinciaMuniComuna;
        private EntradaFile file;

        // construtor sem parametros
        public PainelCentro()
        {
            setLayout(new GridLayout(9, 2));
            provinciaMuniComuna = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new EntradaFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome do Dono"));
            add(nomeTutorJTF = new JTextField());
            
            // 3º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField()); 

            // 4º linha            
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaMuniComuna.getComboBoxFather());

            // 5º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaMuniComuna.getComboBoxSun());

            // 6º linha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaMuniComuna.getComboBoxNeto());

            // 7º linha
            add(new JLabel("Veterinario"));
            add(veterinarioJCB = UInterfaceBox.createJComboBoxsTabela2("Veterinarios.tab"));

            // 8º linha
            add(new JLabel("Data de Entrada"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 9º linha
            add(new JLabel("Data de Registro"));
            JPanel painelData2 = new JPanel( new GridLayout(1, 1) );
			txtData2 = new JTextFieldData("Data ?");
			painelData2.add( txtData2.getDTestField());
			painelData2.add( txtData2.getDButton());
			add(painelData2);
        }

        // construtor com parametros
        public PainelCentro(EntradaModelo modelo)
        {
            setLayout(new GridLayout(9, 2));
            provinciaMuniComuna = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new EntradaFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome do Dono"));
            add(nomeTutorJTF = new JTextField());
            nomeTutorJTF.setText(modelo.getNome());

            // 3º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField()); 
            telefoneJTF.setText(modelo.getTelefone());

            // 4º linha            
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaMuniComuna.getComboBoxFather());
            provinciaJCB.setSelectedItem(modelo.getProvincia());

            // 5º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaMuniComuna.getComboBoxSun());
            municipioJCB.setSelectedItem(modelo.getMunicipio());

            // 6º linha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaMuniComuna.getComboBoxNeto());
            comunaJCB.setSelectedItem(modelo.getComuna());

            // 7º linha
            add(new JLabel("Veterinario"));
            add(veterinarioJCB = UInterfaceBox.createJComboBoxsTabela2("Veterinarios.tab"));
            veterinarioJCB.setSelectedItem(modelo.getVeterinario());

            // 8º linha
            add(new JLabel("Data de Entrada"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataEntrada());

            // 9º linha
            add(new JLabel("Data de Registro"));
            JPanel painelData2 = new JPanel( new GridLayout(1, 1) );
			txtData2 = new JTextFieldData("Data ?");
			painelData2.add( txtData2.getDTestField());
			painelData2.add( txtData2.getDButton());
			add(painelData2);
            txtData2.getDTestField().setText(modelo.getDataRegistro());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome()
        {
            return nomeTutorJTF.getText().trim();
        }

        public String getTelefone()
        {
            return telefoneJTF.getText().trim();
        }

        public String getProvincia()
        {
            return String.valueOf(provinciaJCB.getSelectedItem());      
        }

        public String getMunicipio()
        {
            return String.valueOf(municipioJCB.getSelectedItem());
        }

        public String getComuna()
        {
            return String.valueOf(comunaJCB.getSelectedItem());
        }

        public String getVeterinario()
        {
            return String.valueOf(veterinarioJCB.getSelectedItem());
        }

        public String getDataEntrada()
        {
            return txtData.getDTestField().getText();
        }
        
        public String getDataRegistro()
        {
            return txtData2.getDTestField().getText();
        }   
        
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" + id);
        }   

        public void setNome(String nome)
        {
            nomeTutorJTF.setText(nome);
        }

        public void setTelefone(String telefone)
        {
            telefoneJTF.setText(telefone);
        }

        public void setProvincia(String provincia)
        {
            provinciaJCB.setSelectedItem(provincia);      
        }

        public void setMunicipio(String municipio)
        {
            municipioJCB.setSelectedItem(municipio);
        }

        public void setComuna(String comuna)
        {
           comunaJCB.setSelectedItem(comuna);
        }

        public void setVeterinario(String veterinario)
        {
            veterinarioJCB.setSelectedItem(veterinario);
        }

        public void setDataEntrada(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setDataRegistro(String data)
        {
            txtData2.getDTestField().setText(data);
        }

        // metodo salvar
        public void salvar()
        {
            EntradaModelo modelo = new EntradaModelo(
            getId(),
            getNome(),
            getTelefone(),
            getProvincia(),
            getMunicipio(),
            getComuna(),
            getVeterinario(),
            getDataEntrada(),
            getDataRegistro(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // metodo alterar
        public void alterar()
        {
            EntradaModelo modelo = new EntradaModelo(
            getId(),
            getNome(),
            getTelefone(),
            getProvincia(),
            getMunicipio(),
            getComuna(),
            getVeterinario(),
            getDataEntrada(),
            getDataRegistro(),
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
        new EntradaVisao(false, new EntradaModelo());
    }
}