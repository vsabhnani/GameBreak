import java.awt.Rectangle;

public class Player {
	int x = 0;
	int y = 0;
	int w = 0;
	int h = 0;
	int speed = 5;
	int scrWidth;
	int scrHeight;
	int numLives = 0;
	
	boolean isLeft = false;
	boolean isRight = false;
	boolean up = false;
	boolean down = false;
	
	public Player(int xInitial, int yInitial, int width, int height, int speed, int sW, int sH, int lNumber){
		x = xInitial;
		y = yInitial;
		w = width;
		h = height;
		speed = this.speed;
		scrWidth = sW;
		scrHeight = sH;
	    numLives = lNumber;
	}
	public void getMovement(){
		if(x>0){
			if(isLeft == true){
				x-=speed;
			}
		}
		if(x<(scrWidth-w)){
			if(isRight == true){
				x+=speed;
			}
		}
		if(y > (scrHeight-(scrHeight/3.5))){
			if(up == true){
				y-=speed;
			}
		}
		if(y < (scrHeight - h)){
			if(down == true){
				y+=speed;
			}
		}
		
		
	}
	public int getH(){
		return h;
	}
	public int getW(){
		return w;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSp(){
		return speed;
	}
	public boolean goingLeft(){
		return isLeft;
	}
	public boolean goingRight(){
		return isRight;
	}
	public boolean goingUp(){
		return up;
	}
	public boolean goingDown(){
		return down;
	}
	public void setLeft(boolean in){
		isLeft = in;
	}
	public void setRight(boolean in){
		isRight = in;
	}
	public void setUp(boolean in){
		up = in;
	}
	public void setDown(boolean in){
		down = in;
	}
	public int checkRC(){
		int rc = (int) (x + (w *.875));
		return rc;
	}
	public int leftCorner(){
		int lc =(x + (w/8)); 
		return lc;
	}
	public int getLives(){
		return numLives;
	}
	public void setLives(int in){
		numLives = in;
	}
	public void LoseLife(){
		numLives -=1;
	}
	public Rectangle getBounds(){
		return new Rectangle(this.x,this.y,this.w,this.h);
	}
	public String getLivesString() {
		String lifeString;
		lifeString = Integer.toString(numLives);
		return lifeString;
	}
}