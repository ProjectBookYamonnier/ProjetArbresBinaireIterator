package fr.istic.prg1.tree;


import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage; //prof
//import fr.istic.prg1.tree.util.AbstractImage; //nous
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2023-09-23
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2 image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		affectAux(it, iter);
		it.selfDestroy();
		iter.selfDestroy();
	}
	
	protected void affectAux(Iterator<Node> it, Iterator<Node> iter) {
		int val = it.getValue().state;
		iter.addValue(Node.valueOf(val));
		switch (val) {
			case 2:
				it.goLeft();
				iter.goLeft();
				affectAux(it, iter);
				it.goUp();
				iter.goUp();
				
				it.goRight();
				iter.goRight();
				affectAux(it, iter);
				it.goUp();
				iter.goUp();
				break;
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2 image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		rotate180Aux(it, iter);
		it.selfDestroy();
		iter.selfDestroy();
	}
	
	protected void rotate180Aux(Iterator<Node> it, Iterator<Node> iter) {
		int val = it.getValue().state;
		iter.addValue(Node.valueOf(val));
		switch (val) {
			case 2:
				it.goLeft();
				iter.goRight();
				rotate180Aux(it, iter);
				it.goUp();
				iter.goUp();
			
				it.goRight();
				iter.goLeft();
				rotate180Aux(it, iter);
				it.goUp();
				iter.goUp();
				break;
		}
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		Iterator<Node> it = this.iterator();
		videoInverseAux(it);
		it.selfDestroy();
	}

	protected void videoInverseAux(Iterator<Node> it) {
		int val = it.getValue().state;
		switch (val) {
			case 1:
				it.setValue(Node.valueOf(0));
				break;
			case 0:
				it.setValue(Node.valueOf(1));
				break;
			case 2:
				it.goLeft();
				videoInverseAux(it);
				it.goUp();
				
				it.goRight();
				videoInverseAux(it);
				it.goUp();
				break;
		}
	}
	
	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		mirrorHAux(it, iter, 0);
		it.selfDestroy();
		iter.selfDestroy();
	}

	protected void mirrorHAux(Iterator<Node> it, Iterator<Node> iter, int longueur) {
		int val = it.getValue().state;
		iter.addValue(Node.valueOf(val));
		switch (longueur % 2) {
        	case 1: 
        		switch (val) {
                	case 2:
                		it.goLeft();
                		iter.goLeft();
                		mirrorHAux(it, iter, longueur + 1);
                		it.goUp();
                		iter.goUp();

                		it.goRight();
                		iter.goRight();
                		mirrorHAux(it, iter, longueur + 1);
                		it.goUp();
                		iter.goUp();
                		break;
        		}
            break;
        	case 0: 
        		switch (val) {
                	case 2:
                		it.goLeft();
                		iter.goRight();
                		mirrorHAux(it, iter, longueur + 1);
                		it.goUp();
                		iter.goUp();

                		it.goRight();
                		iter.goLeft();
                		mirrorHAux(it, iter, longueur + 1);
                		it.goUp();
                		iter.goUp();
                		break;
        		}
            break;
		}
	}
	
	
	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		mirrorVAux(it, iter, 0);
		it.selfDestroy();
		iter.selfDestroy();
	}

	protected void mirrorVAux(Iterator<Node> it, Iterator<Node> iter, int longueur) {
		int val = it.getValue().state;
		iter.addValue(Node.valueOf(val));
		switch(longueur % 2) {
			case 0:
				switch (val) {
					case 2:
						it.goLeft();
						iter.goLeft();
						mirrorVAux(it, iter, longueur+1);
						it.goUp();
						iter.goUp();
						
						it.goRight();
						iter.goRight();
						mirrorVAux(it, iter, longueur+1);
						it.goUp();
						iter.goUp();
						break;
				}
				break;
			case 1:
				switch (val) {
					case 2:
						it.goLeft();
						iter.goRight();
						mirrorVAux(it, iter, longueur+1);
						it.goUp();
						iter.goUp();
						
						it.goRight();
						iter.goLeft();
						mirrorVAux(it, iter, longueur+1);
						it.goUp();
						iter.goUp();
						break;
				}
				break;
		}
	}
	
	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2 image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		zoomInAux(it, iter, 0);
		it.selfDestroy();
		iter.selfDestroy();
	}

	protected void zoomInAux(Iterator<Node> it, Iterator<Node> iter, int longueur) {
		int val = it.getValue().state;
		if(val == 2 && longueur < 2	) {
			it.goLeft();
			zoomInAux(it, iter, longueur+1);
		}else if (val == 2){
			iter.addValue(Node.valueOf(val));
			it.goLeft();
			iter.goLeft();
			affectAux(it, iter);
			it.goUp();
			iter.goUp();
			
			it.goRight();
			iter.goRight();
			affectAux(it, iter);
			it.goUp();
			iter.goUp();
		}else {
			iter.addValue(Node.valueOf(1));
		}
	}
	
	
	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this devient
	 * éteint.
	 * 
	 * @param image2 image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> iter = this.iterator();
		iter.clear();
		zoomOutAux(it, iter, 0);
		it.selfDestroy();
		iter.selfDestroy();
	}
	
	protected void zoomOutAux(Iterator<Node> it, Iterator<Node> iter, int longueur) {
		int val = it.getValue().state;
		if(longueur < 2) {
			iter.addValue(Node.valueOf(2));
			iter.goRight();
			iter.addValue(Node.valueOf(0));
			iter.goUp();
			iter.goLeft();
			zoomOutAux(it, iter, longueur+1);
			iter.goUp();
			simplification(iter);
			
		}else if (longueur < 16){
			iter.addValue(Node.valueOf(val));
			if(val == 2) {
				it.goLeft();
				iter.goLeft();
				zoomOutAux(it, iter, longueur+1);
				it.goUp();
				iter.goUp();
				
				it.goRight();
				iter.goRight();
				zoomOutAux(it, iter, longueur+1);
				it.goUp();
				iter.goUp();
				
				simplification(iter);
			}
		}else if (longueur == 16){
			int valMax = it.getValue().state;
			if(valMax == 2) {
				int total = totale(it);
				if(total >= 0) {
					valMax = 1;
				}else {
					valMax = 0;
				}
			}
			iter.clear();
			iter.addValue(Node.valueOf(valMax));
		}
	}
	
	protected int totale(Iterator<Node> iter) {
		int val = iter.getValue().state;
		int res = 0;
		if(val == 2) {
			iter.goLeft();
			if(iter.getValue().state == 1) res +=  1 + totale(iter);
			else if(iter.getValue().state == 0) res += -1 + totale(iter);
			else totale(iter);
			iter.goUp();
			
			iter.goRight();
			if(iter.getValue().state == 1) res += 1 + totale(iter);
			else if(iter.getValue().state == 0) res += -1 + totale(iter);
			else totale(iter);
			iter.goUp();
			
			return res;
		}
		return 0;
	}
	
	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		Iterator<Node> it = image1.iterator();
		Iterator<Node> ite = image2.iterator();
		Iterator<Node> iter = this.iterator();
		intersectionAux(it, ite, iter);
		it.selfDestroy();
		ite.selfDestroy();
		iter.selfDestroy();
	}
	
	protected void intersectionAux(Iterator<Node> it,Iterator<Node> ite, Iterator<Node> iter) {
		int val1 = it.getValue().state;
		int val2 = ite.getValue().state;
		if(val1 == val2) {
			iter.addValue(Node.valueOf(val1));
			if(val1 == 2) {
				it.goLeft();
				ite.goLeft();
				iter.goLeft();
				intersectionAux(it, ite, iter);
				it.goUp();
				ite.goUp();
				iter.goUp();
				
				it.goRight();
				ite.goRight();
				iter.goRight();
				intersectionAux(it, ite, iter);
				it.goUp();
				ite.goUp();
				iter.goUp();
				
				simplification(iter);
			}
		}else if ((val1 == 2 && val2 == 1) || (val2 == 2 && val1 == 1)) {
			if(val1 == 2) {
				affectAux(it, iter);
			}else {
				affectAux(ite, iter);
			}
		}else {
			iter.addValue(Node.valueOf(0));
		}
	}	
	
	/**
	 * 
	 * @param iter l'itérateur de notre arbre qui pointe sur la racine 
	 */
	public void simplification(Iterator<Node> iter) {
		int val = iter.getValue().state;
		if(val == 2) {
			iter.goLeft();
			int valL = iter.getValue().state;
			iter.goUp();
			iter.goRight();
			int valR = iter.getValue().state;
			iter.goUp();
			if (valL == valR && valL != 2) {
				iter.clear();
				iter.addValue(Node.valueOf(valL));
			}
		}
	}
	
	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		Iterator<Node> it = image1.iterator();
		Iterator<Node> ite = image2.iterator();
		Iterator<Node> iter = this.iterator();
		unionAux(it, ite, iter);
		it.selfDestroy();
		ite.selfDestroy();
		iter.selfDestroy();
	}

	protected void unionAux(Iterator<Node> it,Iterator<Node> ite, Iterator<Node> iter) {
		int val1 = it.getValue().state;
		int val2 = ite.getValue().state;
		if(val1 == val2) {
			iter.addValue(Node.valueOf(val1));
			if(val1 == 2) {
				it.goLeft();
				ite.goLeft();
				iter.goLeft();
				unionAux(it, ite, iter);
				it.goUp();
				ite.goUp();
				iter.goUp();
				
				it.goRight();
				ite.goRight();
				iter.goRight();
				unionAux(it, ite, iter);
				it.goUp();
				ite.goUp();
				iter.goUp();
				
				simplification(iter);
			}
		}else if ((val1 == 2 && val2 == 0) || (val2 == 2 && val1 == 0)) {
			if(val1 == 2) {
				affectAux(it, iter);
			}else {
				affectAux(ite, iter);
			}
		}else if(val1 == 1 || val2 == 1){
			iter.addValue(Node.valueOf(1));
		}else {
			iter.addValue(Node.valueOf(0));
		}
	}
	
	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = this.iterator();
		for(int i = 0; i <= 255; i++) {
			it.goRoot();
			if(!testDiagonalAux(it, i, i, 127, 127, 127)) return false;
		}
		it.selfDestroy();
		return true;
	}
	
	protected boolean testDiagonalAux(Iterator<Node> it, int x, int y, int xBis, int yBis, int valNode) {
		valNode = valNode/2;
		if(it.getValue().state == 2) {
			if(y <= yBis) {
				it.goLeft();
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						return isPixelOnAux(it, x, y, xBis-valNode-1, yBis-valNode-1, valNode);
					}else {
						it.goRight();
						return isPixelOnAux(it, x, y, xBis+valNode+1, yBis+valNode+1, valNode);
					}
				}else {
					return isPixelOnAux(it, x, y, xBis, yBis, valNode);
				}
			}else {
				it.goRight();
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						return isPixelOnAux(it, x, y, xBis-valNode-1, yBis+valNode+1, valNode);
					}else {
						it.goRight();
						return isPixelOnAux(it, x, y, xBis+valNode+1, yBis+valNode+1,valNode);//V
					}
				}else {
					return isPixelOnAux(it, x, y, xBis, yBis,valNode);
				}
			}
		}else {
			if(it.getValue().state == 1) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * @param x abscisse du point
	 * @param y ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		Iterator<Node> it = this.iterator();
		boolean res = isPixelOnAux(it, x, y, 127, 127, 127);
		it.selfDestroy();
		return res;
	}
	
	protected boolean isPixelOnAux(Iterator<Node> it, int x, int y, int xBis, int yBis, int valNode) {
		valNode = valNode/2;
		if(it.getValue().state == 2) {
			if(y <= yBis) {
				it.goLeft();
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						return isPixelOnAux(it, x, y, xBis-valNode-1, yBis-valNode-1, valNode);
					}else {
						it.goRight();
						return isPixelOnAux(it, x, y, xBis+valNode+1, yBis+valNode+1, valNode);
					}
				}else {
					return isPixelOnAux(it, x, y, xBis, yBis, valNode);
				}
			}else {
				it.goRight();
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						return isPixelOnAux(it, x, y, xBis-valNode-1, yBis+valNode+1, valNode);
					}else {
						it.goRight();
						return isPixelOnAux(it, x, y, xBis+valNode+1, yBis+valNode+1,valNode);//V
					}
				}else {
					return isPixelOnAux(it, x, y, xBis, yBis,valNode);
				}
			}
		}else {
			if(it.getValue().state == 1) {
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * @param x1 abscisse du premier point
	 * @param y1 ordonnée du premier point
	 * @param x2 abscisse du deuxième point
	 * @param y2 ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
	 *         même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		if(x1 == x2 && y1 == y2) {
			return true;
		}else {
			Iterator<Node> it = this.iterator();
			String str1 = sameLeafAux(it, x1, y1, 127, 127, 127);
			it.goRoot();
			String str2 = sameLeafAux(it, x2, y2, 127, 127, 127);
			it.selfDestroy();
			return str1.equals(str2);
		}
	}
	
	protected String sameLeafAux(Iterator<Node> it, int x, int y, int xBis, int yBis, int valNode) {
		String str = "";
		valNode = valNode/2;
		if(it.getValue().state == 2) {
			if(y <= yBis) {
				it.goLeft();
				str += "L";
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						str += "L" + sameLeafAux(it, x, y, xBis-valNode-1, yBis-valNode-1, valNode);
					}else {
						it.goRight();
						str += "R" + sameLeafAux(it, x, y, xBis+valNode+1, yBis+valNode+1, valNode);
					}
				}else {
					str += sameLeafAux(it, x, y, xBis, yBis, valNode);
				}
			}else {
				it.goRight();
				str += "R";
				if(it.getValue().state == 2) {
					if(x <= xBis) {
						it.goLeft();
						str += "L" + sameLeafAux(it, x, y, xBis-valNode-1, yBis+valNode+1, valNode);
					}else {
						it.goRight();
						str += "R" + sameLeafAux(it, x, y, xBis+valNode+1, yBis+valNode+1,valNode);
					}
				}else {
					str += sameLeafAux(it, x, y, xBis, yBis,valNode);
				}
			}
		}
		return str;
	}

	/**
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	@Override
    public boolean isIncludedIn(AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        Iterator<Node> iter = image2.iterator();
        boolean res = isIncludeInAux(it, iter);
        it.selfDestroy();
        iter.selfDestroy();
        return res;
    }
    
    protected boolean isIncludeInAux(Iterator<Node> it, Iterator<Node> iter) {
        boolean res = true;
        int val = it.getValue().state;
    	int val2 = iter.getValue().state;
    	NodeType node = it.nodeType();
    	NodeType node2 = iter.nodeType();
        switch(node) {
        case LEAF :
            switch(node2) {
            case LEAF :
                if(val == 1 && val2 == 0) res = false;
                break;
            case DOUBLE :
                if(val == 1) res = false;
                break;
            default : break;}
            break;
        case DOUBLE :
            switch(node2) {
            case LEAF :
                if(val2 == 0) res = false;
                break;
            case DOUBLE :
                it.goLeft();
                iter.goLeft();
                res = res && isIncludeInAux(it, iter);
                it.goUp();
                iter.goUp();
                
                it.goRight();
                iter.goRight();
                res = res && isIncludeInAux(it, iter);
                it.goUp();
                iter.goUp();
                break;
            default : break;}
        break;
        default :break;}
        return res;
    }
}