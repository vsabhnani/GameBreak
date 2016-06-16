import java.applet.Applet;
import java.awt.*;


public class BrickBreaker extends Applet implements Runnable{
	

	Thread runner = null; //allows the game to constantly run
	

	int appletW = 500; //sets the width size of the window where you play
	int appletH = 350; //sets the height size of the window where you play
	

		
	//b config
	int bSize = 5;
	int ySpeed = 3;
	int xSpeed = 2;
	int bXLocation = appletW/2;
	int bYLocation = appletH/2;
	int bTimer; //counts when to start moving the b
	Ball b;
	
	//player config
	int playerWidth = 60;
	int playerHeight = 10;
	int playerSpeed = 5;
	int playerXLocation = 230;
	int playerYLocation = appletH-10;
	int numLives = 3;
	Player player;
	

	Block block[];
	
	//levels
	Level level;
	int currentLevel = 1;
	
	int playerScore = 0;
	
	int finishCount;
	int restartTimer;
	
	int gameRestart;
	
	//power up timer
	int powerUpTimer;
	int forRestart = 66;
	
	//initialize game
	public void init(){
		
		level = new Level(currentLevel);
		block = level.getLevel();
		
		this.setSize(appletW,appletH);
		
		
		player = new Player(playerXLocation,playerYLocation,playerWidth,playerHeight,playerSpeed,appletW,appletH,numLives); //configures the player
		//put in config of b
		b = new Ball(bXLocation,bYLocation,bSize,bSize,xSpeed,ySpeed); //configures the b
		restartTimer = (forRestart*3); //3 seconds

		//set focus on the game when run, so no need to click on it
		this.setFocusable(isEnabled());
		
		this.addKeyListener(new MoveListener(player));
		
	}
	
	
	//start the game
	public void start(){
		if(runner == null){
			runner = new Thread(this);
			runner.start();
		}
	}
	
	public void nextLevel(){
		numLives++;
		currentLevel++;
		bTimer = 0;
		playerScore +=150;
		init();
	}
	

	
	//restart game
	public void reset(){
		gameRestart = 0;
		bTimer = 0;
		numLives = 3;
		playerScore = 0;
		currentLevel = 1;
		init();
	}
	
	//stop game
	public void stop(){
		if(runner != null){
		runner = null;
		}
		reset();
	}
	
	public void paint(Graphics g){
		//paint background
		

		g.setColor(Color.white);
		g.fillRect(0, 0, appletW, appletH);
		

		g.setColor(Color.black);
		g.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
		

		g.setColor(Color.red);
		g.setFont(new Font("Courier", Font.BOLD,20));
		g.drawString("Lives: " + player.getLivesString(),5,20);
		
		
		
		//write current level
		g.setColor(Color.green);
		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.drawString("Level: " + Integer.toString(currentLevel) , appletW-120, 20);
		
		//write current level
		
		//paint the blocks that haven't been hit
		for(int i = 0; i < level.getBlockCount(); i++){
			if(block[i].isActive()){
				g.setColor(Color.black);
				g.fillRect(block[i].getX(),block[i].getY(),block[i].getW(),block[i].getH());
				g.setColor(Color.white);
				g.drawRect(block[i].getX(),block[i].getY(),block[i].getW(),block[i].getH());
			}

		}
		
		//paint the b black
		g.setColor(Color.black);
		g.fillRect(b.getX(), b.getY(), b.getW(), b.getH());
		
		
		//Game Over
		if(player.getLives() == 0){
			g.setColor(Color.RED);
			g.setFont(new Font("courier",Font.BOLD,80));
			g.drawString("Game Over", appletW/100, appletH/2);
			gameRestart++;
			if(gameRestart == 300){
				reset();
			}
		}
	}
	

	

	
	public void checkCollision(){

		//collision with user
		if(b.getBounds().intersects(player.getBounds())){
			//If the b speed is going up, don't allow it to hit the paddle again (gets buggy)
			if(b.ySpeed > 0){
				

					if(!b.isGoingRight()){

						if(b.getCen() < player.leftCorner()){
							b.speedUp();
						}

						else if(b.getCen() > player.checkRC()){
							b.reverseX();
							b.slowDown(.3);
						}
						else{
							if(player.goingRight()){
								b.slowDown();
							}

							else if(player.goingLeft()){
								b.speedUp();
							}

							else{

							}
						}
					}

					else{
						if(b.getCen() < player.leftCorner()){
							b.reverseX();
							b.slowDown(.3);
						}	

						else if(b.getCen() > player.checkRC()){
							b.speedUp();
						}
						else{
							if(player.goingLeft()){
								b.slowDown();
							}

							else if(player.goingRight()){
								b.speedUp();
							}
							else{
							}
						}
				}
				b.reverseY(); //bounce up
			}
		}
        // collision with the blocks
		for(int i = 0; i < level.getBlockCount(); i++){
			if(b.getBounds().intersects(block[i].getBounds())){
				if(block[i].isActive()){
					if((b.getY()+(b.getH()/2)) <= (block[i].getY()+block[i].getH()-1)
						&& ((b.getY()+(b.getH()/2)) >= block[i].getY()+1) &&
						
						((b.getX()+b.getW() <= block[i].getX()) ||
						  (b.getX() <= block[i].getX()+block[i].getW()))){
						
						b.reverseX();
					}
					else{
						b.reverseY();
					}
					block[i].maxHit(block[i].getHits()-1);
					finishCount++;
					playerScore+=10;
				}
				else{
				}
				
			}
		}
		//ball bounce off walls
		if(b.getX() < 0){
			b.reverseX();
		}
		if(b.getY() < 0){
			b.reverseY();
		}
		if(b.getX() > (appletW-b.getW())){
			b.reverseX();
		}
		//if the b passes the paddle, reset
		if(b.getY() > (appletH+300)){
			player.LoseLife();
			b.reset(appletW/2, appletH/2,xSpeed);
			bTimer = 0;
		}
	}
	public void update(Graphics g){ //proper image display - no image left behind

		Graphics gc;
		Image offscreen = null;

		Dimension d = size();
		
		offscreen = createImage(d.width,d.height);
		gc = offscreen.getGraphics();
		gc.setColor(getBackground());
		gc.fillRect(0, 0, d.width, d.height);
		gc.setColor(getForeground());
		paint(gc);
		g.drawImage(offscreen,0,0,this);
	}
	
	public void update(){
		repaint();
		player.getMovement();
		
		if(bTimer>200){  //b starts after set time
			b.move();
		}
		else{
			bTimer++;
		}
		checkCollision();
			if(level.isFinished()){
				nextLevel();
			}

		

	}

	@Override
	public void run(){
		
		while(runner!=null){
			update();
			try {
				Thread.sleep(18);
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}