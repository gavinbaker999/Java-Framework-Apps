import javax.xml.transform.Transformer;

interface xslTransformerUtils {
	// e.g. void transformer.setParameter("name","value");
	// void transformer.clearParameters();
	//
	// String transformer.getOutputProperty(String name);
	// void transformer.setOutputProperty(String name,String value);
	// to clear a single property set value=null
	//
	public void preTransform(Transformer transformer);
}
