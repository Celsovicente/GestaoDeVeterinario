/*------------------------------------
Tema: Gestão de uma Clinica Veterinaria
Nome: Vicencia da Cunha
Numero: 31179
Ficheiro: MenuPrincipal.java
Data: 27.06.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class MenuPrincipal extends JFrame implements ActionListener
{
	private JMenu ficheiroMenu, operacoesMenu, listagensMenu, tabelasMenu, ajudaMenu; 
	private JMenuItem novoAnimalItem, alterarAnimalItem, eliminarAnimalItem, sairItem;
	private JMenuItem novaEntradaItem, alterarEntradaItem, eliminarEntradaItem;
	private JMenuItem novaSaidaAnimalItem, alterarSaidaItem, eliminarSaidaItem;
	private JMenuItem listarAnimaisItem, listarEntradasItem, listarSaidasItem;
	private JMenuItem pesquisarAnimalItem, pesquisarEntradaItem, pesquisarSaidaItem;
	private JMenuItem especieItem, racaItem, cartaoVacinaItem, veterinarioItem, provinciaItem,municipioItem, 
	comunaItem;
	private JMenuItem ajudaSobreAutorItem, ajudaSobreAplicacaoItem;
	private JMenuBar menuBar;
	
	public MenuPrincipal(String user)
	{
		super("Menu Principal " + user);
		
		
		menuBar = new JMenuBar();
		
		adicionarComponentes();
		
		setJMenuBar( menuBar);
		
		setSize(800, 700);
		setLocationRelativeTo(null); // alinhar ao centro
		setVisible(true);
	}
	
	public void adicionarComponentes()
	{
		menuBar.add(ficheiroMenu = new JMenu("Ficheiro"));
		ficheiroMenu.setMnemonic('F');
		menuBar.add(operacoesMenu = new JMenu("Operacoes"));
		operacoesMenu.setMnemonic('O');
		menuBar.add(listagensMenu = new JMenu("Listagens/Pesquisas"));
		listagensMenu.setMnemonic('L');
		listagensMenu.setIcon(new ImageIcon("image/search32.png"));
		menuBar.add(tabelasMenu = new JMenu("Tabelas"));
		tabelasMenu.setMnemonic('T');
		tabelasMenu.setIcon(new ImageIcon("image/tabela.png"));
		menuBar.add(ajudaMenu = new JMenu("Ajuda"));
		ajudaMenu.setMnemonic('A');
		
		ficheiroMenu.add(novoAnimalItem = new JMenuItem("Novo Animal", new ImageIcon("image/novo24.png")));
		novoAnimalItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK)); 
		ficheiroMenu.add(alterarAnimalItem = new JMenuItem("Aterar Animal",new ImageIcon("image/edit24.png")));
		ficheiroMenu.add(eliminarAnimalItem = new JMenuItem("Eliminar Animal", new ImageIcon("image/delete24.png")));
		ficheiroMenu.addSeparator();
		ficheiroMenu.add(sairItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));
		
		operacoesMenu.add(novaEntradaItem = new JMenuItem("Nova Entrada", new ImageIcon("image/novo24.png")));
		novaEntradaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		operacoesMenu.add(alterarEntradaItem = new JMenuItem("Alterar Entrada", new ImageIcon("image/edit24.png")));
		operacoesMenu.add(eliminarEntradaItem = new JMenuItem("Eliminar Entrada", new ImageIcon("image/delete24.png")));
		
		operacoesMenu.addSeparator();
		
		// adicionando os elementos da operacao
		operacoesMenu.add(novaSaidaAnimalItem  = new JMenuItem("Nova Saida", new ImageIcon("image/novo24.png")));
		novaSaidaAnimalItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		operacoesMenu.add(alterarSaidaItem  = new JMenuItem("Alterar Saida", new ImageIcon("image/edit24.png")));
		operacoesMenu.add(eliminarSaidaItem  = new JMenuItem("Eliminar Saida", new ImageIcon("image/delete24.png")));
		
		// adicionando os elementos da listagem
		listagensMenu.add(listarAnimaisItem  = new JMenuItem("Listar Animais"));
		listagensMenu.add(pesquisarAnimalItem = new JMenuItem("Pesquisar Animais"));
		listagensMenu.addSeparator();
		listagensMenu.add(listarEntradasItem  = new JMenuItem("Listar Entrada"));
		listagensMenu.add(pesquisarEntradaItem = new JMenuItem("Pesquisar Entrada"));
		listagensMenu.addSeparator();
		listagensMenu.add(listarSaidasItem  = new JMenuItem("Listar Saida"));
		listagensMenu.add(pesquisarSaidaItem = new JMenuItem("Pesquisar Saida"));
		
		// adicionando os elementos da tabela
		tabelasMenu.add(especieItem = new JMenuItem("Especie"));
		tabelasMenu.add(racaItem = new JMenuItem("Raca"));
		tabelasMenu.add(cartaoVacinaItem = new JMenuItem("Cartao de Vacina"));
		tabelasMenu.add(veterinarioItem = new JMenuItem("Veterinario"));
		tabelasMenu.add(provinciaItem = new JMenuItem("Provincia"));
		tabelasMenu.add(municipioItem = new JMenuItem("Municipio"));
		tabelasMenu.add(comunaItem = new JMenuItem("Comuna"));

		ajudaMenu.add(ajudaSobreAutorItem = new JMenuItem("Ajuda Sobre o Autor"));
		ajudaMenu.add(ajudaSobreAplicacaoItem = new JMenuItem("Ajuda Sobre a Aplicacao"));

		//adicionar evento aos elementos do menu animal
		novoAnimalItem.addActionListener(this);
		alterarAnimalItem.addActionListener(this);
		eliminarAnimalItem.addActionListener(this);
		sairItem.addActionListener(this);
		pesquisarAnimalItem.addActionListener(this);
		listarAnimaisItem.addActionListener(this);

		// adicionando evento aos elementos do menu entrada
		novaEntradaItem.addActionListener(this);
		alterarEntradaItem.addActionListener(this);
		eliminarEntradaItem.addActionListener(this);
		pesquisarEntradaItem.addActionListener(this);
		listarEntradasItem.addActionListener(this);

		// adiconando evento aos elementos do menu saida
		novaSaidaAnimalItem.addActionListener(this);
		alterarSaidaItem.addActionListener(this);
		eliminarSaidaItem.addActionListener(this);
		pesquisarSaidaItem.addActionListener(this);
		listarSaidasItem.addActionListener(this);

		//adicionando evento no elementos da tabela
		especieItem.addActionListener(this);
		racaItem.addActionListener(this);
		cartaoVacinaItem.addActionListener(this); 
		veterinarioItem.addActionListener(this); 
		provinciaItem.addActionListener(this);
		municipioItem.addActionListener(this); 
		comunaItem.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getSource ()== novoAnimalItem)
			new AnimalVisao(false, new AnimalModelo());
		else if(evt.getSource() == listarAnimaisItem)
			AnimalFile.listarAnimais();
		else if(evt.getSource() == pesquisarAnimalItem)
			new PesquisarAnimal();
		else if(evt.getSource() == alterarAnimalItem)
			new EditarAnimal();
		else if(evt.getSource() == eliminarAnimalItem)
			new EliminarAnimal();
		else if(evt.getSource() == novaEntradaItem)
			new EntradaVisao(false, new EntradaModelo());
		else if(evt.getSource() == listarEntradasItem)
			EntradaFile.listarEntradas();
		else if(evt.getSource() == alterarEntradaItem)
			new EditarEntrada();
		else if(evt.getSource() == eliminarEntradaItem)
			new EliminarEntrada();
		else if(evt.getSource() == novaSaidaAnimalItem)
			new SaidaVisao(false, new SaidaModelo());
		else if(evt.getSource() == listarSaidasItem)
			SaidaFile.listarSaidas();
		else if(evt.getSource() ==  alterarSaidaItem)
			new EditarSaida();
		else if(evt.getSource() == eliminarSaidaItem)
			new EliminarSaida();
		else if(evt.getSource() == especieItem)
			Tabela2.editarNovosItems("Especies.tab", "Nova Especie");
		else if(evt.getSource() == racaItem)
			Tabela3_2.editarNovosItems("Especies.tab", "Racas.tab ", "Especies", "Racas", "Nova Especie");
		else if(evt.getSource() == cartaoVacinaItem)
			Tabela2.editarNovosItems("CartaoVacina.tab", "Novo Cartao de Vacina");
		else if(evt.getSource() == veterinarioItem)
			Tabela2.editarNovosItems("Veterinarios.tab", "Novo Veterinario");
		else if(evt.getSource() == provinciaItem)
			Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
		else if(evt.getSource() == municipioItem)
			Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincia", "Municipio", "Novo Municipio");
		else if(evt.getSource() == comunaItem)
            Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", "Provincia", 
            "Municipio", "Comuna", "Nova Comuna");
		else if(evt.getSource() == sairItem)
			dispose();
	}
	
	public static void main(String args[])
	{
		Vector_Tabelas.inic();
		new MenuPrincipal("");	
	}
}
	