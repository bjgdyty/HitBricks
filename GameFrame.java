package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.*;



public class GameFrame extends JFrame implements Runnable{
	private FrameRate frameRate;
	private volatile boolean running;
	private KeyboardInput keyboard = new KeyboardInput();
	private Canvas canvas;
	private Thread gameThread;
	private Image im;
	private Pad pad;
	private Block block;
	private Ball ball;
	
	protected void createAndShowGUI(){
		
		canvas = new Canvas();
		canvas.setSize(640, 480);
		
		canvas.setIgnoreRepaint(true);
		
		getContentPane().add(canvas);
		setTitle("Hit Bricks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		
		
		pack();
		
		keyboard = new KeyboardInput();
		canvas.addKeyListener(keyboard);
		
		
		
		setVisible(true);
		gameThread = new Thread(this);
		gameThread.start();	
	}
	
	public void run(){
		initialize();
		running = true;
		while(running){
			gameLoop();
		}
	}
	
	private void gameLoop(){
		processInput();
		gameRender();
		gamePaint();
		
		sleep(9L);
		
		
	}
	
	private void processInput(){
		keyboard.poll();
		if(keyboard.keyDown(KeyEvent.VK_LEFT)){
			pad.padMoveLeft();
		}
		if(keyboard.keyDown(KeyEvent.VK_RIGHT)){
			pad.padMoveRight();
		}
		if(keyboard.keyDown(KeyEvent.VK_SPACE)){
			ball.setAgain();
			ball.ballMove = true;
			
			
		}
		if(keyboard.keyDown(KeyEvent.VK_R)){
			this.initialize();
			
			
			
		}
	}
	
	private void gameRender(){
		
		im = createImage(getWidth(),getHeight());
		Graphics g = im.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(new Font("Courier New",Font.PLAIN,12));
		g.setColor(Color.GREEN);
		frameRate.calculate();
		g.drawString(frameRate.getFrameRate(), 20, 20);
		
		
		pad.draw(g);
		block.update();
		block.draw(g);
		ball.update();
		ball.draw(g);
		
		
		
		
		System.out.println("rendering");
		
	}
	
	private void gamePaint(){
		Graphics g = canvas.getGraphics();
		g.drawImage(im, 0, 0, null);
		
		g.dispose();
		System.out.println("painting");
	}
	
	private void initialize(){
		block = new Block(canvas);
		pad = new Pad(canvas);
		ball = new Ball(canvas,pad,block);
		
		frameRate = new FrameRate();
		frameRate.initialize();
		
	}
	
	private void sleep(long sleep){
		try{
			Thread.sleep(sleep);
		}catch(InterruptedException ex){}
	}
	
	
	public static void main(String[] args) {
		final GameFrame app = new GameFrame();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				app.createAndShowGUI();
				
				
			}
		});
	
	}
}
