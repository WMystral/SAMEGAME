import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
* La classe <code>Check</code> est utilis&eacute;e pour g&eacute;rer les boutons du jeu.
* 
* @version 1.0
* @author Juliette Maumen&eacute; et Arnaud Jorandon
*/
public class Check implements ActionListener{
	/**
	*	Attribut stockant la fenetre &agrave; ouvrir.
	*/
	private JFrame open;

	/**
	*	Attribut stockant la fenetre &agrave; fermer.
	*/
	private JFrame close;

	/**
	*	Attribut stockant un objet Fenetre pour les regles.
	*/
	private Fenetre fenetre;

	/**
	*	Attribut stockant la grille de jeu créée.
	*/
	private Grid grid;

	/**
	*	Attribut stockant le fichier &agrave; lire.
	*/
	public File file;

	/**
	*	Attribut vérifiant si la fenetre Regle est déj&agrave; ouverte.
	*/
	private boolean verif;

	/**
	*	Constructeur destin&eacute; &agrave; l'exploitation des diff&eacute;rentes fenêtres du jeu
	* @param ouvre fenêtre qui sera ouverte lors de l'utilisation des boutons
	* @param ferme fenêtre qui sera fermer lors de l'utilisation des boutons
	*/
	public Check(JFrame ouvre, JFrame ferme){
		this.open = ouvre;
		this.close = ferme;
		this.verif = false;
	}


	/**
	*	M&eacute;thode executant une action en fonction du bouton cliqu&eacute;.
	*
	*	@param event le bouton cliqu&eacute;.
	*/
	@Override
	public void actionPerformed(ActionEvent e){
		String str = e.getActionCommand();

		if(str.equals("Choisissez un niveau")){
			try{
			JFileChooser filechose = new JFileChooser("./niveaux");
			int choix = filechose.showOpenDialog(this.close);
			if(choix == JFileChooser.APPROVE_OPTION){
				file = filechose.getSelectedFile();
			}
			grid = new Grid(1, file);
			this.open.add(grid);

			OnClick obs = new OnClick(grid, this.open, this.close);
			grid.addMouseListener(obs);
			grid.addMouseMotionListener(obs);

			this.close.dispose();
			this.open.setVisible(true);
			}catch(NullPointerException npe){

			}
		}

		if(str.equals("Génération aléatoire")){
			grid = new Grid(0);
			this.open.add(grid);
			this.close.dispose();
			this.open.setVisible(true);
			OnClick obs = new OnClick(grid, this.open, this.close);
			grid.addMouseListener(obs);
			grid.addMouseMotionListener(obs);
		}


		if(str.equals("Quitter")){
			this.close.dispose();
			try{
			this.open.dispose();
			}catch(NullPointerException nor){

			}
		}
		if(str.equals("Menu")){

			this.close.dispose();
			Fenetre menu = new Fenetre();
			menu.menu();
			}
		}

	
}
