import java.util.Random;

public class Level {
	//instances of the block
	Block[] block;
	int bWidth = 35; //width of block
	int bHeight = 10; //height of block
	int yInit = 25; 
	int xInit;
	int blockTake = 0; //how many hits a block can take/default 0 for filling in
	int totalW; //Width range of the current block set
	float rowNum; //Number of rows for the current block set
	int maxPerCol = 14; //default amount of blocks per column
	int maxPerRow = 14; //default amount of blocks per row
	int bCount = maxPerCol * maxPerRow; 
	
	int appletW = 500;
	
	int level;
	
	public Level(int l){
		level = l;
		outlineLevel();
	}
	
	//Set max row and column for the level
	public void outlineLevel(){
		if(level == 1){
			maxPerRow = 10;
			maxPerCol = 14;
		}
		
		else if(level == 2){
			maxPerRow = 8;
			maxPerCol = 8;
		}
		else{
			Random ran = new Random();
			maxPerRow = ran.nextInt(5)+9;
			maxPerCol = ran.nextInt(5)+9;
		}
		
		bCount = maxPerCol * maxPerRow;

		block = new Block[bCount];
		
	}
	

	private void drawLevel(int level) {
		// TODO Auto-generated method stub
		if(level == 1){
			drawRowSection(2, 1, maxPerRow/2-1);
			drawColSection(0, 1, 4);
			drawColSection(maxPerRow-1,1,4);
			drawRow(maxPerCol-1);
			
		}
		else if(level == 2){
			drawCol(0);
			drawCol(maxPerCol-1);
			drawRow(maxPerRow/2+1);
			drawRow(maxPerRow/2-2);
			drawRow(maxPerRow-1);
			drawRow(0);
		}
		//Draw a random level
		else{
			if(level%2==0){
				Random ran = new Random();
				ran.setSeed(System.currentTimeMillis() % 1000);
				int sequence = ran.nextInt((int) (System.currentTimeMillis() % 5)+1)+4;
				
				int count = 0;
				
				while(count < bCount){
					
					if(ran.nextBoolean()){
						for(int i = 0; i < sequence; i++){
							if(count >= bCount){
								break;
							}
							block[count].setActive();
							count++;
						}
					}
					else{
						count++;
					}
				}
				for(int i = 0; i < 3;i++){

					//System.out.println(new Random().nextInt(3)+maxPerRow-1);
						eraseColumn(new Random().nextInt(2)+maxPerRow-2);
					//}
					if(ran.nextBoolean()){
						eraseRow(new Random().nextInt(2)+maxPerCol-2);
					}
				}
			}
			else{
				Random ran = new Random();
				for(int i = 0; i < ran.nextInt((int) (System.currentTimeMillis() % 5)+1)+4;i++){
					int ranCol = (ran.nextInt((int) (System.currentTimeMillis() % 5)+1)+4)%maxPerRow;
					int ranColSpan = ran.nextInt(maxPerRow/2)+maxPerCol/2;
					int ranRow = (ran.nextInt((int) (System.currentTimeMillis() % 5)+1)+4)%maxPerCol;
					int ranRowSpan = ran.nextInt(maxPerCol/2)+maxPerRow/2;
					
					drawColSection(ranCol,1,ranColSpan);
					drawRowSection(ranRow,1,ranRowSpan);
				}
			}
			

		}
		
		
	}


	public Block[] getLevel(){
		
		//get the amount of rows with the given # of blocks
		if(bCount <= maxPerRow){
			totalW = bWidth * bCount;
		}
		else{
			rowNum = bCount/maxPerRow;
			Math.ceil(rowNum);
			totalW = maxPerRow*bWidth;
		}

		int xInit = (appletW - totalW)/2;
		
		//give each block its location and setting
		for(int i = 0; i < bCount; i++){
			if(i%maxPerRow == 0 && i>0){
				yInit+=bHeight;
				xInit -= (totalW);
			}
			block[i] = new Block(xInit,yInit,bWidth,bHeight,blockTake);
			xInit+=bWidth;
		}
		
	    //Can now draw levels by activating blocks
		drawLevel(level);
		
		return block;
	}
	
	
	public void eraseRow(int row){
		for(int i = 0; i < maxPerRow; i++){
			block[row*maxPerRow+i].setInactive();
		}
	}
	
	
	public void eraseColumn(int column){
		for(int i = 0; i < maxPerCol; i++){
			block[maxPerRow*i+column].setInactive();
		}
	}
	
	public void drawRow(int row){
		for(int i = 0; i < maxPerRow; i++){
			block[row*maxPerRow+i].setActive();
		}
	}
	
	//Section: 0 = front, 1 = middle, 2 = back
	public void drawRowSection(int row, int section, int amount){
		if(section == 0){
			for(int i = 0; i < amount; i++){
				block[row*maxPerRow+i].setActive();
			}
		}
		else if(section == 1){
			int start = (int) ((Math.ceil(maxPerRow/2))-(Math.ceil(amount/2)));
			int end = start+amount;
			for(int i = 0; i < amount;i++){
				block[(row*maxPerRow+i)+start].setActive();
			}
		}
		else if(section == 2){
			//avoids null errors
			if(amount < maxPerRow)
				for(int i = maxPerRow-1; i > maxPerRow-amount-1;i--){
					block[row*maxPerRow+i].setActive();
				}
		}
	}
	
	
	public void drawCol(int column){
		for(int i = 0; i < maxPerCol; i++){
			block[maxPerRow*i+(column%maxPerRow)].setActive();

		}
	}
	

	public void drawColSection(int column, int section, int amount){
		if(section == 0){
			for(int i = 0; i < amount; i++){
				block[maxPerRow*i+(column%maxPerRow)].setActive();
			}
		}
		else if(section == 1){
			int start = (int) (maxPerRow*(Math.ceil(maxPerCol/2)));
			start -= (amount*maxPerRow)/2;
			

			for(int i = 0; i < amount; i++){


				block[(maxPerRow*i+start)+column].setActive();
			}
		}
		else if(section == 2){
			for(int i = 0; i < amount;i++){
				block[(bCount-(maxPerRow-column))-(i*maxPerRow)].setActive();
			}
		}
		else{
			
		}
	}
	
	public void drawRec(int width,int height, int startPos){
		
		if(width + (startPos%maxPerRow) > maxPerRow){
			if(height + (startPos%maxPerCol) > maxPerCol){
				for(int i = 0; i < height; i++){
					for(int j = 0; j < width; j++){
						block[(startPos-j)-(i*maxPerRow)].setActive();
					}
				}
			}
			else{
				for(int i = 0; i < height; i++){
					for(int j = 0; j < width; j++){
						block[(startPos-j)+(i*maxPerRow)].setActive();
					}
				}
			}
		}
		else{

			if(height + (startPos%maxPerCol) > maxPerCol){

				for(int i = 0; i < height; i++){
					for(int j = 0; j < width; j++){
						block[(startPos+j)-(i*maxPerRow)].setActive();
					}
				}
			}
			else{

				for(int i = 0; i < height; i++){
					for(int j = 0; j < width; j++){
						block[(startPos+j)+(i*maxPerRow)].setActive();
					}
				}
			}
		}
	}
	public int getBlockCount(){
		return bCount;
	}
	public boolean isFinished() {
		boolean finished = true;
		for(int i = 0; i < bCount; i++){
			if(block[i].isActive()){
				finished = false;
			}
		}
		return finished;
	}
	
}