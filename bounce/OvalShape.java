/*
 * Student Name: Jonathan Cen
 * Student UPI: zcen576
 * Student ID: 715205036
 **/
package bounce;
/**
 * Class to represent a simple oval.
 *
 */
public class OvalShape extends Shape {
    /**
     * Default constructor that creates a OvalShape instance whose instance
     * variables are set to default values.
     */
    public OvalShape(){
        super();
    }
    /**
     * Creates an OvalShape object with a specified x and y position.
     */
    public OvalShape(int x, int y) {
        super(x,y);
    }
    /**
     * Creates an OvalShape instance with specified x, y, deltaX and deltaY values.
     * The OvalShape object is created with a default width and height.
     */
    public OvalShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }
    /**
     * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
     * height values.
     */
    public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX,deltaY, width, height, text);
    }
    public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX,deltaY, width, height);
    }

    public void doPaint(Painter painter){
        paint(painter);
    }


    public void paint(Painter painter) {
        painter.drawOval(_x, _y,_width,_height);
        super.paint(painter);
    }
}
