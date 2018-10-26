import javax.swing.JComponent;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.io.FileReader;
import java.io.Reader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.io.BufferedReader;

/**
* La classe <code>Grid</code> est utilis&eacute;e pour g&eacute;n&eacute;rer une grille de jeu.
* 
* @version 1.0
* @author Juliette Maumen&eacute; et Arnaud Jorandon
*/
public class Grid extends JComponent {

/**
* Tableau repr&eacute;sentant la valeur des cases de la grille &agrave; l'initialisation.
*/
private int[][] grille;

/**
* Tableau repr&eacute;sentant la valeur des cases de la grille lors du jeu.
*/
private int[][] copy;

/**
* Tableau repr&eacute;sentant la couleur des cases de la grille lors de la
* lecture d'un fichier(R pour rouge, V pour vert et B pour bleu)
*/
private char[][] caractere;

/**
* Tableau symbolisant la surbrillance des pions dans la grille(true si la case est
* en surbrillance, false sinon).
*/
private boolean[][] selected;

/**
* Tableau contenant toutes les images n&eacute;cessaires au jeu.
*/
private Image[] tableau_chargementImages;

/**
* Indice du d&eacute;roulement de la partie(vaut 0 lors de l'initialisation de la partie,
* vaut 1 pendant la phase de jeu).
*/
private int inGame;

/**
* Indice repr&eacute;sentant le choix du joueur(0 pour une g&eacute;n&eacute;ration al&eacute;atoire,
* 1 pour une lecture de fichier).
*/
private int mode;

/**
* Variable contenant le score du joueur au fur et &agrave; mesure de la partie.
*/
private double score;

/**
* Constructeur destin&eacute; &agrave; la g&eacute;n&eacute;ration d'une grille
* en mode al&eacute;atoire.
* @param choix le mode de jeu, 0(al&eacute;atoire) ou 1(lecture de fichiers)
*/
public Grid(int choix) {
  super();
  this.grille = new int[15][10];
  this.selected = new boolean[15][10];
  this.copy= new int[15][10];
  this.mode = choix;
  this.inGame = 0;
  this.score=0;
  for(int j=0 ; j<15; j++){
   for(int i = 0; i < 10; i++){
    this.selected[j][i]= false;
  }
}
this.setPreferredSize(new Dimension(50,50));
this.tableau_chargementImages = new Image[7];
for (int i = 0; i < 7; i++)
  this.tableau_chargementImages[i] = Toolkit.getDefaultToolkit().getImage("utilitaires/"+ i +".png");

}

/**
* Constructeur destin&eacute; &agrave; la g&eacute;n&eacute;ration d'une grille
* en mode lecture.
* @param choix le mode de jeu, 0(al&eacute;atoire) ou 1(lecture de fichiers)
* @param file le fichier à lire
*/
public Grid(int choix, File file){
  super();
  this.grille = new int[15][10];
  this.selected = new boolean[15][10];
  this.caractere = new char[15][10];
  this.copy= new int[15][10];
  this.mode = choix;
  this.inGame = 0;
  this.score=0;
  String line;
  for(int j=0 ; j<15; j++){
   for(int i = 0; i < 10; i++){
    this.selected[j][i]= false;
  }
}
try{
  FileReader f = new FileReader(file);
  BufferedReader ligne = new BufferedReader(f);
  try{
    for( int i = 0; i < 10; i++){
      line = ligne.readLine();
      for (int j = 0; j < 15; j++) {
        this.caractere[j][i] = line.charAt(j);
      }
    }
  }catch(FileNotFoundException fileE){
    System.out.println("Il y a un problème avec la lecture.");
  }

  f.close();
}catch(IOException ioE){
  System.out.println("Il y a un problème avec le read.");
}
this.setPreferredSize(new Dimension(50,50));
this.tableau_chargementImages = new Image[7];
for (int i = 0; i < 7; i++)
  this.tableau_chargementImages[i] = Toolkit.getDefaultToolkit().getImage("utilitaires/"+ i +".png");

}

/**
* Renvoie la valeur de s&eacute;l&eacute;ction de la case.
* 
* @return la valeur de s&eacute;l&eacute;ction de la case.
* 
* @param x la colonne (entre 0 et 14)
* @param y la ligne (entre 0 et 9)
*/
public boolean getSelected(int x, int y){
  return this.selected[x][y];
}

/**
* D&eacute;finie une valeur dans le tableau selected.
* 
* @param x la colonne (entre 0 et 14)
* @param y la ligne (entre 0 et 9)
* @param bool la valeur de la case(true ou false)
*/
public void setSelected(int x, int y, boolean bool){
  this.selected[x][y]=bool;
}

/**
* Renvoie la valeur de la case.
* 
* @return la valeur de la case.
* 
* @param x la colonne (entre 0 et 14)
* @param y la ligne (entre 0 et 9)
*/
public int getCaseValue(int x, int y){
  return this.grille[x][y];
}

/**
* D&eacute;finie une nouvelle valeur pour la case.
* 
* @param x la colonne (entre 0 et 14)
* @param y la ligne (entre 0 et 9)
* @param value la valeur de la case(entre 0 et 6)
*/
public void setCaseValue(int x, int y, int value){
  this.grille[x][y]=value;
}

/**
* D&eacute;finie une nouvelle valeur pour la case dans la copie.
* 
* @param x la colonne (entre 0 et 14)
* @param y la ligne (entre 0 et 9)
* @param value la valeur de la case(entre 0 et 6)
*/
public void setCaseCopyValue(int x , int y,int value) {
  this.copy[x][y]=value;
}

/**
* Modifie le score en fonction du nombre de pierres &eacute;limin&eacute;es
* 
* @param count le nombre de pierres &eacute;limin&eacute;es
*/
public void setScore(double count){
  this.score = this.score + Math.pow((count -2),2);
}

/**
* Renvoie la valeur de la case.
* 
* @return la valeur de la case.
* 
*/
public int getScore(){
  return (int)this.score;
}
/**
* G&eacute;nère la surbrillance du groupe survol&eacute; par la souris.
*
* @param posX la colonne (entre 0 et 14)
* @param posY la ligne (entre 0 et 9)
*/
  public void check_Stone(int posX, int posY) {

  int tmp_img;
  try{
    this.setSelected(posX, posY, true);
    
    if (posX > 0) {

        if (this.grille[posX][posY] == this.grille[posX-1][posY] //vérification image de gauche
          && this.selected[posX-1][posY] == false) {

          this.check_Stone(posX-1,posY);

        }
      }
      if (posY > 0) {

        if (this.grille[posX][posY] == this.grille[posX][posY-1] //vérification image en haut
          && this.selected[posX][posY-1] == false) {

          this.check_Stone(posX,posY-1);

        }
      }

    if (posX < 14) { //vérification de non sortie

        if (this.grille[posX][posY] == this.grille[posX+1][posY] //vérification image de droite
          && this.selected[posX+1][posY] == false) {

          this.check_Stone(posX+1,posY);

        }
      }

      if (posY < 9) {

        if (this.grille[posX][posY] == this.grille[posX][posY+1] //vérification image en bas
          && this.selected[posX][posY+1] == false) {       

          this.check_Stone(posX,posY+1);

        }
      }

      tmp_img = this.grille[posX][posY] + 4;
      this.setCaseValue(posX, posY, tmp_img);
    }catch(ArrayIndexOutOfBoundsException e){

    }

  }

/**
* Renvoie le nombre de pierres s&eacute;l&eacute;ction&eacute;es.
*
* @return le nombre de pierres s&eacute;l&eacute;ction&eacute;es.
*/
public double countSelected() {
  double count=0;
  for(int j=0 ; j<15; j++){

   for(int i = 0; i < 10; i++){

    if(this.selected[j][i]) {
      count++;
    }
  }
}
return count;

}

/**
* Remet les anciennes cases s&eacute;l&eacute;ction&eacute;es &agrave;
* l'&eacute;tat de base.
*/

public void enleverSelection() {
  for(int j=0 ; j<15; j++){

   for(int i = 0; i < 10; i++){

    this.selected[j][i]= false;
    this.grille[j][i]=this.copy[j][i];
  }
}
}

