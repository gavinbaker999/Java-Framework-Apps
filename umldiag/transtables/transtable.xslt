<?xml version="1.0"?>
<xsl:stylesheet 
	version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
	<xsl:template match="transtable">
		<xsl:for-each select="ttkeyword">
			<xsl:value-of select="@keyword"/>
			<xsl:apply-templates select="ttentry"/>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="ttentry">
		<xsl:value-of select="@groupid"/>
	</xsl:template>
</xsl:stylesheet>
