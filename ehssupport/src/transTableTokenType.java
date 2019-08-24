import java.awt.Color;

public enum transTableTokenType {
		NONE {
			public Color getTokenColor() {
				return Color.white;
			}
			public String getDescription() {
				return "None";
			}
		},
		OPTIONAL {
			public Color getTokenColor() {
				return Color.red;
			}
			public String getDescription() {
				return "Optional";
			}
		},
		KEYWORD {
			public Color getTokenColor() {
				return new Color(255,255,170);
			}
			public String getDescription() {
				return "Keyword";
			}
		},
		NORMAL {
			public Color getTokenColor() {
				return new Color(255,255,170);
			}
			public String getDescription() {
				return "Normal";
			}
		},
		MULTIPLE {
			public Color getTokenColor() {
				return Color.green;
			}
			public String getDescription() {
				return "Multiple";
			}
		};
		public abstract Color getTokenColor();
	}
