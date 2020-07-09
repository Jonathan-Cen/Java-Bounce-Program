package bounce.views;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class TreeModelAdapter implements TreeModel {
    public ShapeModel _adaptee;
    public List<TreeModelListener> _treeModelListeners;

    public TreeModelAdapter(ShapeModel root){
        _adaptee =  root;
        _treeModelListeners = new ArrayList<TreeModelListener>();
    }

    public Object getRoot(){
        return _adaptee.root();
    }

    public Object getChild(Object parent, int index){
        if(parent instanceof NestingShape && index >= 0 && index < ((NestingShape) parent).shapeCount() ){
            return ((NestingShape) parent).shapeAt(index);
        }
        return null;
    }

    public int getChildCount(Object parent){
        int result = 0;
        if(parent instanceof NestingShape){
            NestingShape output = (NestingShape) parent;
            result = output.shapeCount();
        }
        return result;
    }

    public boolean isLeaf(Object node){
        return !(node instanceof NestingShape);
    }

    public void valueForPathChanged(TreePath path, Object newValue){

    }

    public int getIndexOfChild(Object parent, Object child){
        int indexOfChild = -1;
        if(parent instanceof NestingShape && child instanceof Shape){
            NestingShape output = (NestingShape) parent;
            indexOfChild = output.indexOf((Shape)child);
        }
        return indexOfChild;
    }

    public void addTreeModelListener(TreeModelListener l){
        _treeModelListeners.add(l);
    }
    public void removeTreeModelListener(TreeModelListener l){
        _treeModelListeners.remove(l);
    }
}
