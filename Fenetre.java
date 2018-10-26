import javax.swing.*;
import java.awt.*;

/**
* La classe <code>Fenetre</code> est utilis&eacute;e pour g&eacute;rer les fenetres du jeu.
* 
* @version 1.0
* @author Juliette Maumen&eacute; et Arnaud Jorandon
*/
public class Fenetre extends JFrame {
    /**
    *   Attribut privé représentant la fenetre utilisée
    */
	private JFrame fenetre;

    /**
    *   Constructeur uniquement utilisé pour initialiser la JFrame
    */
	public Fenetre(){
		this.fenetre = new JFrame();
	}
/**
* M&eacute;thode qui cr&eacute;er un fenetre de menu
*/
	public void menu() {
     Check bout;
     Fenetre jeu = new Fenetre();
     this.fenetre.setSize(450, 550);

     this.fenetre.setLocation(100, 100);

     this.fenetre.setTitle("Menu");

     this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     GridLayout gest = new GridLayout(3, 1);
     this.fenetre.setLayout(gest);

     JLabel titre = new JLabel("SameGame");
     titre.setVerticalAlignment(JLabel.CENTER);
     titre.setHorizontalAlignment(JLabel.CENTER);
     this.fenetre.add(titre);

     JPanel panneaubot = new JPanel();
     FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
     panneaubot.setLayout(flow);


     jeu.fenetre();

     bout = new Check(jeu.fenetre, fenetre);
    //Boutons du menu

     JButton niveau = new JButton("Choisissez un niveau");

     JButton aleatoire = new JButton("Génération aléatoire");

     JButton exit = new JButton("Quitter");

     exit.setBackground(Color.RED);

    //Ajout des boutons et du contrôleur
     panneaubot.add(niveau);

     niveau.addActionListener(bout);

     panneaubot.add(aleatoire);

     aleatoire.addActionListener(bout);

     panneaubot.add(exit);

     exit.addActionListener(bout);

     this.fenetre.add(panneaubot);

    //Affichage du menu
     this.fenetre.setVisible(true);
 }

/**
* M&eacute;thode qui cr&eacute;er un fenetre de jeu
*/
 private void fenetre() {
    // on configure la fenetre

    this.fenetre.setSize(755, 600);

    this.fenetre.setLocation(100, 100);

    this.fenetre.setResizable(false);

    this.fenetre.setTitle("SameGame");

    this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Gestion de la fenetre

    JPanel emplacement = new JPanel();

    JPanel score = new JPanel();

    //Affichage de la fenetre de jeu
    this.fenetre.add(emplacement);

    this.fenetre.add(score);

    this.fenetre.add(score, BorderLayout.SOUTH);

}


/**
* M&eacute;thode qui g&egrave;re les fenetres lors de la fin de partie
*/
    public static void end(JFrame window, JFrame menu, int score){
        JButton back = new JButton("Menu");
        JButton exit = new JButton("Quitter");
        Check fin = new Check(menu, window);
        String str;
        String rajout = "";
        window.getContentPane().removeAll();
        window.getContentPane().revalidate();
        window.setSize(450, 550);
        if(score < 400){
            rajout = "Vous pouvez faire mieux!";
        }else{
            if(score > 400 && score < 1200){
                rajout = "Pas mal!";
            }else{
                if (score >1200 && score < 3000){
                    rajout = "Bon score!";
                }else{
                    if (score >3000){
                        rajout = "Wow excellent! Félicitations!";
                    }
                }
            }
        }

        str = "Votre Score est " + score + ". "+ rajout;
        

        JLabel titre = new JLabel(str);
        titre.setVerticalAlignment(JLabel.CENTER);
        titre.setHorizontalAlignment(JLabel.CENTER);
        window.add(titre);

        GridLayout gest = new GridLayout(3, 1);
        window.setLayout(gest);

        JPanel panneaubot = new JPanel();
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        panneaubot.setLayout(flow);



        exit.setBackground(Color.RED);
        panneaubot.add(back);
        panneaubot.add(exit);
        window.add(panneaubot);
        exit.addActionListener(fin);
        back.addActionListener(fin);
        window.getContentPane().repaint();
    }
}