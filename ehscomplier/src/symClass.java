	public enum symClass {NONE("None"),GLOBAL("Global"),BULITIN("Builtin"),LIBRARY("Library"),CONTAINER("Container"),APPLICATION("Application"),TEMPLATETYPE("TemplateType");
		
		private final String description;
		symClass(String description) {
			this.description = description;
		}
		public String getDescription() {return description;}
	}
