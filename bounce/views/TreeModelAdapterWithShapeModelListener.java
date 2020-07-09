package bounce.views;

import bounce.Shape;
import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

import javax.swing.event.TreeModelEvent;


public class TreeModelAdapterWithShapeModelListener extends TreeModelAdapter implements ShapeModelListener{

    public TreeModelAdapterWithShapeModelListener(ShapeModel root) {
        super(root);
    }



    public void update(ShapeModelEvent event) {
        // Unpack event.
        ShapeModelEvent.EventType eventType = event.eventType();
        Shape shape = event.operand();
        int[] childIndices = {event.index()};
        Object[] children = {event.operand()};




        for(int i = 0; i < _treeModelListeners.size(); i++){
            if(eventType == ShapeModelEvent.EventType.ShapeAdded) {
                Object[] path; //{event.source().root(), event.parent()};
                if(event.parent() == event.source().root()){
                    path = new Object[]{event.parent()};//event.parent()
                }else{
                    path = new Object[]{event.source().root(), event.parent()};//event.parent()
                }
                TreeModelEvent toAdd = new TreeModelEvent(event.source(), path, childIndices, children);
                super._treeModelListeners.get(i).treeNodesInserted(toAdd);
            } else if(eventType == ShapeModelEvent.EventType.ShapeRemoved ) {//good for root
                Object[] deletePath;
                if(event.parent() == event.source().root()){
                    deletePath = new Object[]{event.parent()};//event.parent()
                }else{
                     deletePath = new Object[]{event.source().root(), event.parent()};//event.parent()
                }
                TreeModelEvent toRemove = new TreeModelEvent(event.source(), deletePath, childIndices, children);
                super._treeModelListeners.get(i).treeNodesRemoved(toRemove);
            }
        }

    }
    
}
