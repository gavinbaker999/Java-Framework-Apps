import java.awt.*;

public enum connSymbol {NONE(0);
		private final int symID;
		connSymbol(int i) {
			this.symID = i;
		}
		public int symID() {return symID;}
		public void draw(Graphics2D g2d,Point ptOrigin,int rotate) {
			switch(symID) {
				case 0:
					break;
				
				default:
					break;
			}
		}
	};
