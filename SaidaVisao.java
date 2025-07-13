/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: SaidaVisao.java
Data: 13.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class SaidaVisao extends JFrame 
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public SaidaVisao(boolean alterar, SaidaModelo modelo)
    {
        super("Saida Visao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, observacaoJTF, dataSaidaJTF; 
        private JTextFieldData txtData; 
        private SaidaFile file;

        // construtor sem parametros
        public PainelCentro()
        {
            setLayout(new GridLayout(3, 2));
            file = new SaidaFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Observacao"));
            add(observacaoJTF = new JTextField());

            // 3º linha            
            add(new JLabel("Data da Observacao"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
        }

        // construtor com parametros
        public PainelCentro(SaidaModelo modelo)
        {
            setLayout(new GridLayout(3, 2));
            file = new SaidaFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(observacaoJTF = new JTextField());
            observacaoJTF.setText(modelo.getObservacao());

            // 3º linha
            add(new JLabel("Data de Observacao"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataObservacao());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getObservacao()
        {
            return observacaoJTF.getText().trim();
        }

        public String getDataObservacao()
        {
            return txtData.getDTestField().getText();
        }
        
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" + id);
        }   

        public void setObsevacao(String observacao)
        {
            observacaoJTF.setText(observacao);
        }

        public void setDataObsercao(String data)
        {
            txtData.getDTestField().setText(data);
        }

        // metodo salvar
        public void salvar()
        {
            SaidaModelo modelo = new SaidaModelo(
            getId(),
            getObservacao(),
            getDataObservacao(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // metodo alterar
        public void alterar()
        {
            SaidaModelo modelo = new SaidaModelo(
            getId(),
            getObservacao(),
            getDataObservacao(),
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
        new SaidaVisao(false, new SaidaModelo());
    }
}