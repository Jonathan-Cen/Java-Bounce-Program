/*
 * Student Name: Jonathan Cen
 * Student UPI: zcen576
 * Student ID: 715205036
 **/
package bounce;

import java.awt.*;

public class DynamicRectangleShape extends RectangleShape {
    private Color _color;
    private boolean solidColor = true;

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text){
        super(x, y, deltaX, deltaY, width, height, text);
        _color = Color.red; //Default color is red
    }
    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height){
        super(x, y, deltaX, deltaY, width, height);
        _color = Color.red; //Default color is red
    }




    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height,  String text, Color color){ //This sequence of arguments is assumed by A4
        super(x, y, deltaX, deltaY, width, height, text);
        _color = color;

    }
    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color){
        super(x, y, deltaX, deltaY, width, height, DEFAULT_TEXT);
        _color = color;

    }
    @Override
    public void move(int width, int height) {
        super.move(width, height);

        if(this._y + this._deltaY <= 0 || this._y + this._deltaY + this._height >= height){
            solidColor = false;
        }
        if(this._x + this._deltaX <= 0 || this._x + this._deltaX + this._width >= width){
            solidColor = true;
        }

    }
    public void doPaint(Painter painter){
        paint(painter);
    }

    public void paint(Painter painter){
        painter.drawRect(_x,_y,_width,_height);
        if(solidColor) {
            painter.setColor(_color);
            painter.fillRect(_x, _y, _width, _height);
            painter.drawRect(_x, _y, _width, _height);
            painter.setColor(Color.black);
        }
        super.paint(painter);
    }

}
