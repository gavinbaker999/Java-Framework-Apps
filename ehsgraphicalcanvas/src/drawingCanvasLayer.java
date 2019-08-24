public class drawingCanvasLayer {
		private	String		layerName;
		private	boolean		layerVisable;
		
		public drawingCanvasLayer() {
			layerName = "Layer 1";
			layerVisable = true;
		}
		public drawingCanvasLayer(String name) {
			layerName = name;
			layerVisable = true;
		}
		public drawingCanvasLayer(String name,boolean bEnabled) {
			layerName = name;
			layerVisable = bEnabled;
		}
		public String getLayerName() {return layerName;}
		public void setLayerName(String name) {layerName = name;}
		public boolean getLayerVisable() {return layerVisable;}
		public void setLayerVisable(boolean visable) {layerVisable = visable;}
		public String getAsXML() {return "<layer name=\""+layerName+"\"visable=1\""+String.valueOf(layerVisable)+"\"";}
	}
