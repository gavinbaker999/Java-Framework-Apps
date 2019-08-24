	public enum symType {NONE("None"),LABEL("Label"),ARCH("Arch"),SIGNAL("Signal"),ENTITY("Entity"),PROCESS("Process"),PROCEDURE("Procedure"),PACKAGE("Package"),COMPONENT("Component"),COMPONENTID("ComponentID"),INTERNAL("Internal"),FILE("File"),CONSTANT("Constant"),FUNCTION("Function"),VARIABLE("Variable"),LOCALVARIABLE("Local Variable"),PARAMETER("Parameter"),TYPE("Type"),CLASS("Class"),INTERFACE("Interface"),ENUM("Enum"),CLASSINTERFACEENUM("ClassInterfaceEnum"),TEMPLATE("Template"),TEMPLATETYPE("TemplateType"),NEWDATATYPE("NewDataType"),CONNECTOR("Connector");
		
		private final String description;
		symType(String description) {
			this.description = description;
		}
		public String getDescription() {return description;}
	}
