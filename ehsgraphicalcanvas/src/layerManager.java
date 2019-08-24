import java.util.Vector;

public class layerManager {
		private	Vector layers = new Vector();
		private int nextLayerNumber = 1;
		
		public int getNumLayers() {return layers.size();}
		public int getNextLayerNumber() {return nextLayerNumber++;} 
		public void addNewLayer() {
			layers.addElement(new drawingCanvasLayer());
			nextLayerNumber++;
		}
		public void addNewLayer(String name) {
			layers.addElement(new drawingCanvasLayer(name));
			nextLayerNumber++;
		}
		public void addNewLayer(String name,boolean bEnabled) {
			layers.addElement(new drawingCanvasLayer(name,bEnabled));
			nextLayerNumber++;
		}
		public void removeAllLayers() {layers.removeAllElements();nextLayerNumber = 1;}
		public drawingCanvasLayer getLayer(int layerIndex) {
			if (layerIndex >= layers.size()) {return (drawingCanvasLayer)null;}
			return (drawingCanvasLayer)layers.elementAt(layerIndex);
		}
		public boolean layerDisplayed(String name) {
			for (int i=0;i<layers.size();i++) {
				drawingCanvasLayer tmp = (drawingCanvasLayer)layers.elementAt(i);
				if (name.equals(tmp.getLayerName())) {return tmp.getLayerVisable();}
			}
		
			return true;
		}
		public drawingCanvasLayer getLayer(String name) {
			for (int i=0;i<layers.size();i++) {
				drawingCanvasLayer tmp = (drawingCanvasLayer)layers.elementAt(i);
				if (name.equals(tmp.getLayerName())) {return tmp;}
			}
			
			return (drawingCanvasLayer)null;
		}
		public String[] getLayerNames() {
			String[] names = new String[getNumLayers()];

			for (int i=0;i<getNumLayers();i++) {
				drawingCanvasLayer tmp = getLayer(i);
				names[i] = tmp.getLayerName();
			}
			
			return names;
		}
	}
