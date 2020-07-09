package bounce;

import java.util.ArrayList;

public class NestingShape extends Shape {
    public ArrayList<Shape> children = new ArrayList<>();

    public NestingShape(){
        super();
    }

    public NestingShape (int x, int y){
        super(x, y);
    }


    public NestingShape (int x, int y, int deltaX, int deltaY){
        super(x,y,deltaX,deltaY);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height){
        super(x, y, deltaX, deltaY, width, height);
    }

    public NestingShape (int x, int y, int deltaX, int deltaY, int width, int height, String text){
        super(x, y, deltaX, deltaY, width, height, text);
    }


     public void move (int width, int height){
         super.move(width, height);//move the nested shape
         //move the children
         for(int i = 0; i < children.size(); i++){
             children.get(i).move(_width, _height);
         }

     }


     public void doPaint(Painter painter){
        paint(painter);
     }

    public void paint (Painter painter){
        painter.drawRect(_x,_y,_width,_height);//paints the nested shape
        //paint the children
        painter.translate(_x,_y);
        for(int i = 0; i < children.size(); i++){
            children.get(i).paint(painter);
        }
        painter.translate(-_x,-_y);


    }





    void add (Shape shape) throws IllegalArgumentException{
        if(this.existInDescendant(shape)){
            throw new IllegalArgumentException();
        }else if(shape._x + shape._width > this._width + this._x || shape._y +shape._height > this._y + this._height){
            throw new IllegalArgumentException();
        }else{
            children.add(shape);
            shape.setParent(this);
        }
    }

     void remove (Shape shape){
         if(children.contains(shape)){
             children.remove(shape);
             shape.setParent(null);
         }
     }

    public Shape shapeAt (int index) throws IndexOutOfBoundsException{
        int numOfChildren = children.size();
        if(index < 0 || index > numOfChildren - 1){
            throw new IndexOutOfBoundsException();
        }else{
            return children.get(index);
        }
    }

    public int shapeCount (){
        return children.size();
    }


    public int indexOf (Shape shape){
        if(children.contains(shape)){
            return children.indexOf(shape);
        }else{
            return -1;
        }
    }

    public boolean existInDescendant(Shape shape){
        for(int i = 0; i < children.size(); i++){
            if(children.get(i) == shape){
                return true;
            }else if(children.get(i) instanceof NestingShape){
                if(((NestingShape) children.get(i)).children.contains(shape) == true){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean contains (Shape shape){//check its children and check its children's children
        return this.children.contains(shape);
    }
}
