import java.awt.event.*;
import java.lang.Object;
import javax.swing.*;

/**
*   La classe <code>OnClick</code> est utilis&eacute;e pour g&eacute;rer les diff&eacute;rents mouvements
*   de la souris 
*
* @version 1.0
* @author Juliette Maumen&eacute; et Arnaud Jorandon
*/
public class OnClick implements MouseMotionListener,MouseListener {

	/**
    *
    *Attribut priv&eacute; de l'abscisse de la souris
    */
	private int mouseXPos;

    /**
    *Attribut priv&eacute; de l'ordonn&eacute; de la souris
    */
	private int mouseYPos;

    /**
    *Attribut priv&eacute; qui permet de r&eacute;cup&eacute;rer les informations de la grille
    */
	private Grid gridle;

    /**
    *   Attribut priv&eacute; qui permet de r&eacute;cup&eacute;rer la fenetre de jeu
    */
    private JFrame wind;

    /**
    *   Attribut priv&eacute; qui permet de r&eacute;cup&eacute;rer la fenetre de menu
    */
    private JFrame windclose;

    /**
    *Constructeur uniquement destin&eacute; à la cr&eacute;ation des constantes publique
    *et l'initialisation de l'abscisse et l'ordonn&eacute; de la souris
    *@param Grid donne les informations de la grille qui sont r&eacute;cup&eacute;r&eacute;e par gridle.
    *@param window fenetre de jeu.
    *@param windowclose fenetre de menu.
    */
	public OnClick(Grid gd, JFrame window, JFrame windclose) {

		this.wind = window;
        this.windclose = windclose;
		this.mouseXPos = 0;
		this.mouseYPos = 0;
		this.gridle=gd;
	}


    /**
    *Lors d'un clic on suppimme les items de la grille si il y a possibilit&eacute;
    *puis on enclenche les d&eacute;calages avec les fonctions provenant de la classe Decalage
    *
    */
@Override
public void mouseClicked(MouseEvent evenement) {
	Decalage decal = new Decalage(this.gridle);
    int score;

    if((this.gridle.countSelected()>1)
     && (this.gridle.getCaseValue(this.mouseXPos,this.mouseYPos)!=3)
    && (this.gridle.getCaseValue(this.mouseXPos,this.mouseYPos)!=7)){
    	decal.deletingItem();
    	this.gridle.setScore(this.gridle.countSelected());
    }

    decal.decalageBottom();

    decal.decalageGauche();

    this.gridle.repaint();

    if(decal.gameOver()){
        score=this.gridle.getScore();
        Fenetre.end(this.wind, this.windclose, score);
    }

    this.mouseMoved(evenement);


}

/**
*On ne fait rien ici
 */
@Override
public void mouseDragged(MouseEvent evenement) {

}

/**
*On ne fait rien ici
*/
@Override
public void mouseEntered(MouseEvent evenement) {
}          

/**
*   M&eacute;thode qui enleve la s&eacute;l&eacute;ction lors d'une sortie de fenetre.
*/
@Override
public void mouseExited(MouseEvent evenement) {
    this.gridle.enleverSelection();

}

/**
*En fonction des mouvements de la souris,
*On v&eacute;rifie si il y a possibilit&eacute; de groupe d'items.
*Grace à la fonction check_Stone de la classe Grid
*on peut mettre les groupe d'items en surbrillance
*/
@Override
public void mouseMoved(MouseEvent evenement) {

	this.gridle.enleverSelection();
	this.mouseXPos = (int)(evenement.getX()/50);
	this.mouseYPos = (int)(evenement.getY()/50);
	this.gridle.check_Stone(this.mouseXPos,this.mouseYPos);
	this.gridle.repaint();

	this.gridle.countSelected();

}

/**
*On ne fait rien ici
*/
@Override
public void mousePressed(MouseEvent evenement) {
}

/**
*On ne fait rien ici
*/
@Override
public void mouseReleased(MouseEvent evenement) {
}

}        

