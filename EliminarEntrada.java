/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: EliminarEntrada.java
Data: 12.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarEntrada extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarEntrada()
    {
        super("Pesquisas da Entrada para Eliminar");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  dataJTF, nomeJTF;
        private JRadioButton pesquisarPorData, pesquisarPorNome;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorData = new JRadioButton("Pesquisa Por Data"));
            add(pesquisarPorNome = new JRadioButton("Pesquisa Por Nome"));

            grupo.add(pesquisarPorData);
            grupo.add(pesquisarPorNome);
            
            add(new JLabel("Digite a Data Procurada"));
            add(dataJTF = new JTextField());
            dataJTF.setEnabled(false);
            
            add(new JLabel("Digite o Nome Procurado"));
            add(nomeJTF = new JTextField());
            nomeJTF.setEnabled(false);
            
            pesquisarPorData.addActionListener(this);
            pesquisarPorNome.addActionListener(this);
        }

        public String getDataProcurada() 
        {
            return dataJTF.getText().trim();
        }

        public String getNomeProcurado()
        {
            return nomeJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorData.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorData)
            {
                dataJTF.setEnabled(true);
                nomeJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorNome)
            {
                dataJTF.setEnabled(false);
                nomeJTF.setEnabled(true);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton pesquisarJB, cancelarJB;

        public PainelSul()
        {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.PNG")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.PNG")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarJB)
            {    
                EntradaModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                   modelo = EntradaFile.getPesquisaPorData(centro.getDataProcurada());

                   JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new EntradaFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = EntradaFile.getPesquisarPorNome(centro.getNomeProcurado());
                    
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new EntradaFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }    
            }
            else 
                dispose();
        }
    }
}
