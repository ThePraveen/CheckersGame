package checkers;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class coin{
	int color;
	int position;
	int pox_x;
	int pos_y;
	boolean canMove;
	boolean canKill;
}
@SuppressWarnings("serial")
class CheckersPanel extends Panel {
	protected int checkerboard[][] = new int[8][8];
	Checkers myApplet;
	protected static int redType = 0;
	protected static int blackType = 1;
	protected static int redKingType = 2;
	protected static int blackKingType = 3;
	protected static int emptyType = -1;
	protected static int vacantType = -2;

	protected int turn;
	public int total_red, total_black;
	protected boolean pick;
	protected int pieceMovingFrom;
	protected int pieceMovingTo;
	protected int movingPieceColor;
	
	Image Panelscreen = null;
	Dimension Panelscreensize = null;
	Graphics Panelgraphics = null;
	
	Dimension d;
	int Width,Height;
	
	@SuppressWarnings("deprecation")
	public CheckersPanel(Checkers Applet){
		  myApplet = Applet;		
		  black_coin = myApplet.getImage(myApplet.getCodeBase(), "images/black_black.gif");
		  black_king_coin = myApplet.getImage(myApplet.getCodeBase(), "images/black_black_king.gif");
		  empty = myApplet.getImage(myApplet.getCodeBase(), "images/black_empty.gif");
		  red_coin = myApplet.getImage(myApplet.getCodeBase(), "images/black_red.gif");
		  red_king_coin = myApplet.getImage(myApplet.getCodeBase(), "images/black_red_king.gif");
		  vacant = myApplet.getImage(myApplet.getCodeBase(), "images/red_empty.gif");
		  d = size();
		  Width = d.width/8;
		  Height = d.height/8;
		  newGame();
		  printBoard(checkerboard);
		}
	
	protected void newGame() {
	  int x,y;
	  for(y = 0; y < 3; y++){
		  for(x = 0; x < 8; x++){
			  if(y%2==0){
				  if(x%2==0)
					  checkerboard[x][y] = 	redType;		  					  
				  else
					  checkerboard[x][y] = vacantType;
			  }
			  else{
				  if(x%2==0)
					  checkerboard[x][y] = 	vacantType;		  					  
				  else
					  checkerboard[x][y] = redType;				  
			  }
		  }
	  }
	  
	  for(y = 3; y < 5; y++){
		  for(x = 0; x < 8; x++){
			  if(y%2==0){
				  if(x%2==0)
					  checkerboard[x][y] = 	emptyType;		  					  
				  else
					  checkerboard[x][y] = vacantType;
			  }
			  else{
				  if(x%2==0)
					  checkerboard[x][y] = 	vacantType;		  					  
				  else
					  checkerboard[x][y] = emptyType;				  
			  }
		  }
	  }
	  
	  for(y = 5; y < 8; y++){
		  for(x = 0; x < 8; x++){
			  if(y%2==0){
				  if(x%2==0)
					  checkerboard[x][y] = 	blackType;		  					  
				  else
					  checkerboard[x][y] = vacantType;
			  }
			  else{
				  if(x%2==0)
					  checkerboard[x][y] = 	vacantType;		  					  
				  else
					  checkerboard[x][y] = blackType;				  
			  }
		  }
	  }
	  turn = 0;
	  total_red = total_black = 12;
	  pick = true;
	  repaint();	  
	}

	protected Image black_coin, black_king_coin, red_coin, red_king_coin, empty, vacant;

	public Image elementImage(int element){
		  switch(element) {
		  	case 0:  return red_coin;
		  	case 1:  return black_coin;
		  	case 2:  return red_king_coin;
		  	case 3:  return black_king_coin;
		  	case -1: return empty;
		  	case -2: return vacant;
		  	default: return empty;
		  }
	}

	@SuppressWarnings("deprecation")
	public void paint(Graphics g) {
		  int x,y;
		  d = size();
		  if ((Panelscreen == null) || (d.width != Panelscreensize.width) || (d.height != Panelscreensize.height)) {
			  Panelscreen = createImage(d.width, d.height);
			  Panelscreensize = d;
			  Panelgraphics = Panelscreen.getGraphics();
			  Panelgraphics.setFont(getFont());
		  }

		  Panelgraphics.setColor(getBackground());
		  Panelgraphics.fillRect(0, 0, d.width, d.height);
		  Height = d.height/8;
		  Width = d.width/8;
		  
		  for(y=0;y<8;y++){
			  for(x=0;x<8;x++){
				  Panelgraphics.drawImage(elementImage(checkerboard[x][y]), x*Width, y*Height, Width-5, Height-5, this);
			  }
		  }
		  g.drawImage(Panelscreen,0,0,null);
	}

