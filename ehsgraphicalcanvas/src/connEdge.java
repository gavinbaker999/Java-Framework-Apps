	public enum connEdge {NONE(0),TOP(1),RIGHT(2),BOTTOM(3),LEFT(4);
		private final int edgeID;
		connEdge(int i) {
			this.edgeID = i;
		}
		public int edgeID() {return edgeID;}
	};
