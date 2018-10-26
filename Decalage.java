/**
* La classe <code>Decalage</code> est utilis&eacute;e pour g&eacute;rer les d&eacute;calages dans la grille.
* 
* @version 1.0
* @author Juliette Maumen&eacute; et Arnaud Jorandon
*/

public class Decalage {

	private Grid grid;
	private boolean[][] selection;
	private boolean[][] caseVide;
	private int[][] tabIm;


/**
* Constructeur destin&eacute; &agrave; contenir les informations de la grille.
*
* @param grille la grille &agrave; analyser
*/
	public Decalage(Grid grille) {		

		this.grid=grille;

		this.selection = new boolean[15][10];

		this.caseVide = new boolean[15][10];

		this.tabIm = new int[15][10];


		for(int j=0 ; j<15 ; j++) {

			for(int i=0 ; i<10 ; i++) {				

				this.selection[j][i]=this.grid.getSelected(j,i);

				this.tabIm[j][i]= this.grid.getCaseValue(j,i);

			}
		}

	}
	/**
	*
	*Fonction qui prends en compte les  informations du tableau
	*pour supprimer les pierres qui sont surlign&eacute;es.
	*/
	public void deletingItem() {

		for(int i=0 ; i<15 ; i++) {

			for(int j=0 ; j<10 ; j++) {

				if(this.selection[i][j]) {

					this.grid.setCaseValue(i,j,3);
					this.grid.setCaseCopyValue(i,j,3);
					this.tabIm[i][j]=3;
					this.caseVide[i][j]=true;
				}
			}
		}

	}


	/**
	*	Fonction qui analyse le tableau
	*	pour trouver des colonnes vides et d&eacute;caler
	*	la grille &agrave; gauche si besoin.
	*/
	public void decalageGauche() {

		int tmp;
		for(int i=0 ; i<15 ; i++) {

			if((this.tabIm[i][9] == 3) || (this.tabIm[i][9] == 7)) {

				for(int j=0 ; j<10 ; j++) {
					if((i<14) && (i>-1)) {
						if((this.tabIm[i+1][j]!=3) && (this.tabIm[i+1][j]!=7)) {
							this.tabIm[i][j] = this.tabIm[i+1][j];
							this.tabIm[i+1][j]=3;
							this.grid.setCaseValue(i,j,this.tabIm[i][j]);
							this.grid.setCaseValue(i+1,j,3);
							this.grid.setCaseCopyValue(i,j,this.tabIm[i][j]);
							this.grid.setCaseCopyValue(i+1,j,3);
							i=-1;
						}

					}

				}       
			}
		}
	}

	/**
	*
	*	Fonction qui analyse le tableau pour trouver
	*	la possibilit&eacute; de d&eacute;caler les items
	*	vers le bas.
	*/
	public void decalageBottom() {

		int tmp;

		for (int i=0 ; i<15 ; i++) {

			for (int j=0 ; j<10 ; j++) {

				if((this.tabIm[i][j] == 3) || (this.tabIm[i][j] == 7)) {


					if(j>0) {
						if((this.tabIm[i][j-1]!=3) && (this.tabIm[i][j-1]!=7)) {
							tmp = this.tabIm[i][j-1];
							this.tabIm[i][j-1]=3;
							this.tabIm[i][j]=tmp;
							this.grid.setCaseValue(i,j,tmp);
							this.grid.setCaseValue(i,j-1,3);
							this.grid.setCaseCopyValue(i,j,tmp);
							this.grid.setCaseCopyValue(i,j-1,3);
							j=-1;

						}
					}
				}

			}
		}
	}

	/**
	*Retourne true si la partie est finie, false sinon
	*@return true si la partie est finie, ou false sinon
	*/
    public boolean gameOver() {

        for(int i=0 ; i<15 ; i++) {
            for(int j=0 ; j<10 ; j++) {
                if(detectedNearColor(i, j)){
                    return false;
                }
            }
        }
        return true;

    }
    /**
	*	M&eacute;thode qui cherche une pierre de même couleur parmis les cases voisines.
	*	@param i la colonne (entre 0 et 14)
	*	@param j la ligne (entre 0 et 9)
	*/
    private boolean detectedNearColor(int i, int j){
        if(grid.getCaseValue(i ,j) != 3 && grid.getCaseValue(i ,j) != 7){
            if(i + 1 < 15 && grid.getCaseValue(i ,j) == grid.getCaseValue(i + 1, j))
                return true;
            else if(i - 1 > 0 && grid.getCaseValue(i ,j) == grid.getCaseValue(i - 1, j))
                return true;
            else if(j + 1 < 10 && grid.getCaseValue(i ,j) == grid.getCaseValue(i, j + 1))
                return true;
            else if(j - 1 > 0 && grid.getCaseValue(i ,j) == grid.getCaseValue(i, j - 1))
                return true;
        }
        return false;
    }

}
