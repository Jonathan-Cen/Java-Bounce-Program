/*
 * Student Name: Jonathan Cen
 * Student UPI: zcen576
 * Student ID: 715205036
 **/
package bounce;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting. 
 *
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;

	protected static final String DEFAULT_TEXT = "";
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;

	protected String _text;

	private NestingShape parent;


	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TEXT);

	}
	
	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TEXT);
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TEXT);
	}
	public Shape(int x, int y, int deltaX, int deltaY, String text) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, text);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_text = text;
        parent = null;
	}

	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		this(x, y, deltaX, deltaY, width, height, DEFAULT_TEXT);
	}
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width width of two-dimensional world.
	 * @param height height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;
		//System.out.println("deltaX = " + deltaX());
		//System.out.println("deltaY = " + deltaY());
		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			//System.out.println("Vertical");
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			//System.out.println("Vertical");
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			//System.out.println("Horizontal");
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			//System.out.println("Horizontal");
		}

		_x = nextX;
		_y = nextY;
	}


	protected abstract void doPaint(Painter painter);

	public void paint(Painter painter){

		if(_text != ""){
			painter.drawCenteredText(_text, _width/2 + _x,_height/2 + _y);
		}

	}



	public String getMessage(){
		return this._text;
	}

	public void setMessage(String s){
		this._text = s;
	}

	public void drawMessage(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		FontMetrics fm = g2d.getFontMetrics();
		int stringWidth = fm.stringWidth(this._text);
		int xPosition = _x + (_width - stringWidth)/2;
		int stringHeight = fm.getAscent();
		int yPosition = _y + (_height - stringHeight)/2 + stringHeight;
		g.drawString(this._text, xPosition, yPosition);
	}


	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}
	

	@Override
	public String toString() {
		return getClass().getName();
	}

	public NestingShape parent(){
        return parent;
    }


	public String text(){
		return _text;
	}

	protected void setParent(NestingShape parent){
	    this.parent = parent;
    }

	public java.util.List<Shape> path(){//
		java.util.List<Shape> myPath = new ArrayList<>();
		myPath.add(this);
		for(int i = 0; i < 10; i++){
			if(myPath.get(0).parent != null){
				myPath.add(0,myPath.get(0).parent);
			}
		}
		return myPath;

    }



}