  /**
  * Affiche la grille et le score dans la fenetre.
  *
  * @param pinceau l'outil de dessin
  */
  @Override
  public void paintComponent(Graphics pinceau) {

    Graphics secondPinceau = pinceau.create();
    Random composant = new Random();


    if(this.inGame == 0){
      for (int j = 0; j < 10; j++) {

        for (int k = 0; k < 15; k++) {

            if(this.mode==0){ //aléatoire

              this.grille[k][j] = composant.nextInt(3);

              if (this.grille[k][j] == 0) {

                secondPinceau.drawImage(this.tableau_chargementImages[0],k*50,j*50, this);

              }

              else if (this.grille[k][j] == 1) { 

                secondPinceau.drawImage(this.tableau_chargementImages[1],k*50,j*50, this);

              }

              else {

                secondPinceau.drawImage(this.tableau_chargementImages[2],k*50,j*50,this);

              }
              this.copy[k][j]=this.grille[k][j]; 


            }else{ //lecture

              if (this.caractere[k][j] == 'R') {

                this.grille[k][j] = 0;

                secondPinceau.drawImage(this.tableau_chargementImages[0],k*50,j*50, this);
              }
              else if (this.caractere[k][j] == 'V') {

                this.grille[k][j] = 1;

                secondPinceau.drawImage(this.tableau_chargementImages[1],k*50,j*50, this);
              }
              else if(this.caractere[k][j] == 'B') {

                this.grille[k][j] = 2;

                secondPinceau.drawImage(this.tableau_chargementImages[2],k*50,j*50,this);
              }

              this.copy[k][j]=this.grille[k][j]; 
            }
          }  
        }
      }//fin du InGame=0
      if (this.inGame == 1) {

       for (int i = 0; i < 10; i++) {
         for (int l = 0; l < 15; l ++) {


          switch(getCaseValue(l,i)) {

            case 0:

            secondPinceau.drawImage(this.tableau_chargementImages[0],l*50,i*50, this);
            break;
            case 1:

            secondPinceau.drawImage(this.tableau_chargementImages[1],l*50,i*50, this);
            break;
            case 2:

            secondPinceau.drawImage(this.tableau_chargementImages[2],l*50,i*50, this);
            break;
          case 3:
          break;
          case 4:

          secondPinceau.drawImage(this.tableau_chargementImages[4],l*50,i*50, this);
          break;
          case 5:

          secondPinceau.drawImage(this.tableau_chargementImages[5],l*50,i*50, this);
          break;
          case 6:

          secondPinceau.drawImage(this.tableau_chargementImages[6],l*50,i*50, this);
          break;

          case 7:
          break;


        }
      }
    }
  }
  this.inGame=1;
  secondPinceau.setFont(new Font("default", Font.BOLD, 18));
  secondPinceau.drawString("Score: "+(int)this.score, 350, 550);

    }//fin de paint
  }//fin de la classe