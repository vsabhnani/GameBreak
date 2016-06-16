import java.awt.Rectangle;

//all the classes relating to "dur" = useless - was trying to do something - didn't work

public class Block {
	int x = 0;
	int y = 0;
	int w = 0;
	int h = 0;
	int dur = 0; 
	
	public Block(int X, int Y, int W, int H, int hits){
		x = X;
		y = Y;
		w = W;
		h = H;
		dur = hits;
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
	public int getHits(){
		return dur;
	}
	public void setX(int in){
		x = in;
	}
	public Rectangle getBounds(){
		return new Rectangle(this.x,this.y,this.w,this.h);
    }
	public void maxHit(int in) {
		dur = in;
	}
	public boolean isActive(){
		if(dur>0){
			return true;
		}
		else{
			return false;
		}
	}
	public void setInactive(){
		dur = 0;
	}
	public void setActive(){
		dur = 1;
	}
	
}