/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: EditarSaida.java
Data: 13.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarSaida extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EditarSaida()
    {
        super("Pesquisas da Saida para Edicao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  dataJTF, observacaoJTF;
        private JRadioButton pesquisarPorData, pesquisarPorObservacao;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorData = new JRadioButton("Pesquisa Por Data"));
            add(pesquisarPorObservacao = new JRadioButton("Pesquisa Por Observacao"));

            grupo.add(pesquisarPorData);
            grupo.add(pesquisarPorObservacao);
            
            add(new JLabel("Digite a Data Procurada"));
            add(dataJTF = new JTextField());
            dataJTF.setEnabled(false);
            
            add(new JLabel("Digite a observacao Procurada"));
            add(observacaoJTF = new JTextField());
            observacaoJTF.setEnabled(false);
            
            pesquisarPorData.addActionListener(this);
            pesquisarPorObservacao.addActionListener(this);
        }

        public String getDataProcurada() 
        {
            return dataJTF.getText().trim();
        }

        public String getObservacaoProcurado()
        {
            return observacaoJTF.getText().trim();
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
                observacaoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorObservacao)
            {
                dataJTF.setEnabled(false);
                observacaoJTF.setEnabled(true);
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
                SaidaModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = SaidaFile.getPesquisaPorDataObservacao(centro.getDataProcurada());
                    new SaidaVisao(true, modelo);
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = SaidaFile.getPesquisarPorObservaco(centro.getObservacaoProcurado());
                    new SaidaVisao(true, modelo);
                }
                    
            }
            else 
                dispose();
        }
    }
}