	public void update(Graphics g) {
	  paint(g);
	}

	public int returnPosition(int x, int y){
		x = x/Width;
		y = y/Height;
		return x+y*8;
	}	

	public boolean mouseDown(Event evt, int x, int y) {
		Graphics g = getGraphics();
		int pos_x,pos_y;
		int position = returnPosition(x,y);
		pos_x = position%8;
		pos_y = position/8;
		
		if(pick){
			
			if(turn == plainType(checkerboard[pos_x][pos_y])){
				
				pieceMovingFrom = position;
				System.out.println("moving  Form " + pieceMovingFrom);
				movingPieceColor = checkerboard[pos_x][pos_y];
				g.drawImage(elementImage(checkerboard[pos_x][pos_y]), pos_x*Width-5, pos_y*Height-5, Width+5, Height+5, this);
				pick = false;				
			}
			else{
				if(plainType(checkerboard[pos_x][pos_y]) == redType){
					System.out.println("Not your turn, Let black coin move");				
				}
				if(plainType(checkerboard[pos_x][pos_y]) == blackType){
					System.out.println("Not your turn, Let red coin move");								
				}
				else{
					if(turn==0) System.out.println("Red coin turn, click on red coin to make a move");
					else System.out.println("Black coin turn, click on Black coin to make a move");
				}				
			}
		}
		

		else{
			if(movingPieceColor == redType)
			{
				if( pos_x == (pieceMovingFrom%8)+2 &&
					pos_y == (pieceMovingFrom/8)+2 &&
					checkerboard[pos_x][pos_y] == emptyType &&
					plainType(checkerboard[(pieceMovingFrom%8)+1][(pieceMovingFrom/8)+1])==oppositeType(movingPieceColor)
				   )
				   {
						pieceMovingTo = position;
						if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						checkerboard[pos_x-1][pos_y-1] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;								
					}
					
				else if(pos_x == (pieceMovingFrom%8)-2 &&
					   pos_y == (pieceMovingFrom/8)+2 &&
					   checkerboard[pos_x][pos_y] == emptyType &&
					   plainType(checkerboard[pos_x+1][pos_y-1]) == oppositeType(movingPieceColor)
					   )
					{
						pieceMovingTo = position;
						if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						checkerboard[pos_x+1][pos_y-1] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;													
					}
						
				else if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType))
				{
						pieceMovingTo = position;
						if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;								
				}
				else{
					if(plainType(checkerboard[pos_x][pos_y]) == plainType(movingPieceColor)){
						repaint();
						pick = true;
					}
					else{
						System.out.println("Invalid move");					
					}
				}
			}
			
