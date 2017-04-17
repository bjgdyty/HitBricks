package game;

import java.awt.*;

public class Pad {
	public Point location;
	public Point size;
	private Canvas screen;
	public Pad(Canvas screen){
		this.screen = screen;
		size = new Point(100,20);
		location = new Point((screen.getWidth() - size.x)/2 , screen.getHeight() - size.y);
		
	}
	
	public void padMoveRight(){
		if(location.x + size.x < screen.getWidth()){
			location.x += 8;
		}
	}
	
	public void padMoveLeft(){
		if(location.x > 0){
			location.x -= 8;
		}
	}
	

	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRoundRect(location.x, location.y, size.x, size.y, 10, 10);
	}
}
