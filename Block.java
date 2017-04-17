package game;

import java.awt.*;

public class Block {
	private Point allocation;
	public Point location[];
	public Point size;
	public int num;
	public boolean exist[];
	private Canvas screen;
	
	public Block(Canvas screen){
		this.screen = screen;
		num = 20;
		allocation = new Point(4,5);
		size = new Point(50,20);
		location = new Point[num];
		for(int i=0;i < allocation.y;i++){
			for(int j=0;j < allocation.x;j++){
				
				location[i * allocation.x + j] =
				new Point((screen.getWidth()/allocation.x - size.x)/2 + j * (screen.getWidth() / allocation.x)
				,(i*2 + 1)* size.y);
			}
		}
		exist = new boolean[num];
		for(int i = 0;i < num;i++){
			exist[i] = true;
		}
	}
	
	public void update(){
		for(int i=0;i<num;i++){
			if(exist[i] == false){
				location[i].setLocation(screen.getWidth() + 1, screen.getHeight() + 1);
			}
		}
	}

	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		for(int i=0;i<num;i++){
			if(exist[i]){
				g.fillRect(location[i].x, location[i].y, size.x, size.y);
			}
		}
	}
}