			else if(movingPieceColor == blackType)
			{
				if( pos_x == (pieceMovingFrom%8)+2 &&
				    pos_y == (pieceMovingFrom/8)-2 &&
				    checkerboard[pos_x][pos_y] == emptyType &&
				    plainType(checkerboard[(pieceMovingFrom%8)+1][(pieceMovingFrom/8)-1]) == oppositeType(movingPieceColor)
				  )
					{
						pieceMovingTo = position;
						if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						checkerboard[pos_x-1][pos_y+1] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;								
					}
					
					else if(pos_x == pieceMovingFrom%8-2 &&
							pos_y == pieceMovingFrom/8-2 &&
							checkerboard[pos_x][pos_y] == emptyType &&
							plainType(checkerboard[pieceMovingFrom%8-1][pieceMovingFrom/8-1]) == oppositeType(movingPieceColor))
					{
						pieceMovingTo = position;
						if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						checkerboard[pos_x+1][pos_y+1] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;								
					}				

					else if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType)){
						pieceMovingTo = position;
						if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
						else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
						checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
						repaint();
						turn = (turn+1)%2;
						pick = true;								
					}
					else{
						if(plainType(checkerboard[pos_x][pos_y]) == plainType(movingPieceColor)){
							repaint();
							pick = true;
						}
						else{
							System.out.println("Invalid move");					
						}
					}
			}

			else if(movingPieceColor == redKingType || movingPieceColor == blackKingType)
			{
				if( pos_x == (pieceMovingFrom%8)+2 &&
					pos_y == (pieceMovingFrom/8)+2 &&
					checkerboard[pos_x][pos_y] == emptyType &&
					plainType(checkerboard[(pieceMovingFrom%8)+1][(pieceMovingFrom/8)+1])==oppositeType(movingPieceColor)
					)
					   {
							pieceMovingTo = position;
							if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							checkerboard[pos_x-1][pos_y-1] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;								
						}
						
					else if(pos_x == (pieceMovingFrom%8)-2 &&
						   pos_y == (pieceMovingFrom/8)+2 &&
						   checkerboard[pos_x][pos_y] == emptyType &&
						   plainType(checkerboard[pos_x+1][pos_y-1]) == oppositeType(movingPieceColor)
						   )
						{
							pieceMovingTo = position;
							if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							checkerboard[pos_x+1][pos_y-1] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;													
						}
							
					else if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType))
					{
							pieceMovingTo = position;
							if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;								
					}
					else if( pos_x == (pieceMovingFrom%8)+2 &&
					    pos_y == (pieceMovingFrom/8)-2 &&
					    checkerboard[pos_x][pos_y] == emptyType &&
					    plainType(checkerboard[(pieceMovingFrom%8)+1][(pieceMovingFrom/8)-1]) == oppositeType(movingPieceColor)
					  )
						{
							pieceMovingTo = position;
							if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							checkerboard[pos_x-1][pos_y+1] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;								
						}
						
						else if(pos_x == pieceMovingFrom%8-2 &&
								pos_y == pieceMovingFrom/8-2 &&
								checkerboard[pos_x][pos_y] == emptyType &&
								plainType(checkerboard[pieceMovingFrom%8-1][pieceMovingFrom/8-1]) == oppositeType(movingPieceColor))
						{
							pieceMovingTo = position;
							if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							checkerboard[pos_x+1][pos_y+1] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;								
						}				

						else if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType)){
							pieceMovingTo = position;
							if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
							else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
							checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
							repaint();
							turn = (turn+1)%2;
							pick = true;								
						}
						else{
							if(plainType(checkerboard[pos_x][pos_y]) == plainType(movingPieceColor)){
								repaint();
								pick = true;
							}
							else{
								System.out.println("Invalid move");					
							}
						}				
			}
		}
		return true;		
	}
			
			
			
			
