import java.awt.Rectangle;
public class Ball {
	int x = 0;
	int y = 0;
	int w = 0;
	int h = 0;
	int ySpeed = 5;
	int xSpeed = 3;

	public Ball(int xCoord, int yCoord, int width, int height,int xCoordSpeed, int yCoordSpeed){
		x = xCoord;
		y = yCoord;
		w = width;
		h = height;
		ySpeed = yCoordSpeed;
		xSpeed = xCoordSpeed;
	}
	public void move(){ //moves the ball with the current xSpeed and ySpeed
		x+=xSpeed;
		y+=ySpeed;
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
	public int getxSpeed(){
		return xSpeed;
	}
	public int getySpeed(){
		return ySpeed;
	}
	public void setX(int xCoord) {
		x = xCoord;
	}
	public void setY(int yCoord) {
		y = yCoord;
	}
	public void setxSpeed(int input){
		xSpeed = input;
	}
	public void setySpeed(int input) {
		ySpeed = input;
	}
	public void reverseX(){ //allows the ball to rebound off of X
		xSpeed = -xSpeed;
	}
	public void reverseY(){//allows the ball to rebound off of Y
		ySpeed = -ySpeed;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,w,h);
	}
	public void speedUp(){ //increases the speed of the ball in the X direction
		if(xSpeed < 0){
			if(xSpeed > -5){
				xSpeed--;
			}
		}
		else{
			if(xSpeed < 5){
				xSpeed++;
			}
		}
	}
	public void slowDown(){ //decreases the speed of the ball in the X direction
		if(xSpeed>0){
			xSpeed = 2;
		}
		else{
			xSpeed = -2;
		}
	}
	public void slowDown(double amt){ //slows down in the X direction by a specific amount
		if(xSpeed>0){
			xSpeed-=amt;
		}
		else{
			xSpeed+=amt;
		}
	}
	public boolean isGoingRight(){ //sees if the ball's speed is greater than 0 (moving right)
		if(xSpeed < 0){
			return false;
		}
		else{
			return true;
		}
    }
	public void reset(int xInitial, int yInitial, int initialSpeed) {

		x = xInitial;
		y = yInitial;
		xSpeed = initialSpeed;
	}
	public int getCen() {
		return x+(w/2);
	}
	
}