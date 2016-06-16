import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveListener implements KeyListener{

	Player player;
	
	public MoveListener(Player pl){
	    player = pl;
	}

	public void keyPressed(KeyEvent input) {
	    
		int key = input.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(true);	
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(true);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
	}

	public void keyReleased(KeyEvent input) {
	    
		int key = input.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent input) {
	}

}