/*			
			
			
			
			if(movingPieceColor == 0 || movingPieceColor == 2 || movingPieceColor == 3){
				System.out.println("------------------------------");
				System.out.println("Piece is moving from "+pieceMovingFrom);
				System.out.println("Piece is moving To "+position);
				System.out.println("/////////////////////////");
				System.out.println(checkerboard[pos_x][pos_y]);
				

				if( pos_x == (pieceMovingFrom%8)+2 &&
				    pos_y == (pieceMovingFrom/8)+2 &&
				    checkerboard[pos_x][pos_y] == emptyType &&
				    plainType(checkerboard[pos_x-1][pos_y-1])==oppositeType(movingPieceColor)
				   )
				{
					pieceMovingTo = position;
					if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					checkerboard[pos_x-1][pos_y-1] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;								
				}
				
				if(pos_x == (pieceMovingFrom%8)-2 &&
				   pos_y == (pieceMovingFrom/8)+2 &&
				   checkerboard[pos_x][pos_y] == emptyType &&
				   plainType(checkerboard[pos_x+1][pos_y-1]) == oppositeType(movingPieceColor))
				{
					pieceMovingTo = position;
					if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					checkerboard[pos_x+1][pos_y-1] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;													
				}
					
				if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8+1 && checkerboard[pos_x][pos_y] == emptyType)){
					pieceMovingTo = position;
					if(pieceMovingTo/8==7) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;								
				}
				
				else{
					if(plainType(checkerboard[pos_x][pos_y]) == plainType(movingPieceColor)){
						repaint();
						pick = true;
					}
					else{
						System.out.println("Invalid move");					
					}
				}
			}
			
			else if(movingPieceColor==1 || movingPieceColor == 2 || movingPieceColor == 3)
			{
				
				System.out.println("------------------------------");
				System.out.println("Piece is moving from"+pieceMovingFrom);
				System.out.println("/////////////////////////");
				System.out.println(checkerboard[pos_x][pos_y]);
				//System.out.println(checkerboard[pos_x+1][pos_y+1]); 
				
				if( pos_x == (pieceMovingFrom%8)+2 &&
				    pos_y == (pieceMovingFrom/8)-2 &&
				    checkerboard[pos_x][pos_y] == emptyType &&
				    plainType(checkerboard[pos_x-1][pos_y+1]) == oppositeType(movingPieceColor)
				  )
				{
					pieceMovingTo = position;
					if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					checkerboard[pos_x-1][pos_y+1] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;								
				}
				
				else if(pos_x == pieceMovingFrom%8-2 &&
						pos_y == pieceMovingFrom/8-2 &&
						checkerboard[pos_x][pos_y] == emptyType &&
						plainType(checkerboard[pos_x+1][pos_y+1]) == oppositeType(movingPieceColor))
				{
					pieceMovingTo = position;
					if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					checkerboard[pos_x+1][pos_y+1] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;								
				}				

				else if( (pos_x == pieceMovingFrom%8+1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType) || (pos_x == pieceMovingFrom%8-1 && pos_y == pieceMovingFrom/8-1 && checkerboard[pos_x][pos_y] == emptyType)){
					pieceMovingTo = position;
					if(pieceMovingTo/8 == 0) checkerboard[pieceMovingTo%8][pieceMovingTo/8] = kingType(checkerboard[pieceMovingFrom%8][pieceMovingFrom/8]);
					else checkerboard[pieceMovingTo%8][pieceMovingTo/8] = checkerboard[pieceMovingFrom%8][pieceMovingFrom/8];
					checkerboard[pieceMovingFrom%8][pieceMovingFrom/8] = emptyType;
					repaint();
					turn = (turn+1)%2;
					pick = true;								
				}
				
				else{
					if(plainType(checkerboard[pos_x][pos_y]) == plainType(movingPieceColor)){
						repaint();
						pick = true;
					}
					else{
						System.out.println("Invalid move");					
					}
				}
			}
		}
*/
		
	protected int oppositeType(int type) {
		switch(type){
			case 0:  return 1;
			case 1:  return 0;
			case 2:  return 1;
			case 3:  return 0;
			case -1: return -1;
			case -2: return -2;			
		}
		return -1;
	}
	  
	protected int kingType(int type) {
		switch(type){
		case 0:  return 2;
		case 1:  return 3;
		case 2:  return 2;
		case 3:  return 3;
		case -1: return -1;
		case -2: return -2;			
		}
		return -1;
	}
	
	protected int plainType(int type) {
		switch(type){
			case 0:  return 0;
			case 1:  return 1;
			case 2:  return 0;
			case 3:  return 1;
			case -1: return -1;
			case -2: return -2;			
		}
		return -1;
	}
	
	public void printBoard(int[][] checkerboard){
		int x,y;
		for(y = 0; y < 8; y++){
			System.out.print("|");
			for(x = 0; x<8; x++){
				switch(checkerboard[x][y])
				{
					case 0:
						System.out.print(" r |");
						break;
				  	case 1:
				  		System.out.print(" b |");
				  		break; 
				  	case 2:
				  		System.out.print(" R |");
				  		break;
				  	case 3:
				  		System.out.print(" B |");
				  		break;
				  	case -1:
				  		System.out.print("   |");
				  		break;
				  	case -2:
				  		System.out.print(" X |");
				  		break;
				}
			}
			System.out.println();
			System.out.println("--------------------------------");
		}
	}

		
/*	
	protected void undo() {
	    System.arraycopy(preBoard,0,mainBoard,0,36);
	    turn = (turn+1)%2;
	    repaint();
	}
	
	
	public double score(){
	  return score(0);
	}
	
	public double score(int type) { 
	  double t = score(type, mainBoard);
	  return t;
	}
	
	public double score(int type, int aBoard[]) {
	  double reds, blacks, ret=0.0;
	  int loop;	
	  for(reds = blacks = loop=0; loop<36; loop++) {
		switch(aBoard[loop])
		{
	    	case 2: //redKingType:
	    		reds+=1;
	    	case 0://redType:
	    		reds+=1;
	    		break;
	    	case 3://blackKingType:
	    		blacks+=1;
	    	case 1://blackType:
	    		blacks+=1;
	    		break;
	    	default:
	    		break;
	    }
	  }
	  
	  switch(type)
	  {
	  	case 0://redType:
	  		if(reds==0) {
	  			return 0.0;
	  		}
	  		if(blacks==0) {
	  			return 24.0;
	  		}
	  		ret=reds/blacks;
	  		if(ret>24 || ret<0) myErr("score(i,i)");
	  		return ret;
	  		
	  	case 1://blackType:
	  		if(reds==0) {
	  			return 24.0;
	  		}
	  		if(blacks==0) {
	  			return 0.0;
	  		}
	  		ret=blacks/reds;
	  		if(ret>24 || ret<0) myErr("score(i,i)");
	  		return ret;
	  	default:
	  		break;
	  }
	  return ret;
	}               
	
	  //#returns < zero if the piece could not jump
	  //#or returns the place to jump to
	  //#
	  //#int piece_could_jump(int piece, color,  board)
	protected int piece_could_jump(int piececount, int piececolor, int tBoard[]) {
	  int j[] = new int[4];
	  int ret = -1;
	  int loop;
	
	  if(plainType(piececolor)!=redType && plainType(piececolor)!=blackType)
	    return -1;
	
	  j[0] = piececount-8;
	  j[1] = piececount-10;
	  j[2] = piececount+8;
	  j[3] = piececount+10;
	
	  for(loop=0;loop<4;loop++) {
	    if(j[loop] >=0 && j[loop] <= 31) {
	      if(valid_move(piececount, j[loop], tBoard, piececolor)) {
	            ret=j[loop];
	      }
	    }
	  }
	  return ret;
	}
	
	protected int could_jump(int jumpcol, int tBoard[]) {
	  int ret=-1;
	  int loop;
	  for(loop=0; ret<0 && loop<36; loop++) {
	    if(plainType(tBoard[loop]) == jumpcol) {
	      if(piece_could_jump(loop, jumpcol, tBoard)>=0) {
	          ret=loop;
	      }
	    }
	  }
	  return ret;
	}
	
	protected boolean valid_move(int from, int to, int aBoard[], int colorfor) {
	  if(plainType(colorfor) == userColor) {
	    return ( to>=0 && to<=35 && from >=0 && from<=35 && plainType(aBoard[from])==colorfor && aBoard[to]==emptyType &&
	    		 ( 
	    			(from-to == 4 || from-to==5) ||
	    			((from-to == 10 && plainType(aBoard[from-5])==oppositeType(colorfor)) || ( from-to == 8 && plainType(aBoard[from-4])== oppositeType(colorfor))) ||
	    			(((to-from == 4 || to-from==5) || ((to-from == 10 && plainType(aBoard[from+5])==oppositeType(colorfor)) || (to-from == 8 && plainType(aBoard[from+4])==oppositeType(colorfor)))) && aBoard[from]==kingType(colorfor))
	    		 )
	    	   );
	  }
	  else {
	    return ( to>=0 && to<=35 && from >=0 && from<=35 && plainType(aBoard[from])==colorfor && aBoard[to]==emptyType &&
	    		(
	    		  (to-from == 4 || to-from==5) || ((to-from == 10 && plainType(aBoard[from+5])==oppositeType(colorfor)) || (to-from == 8 && plainType(aBoard[from+4])==oppositeType(colorfor))) ||
	    		  (((from-to == 4 || from-to==5) || ((from-to == 10 && plainType(aBoard[from-5])==oppositeType(colorfor)) || (from-to == 8 && plainType(aBoard[from-4])==oppositeType(colorfor)))) && aBoard[from]==kingType(colorfor))
	    		)
	    	   );
	  }
	}
	
	protected int[] perform_move(int from, int to, int zBoard[]) {
	  zBoard[to]=zBoard[from];
	  zBoard[from]=emptyType;
	  didjump=false;
	  if((to>=0 && to<=3) || (to>=31 && to<=35)) zBoard[to]=kingType(zBoard[to]);
	  if(to>from) {
	    if(to-from == 8) { 
	      didjump=true;
	      zBoard[from+4]=emptyType;
	    }
	    else if(to-from == 10) {
	      didjump=true;
	      zBoard[from+5]=emptyType;
	    }
	  }
	  else if(to<from) {
	    if(from-to == 8) { 
	      didjump=true;
	      zBoard[from-4]=emptyType;
	    }
	    else if(from-to == 10) {
	      didjump=true;
	      zBoard[from-5]=emptyType;
	    }
	  }
	  return zBoard;
	}       
	
	protected void myErr(String message)
	{
	    System.out.println(message);
	}
	
	protected double scores_for_moves(int colorfor, int colormove, int aBoard[], int depth) {
	  int piececount=0;
	  int piece=0;
	  //int oddrow=0;
	  int moves[] = new int[8];
	  int loop=0;
	  int newboard[]=new int[36];
	  int t=0; double temp=0.0;
	  double ret=0.0, lowest=25.0, highest=-1.0;
	  int a=0;
	  boolean valid=false, wasValid=false;
	  boolean anyValid=false;
	
	  ret=score(colormove, aBoard);
	  if(ret<0 || ret>24) myErr("ret out of bounds: "+ret+" :scores_for_moves:[no depth]");
	  if(depth==0 || ret==0 || ret==24) { return ret; }
	
	  if((could_jump(colormove, aBoard))>=0) {
	    for(piececount=0;piececount<36;piececount++) {
	                                
	      piece=aBoard[piececount];
	
	      t=piece_could_jump(piececount, colormove, aBoard);
	      if(t>=0) {
	        valid=valid_move(piececount, t, aBoard, colormove);
	        System.arraycopy(aBoard, 0, newboard, 0, 36);
	        if(valid) perform_move(piececount, t, newboard);
	        wasValid=valid;
	        while(wasValid && didjump
	              && (a=piece_could_jump(t, colormove, newboard))>=0){
	          wasValid=valid_move(t, a, newboard, colormove);
	          if(wasValid) perform_move(t,a,newboard);
	          t=a;
	        }
	
	        if(valid) {
	         anyValid=true;
	          temp=scores_for_moves(colorfor, oppositeType(colormove), newboard, depth-1);
	      if(temp<0 || temp>24) 
	        myErr("temp out of bounds: "+temp+" :scores_for_moves[must jump]");
	          // myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
	          if(temp>highest) { highest=temp; }
	          if(temp<lowest)  { lowest =temp; }
	        }
	      } 
	    }
	  }
	  else {
	    for(piececount=0;piececount<36;piececount++) {
	      piece=aBoard[piececount];                                              
	      if(plainType(piece) == colormove) {
	
	        moves[0]=piececount+5;
	        moves[1]=piececount-5;
	        moves[2]=piececount+4;
	        moves[3]=piececount-4;
	        moves[4]=piececount+8;
	        moves[5]=piececount-8;
	        moves[6]=piececount+10;
	        moves[7]=piececount-10;
	
	        for(loop=0;loop<8;loop++) {
	          a = moves[loop];
	          if((a>=0) && (a<=35)) {
	
	
	            valid=valid_move(piececount, a, aBoard, colormove);
	            System.arraycopy(aBoard,0,newboard,0,36);
	            if(valid) perform_move(piececount, a, newboard);
	            
	            wasValid=valid;
	            while(wasValid && didjump
	                  && (t=piece_could_jump(a, colormove, newboard))>=0){
	              wasValid=valid_move(a, t, newboard, colormove);
	              if(wasValid) perform_move(a,t,newboard);
	              a=t;
	            }
	            if(valid) {
	                anyValid=true;
	              temp=scores_for_moves(colorfor, oppositeType(colormove), newboard, depth-1);
	              if(temp<0 || temp>24) 
	                myErr("temp out of bounds: "+temp+" :scores_for_moves");
	              // myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
	              if(temp>highest) highest=temp;
	              if(temp<lowest) lowest=temp;
	            }
	          }
	        }
	                                        
	      }
	      
	    }
	  }
	  
	  if(lowest<0 || lowest>24) {
	        lowest=score(colormove, aBoard);
	  }
	  
	  ret=24.0-lowest;
	  
	  return ret;
	}

	protected void make_move(int colorfor, int aBoard[]) {
	  int colormove=0;
	  int piececount=0,a=0;
	  int piecet=0, t=0;
	  int newboard[]=new int[36];
	  int piece=0;
	  int oddrow=0, loop=0;
	  int moves[] = new int[8];
	  double ret = 0.0;
	  double temp=0.0;
	  double highest=-1.0, lowest=25.0;
	  int highBoard[]=new int[36], lowBoard[]=new int[36];
	  boolean valid=false, wasValid=false;
	  int from=0, to=0;
	
	  colormove=colorfor;
	
	  piececount=0;
	
	  System.arraycopy(aBoard,0,lowBoard,0,36);
	  System.arraycopy(aBoard,0,highBoard,0,36);
	
	  if((could_jump(colorfor, aBoard))>=0) {
	    for(piececount=0;piececount<36;piececount++) {
	                                
	      piece=aBoard[piececount];
	
	      t=piece_could_jump(piececount, colorfor, aBoard);
	      if(t>=0) {
	             valid=valid_move(piececount, t, aBoard, colorfor);
	             System.arraycopy(aBoard,0,newboard,0,36);
	             if(valid) perform_move(piececount, t, newboard);
	             from=piececount; to=t;
	             wasValid=valid;
	             while(wasValid && didjump && (a=piece_could_jump(t, colorfor, newboard))>=0){
	                 wasValid=valid_move(t, a, newboard, colorfor);
	                 if(wasValid) perform_move(t,a,newboard);
	                 from=t; to=a;
	                 t=a;
	             }
	
	            if(valid) {
	                temp= scores_for_moves(colorfor, oppositeType(colorfor), newboard, 1);
	                if(temp<0 || temp>24) 
	                        myErr("temp out of bounds: "+temp+" :make_move[must jump]");
	                if(temp>highest) { highest=temp; System.arraycopy(newboard,0,highBoard,0,36); }
	                if(temp<lowest)  { lowest =temp; System.arraycopy(newboard,0,lowBoard,0,36); }
	            }
	      } 
	    }
	  }
	  else {
	    for(piececount=0;piececount<36;piececount++) {
	      piece=aBoard[piececount];
	                
	      if(plainType(piece) == colorfor) {
	        moves[0]=piececount+5;
	        moves[1]=piececount-5;
	        moves[2]=piececount+4;
	        moves[3]=piececount-4;
	        moves[4]=piececount+8;
	        moves[5]=piececount-8;
	        moves[6]=piececount+10;
	        moves[7]=piececount-10;
	
	        for(loop=0;loop<8;loop++) {
	          a=moves[loop];
	          if(a>=0 && a<=35) {
	        
	              valid=valid_move(piececount, a, aBoard, colormove);
	              System.arraycopy(aBoard,0,newboard,0,36);
	              if(valid) perform_move(piececount, a, newboard);
	              from=piececount; to=a;
	            
	            wasValid=valid;
	            while(wasValid && didjump
	                  && (t=piece_could_jump(a, colormove, newboard))>=0){
	              wasValid=valid_move(a, t, newboard, colormove);
	              perform_move(a,t,newboard);
	              from=a; to=t;
	              a=t;
	            }
	            if(valid) {
	              temp = scores_for_moves(colorfor, oppositeType(colorfor), newboard, 1);
	              if(temp<0 || temp>24) 
	                myErr("temp out of bounds: "+temp+" :make_move[must jump]");
	              //myErr("temp: "+temp+" highest: "+highest+" lowest: "+lowest);
	              if(temp>highest) { highest=temp; System.arraycopy(newboard,0,highBoard,0,36); }
	              if(temp<lowest)  { lowest =temp; System.arraycopy(newboard,0,lowBoard,0,36); }
	            }
	          }
	        }
	                                        
	      }
	    }
	  }
	
	  if(lowest<0 || lowest>24) {
	      lowest=score(colormove, aBoard);
	  }
	  
	  ret=24.0-lowest;
	
	  if(ret == 24.0) {
	    System.out.println("I will win within "+1+" moves.");
	  }
	  else if(ret == 0.0) {
	    System.out.println("I will lose within "+1+" moves.");
	  }
	  System.arraycopy(lowBoard,0,aBoard,0,36);
	}       
	

	protected boolean pieceIsInside(int piece, int x, int y) {
	  return (x >= mainGrid[piece].xpos && y >= mainGrid[piece].ypos && x <= (mainGrid[piece].xpos + mainGrid[piece].xwidth) && y <= (mainGrid[piece].ypos+mainGrid[piece].ywidth));
	}
	
	  
*/
	
}