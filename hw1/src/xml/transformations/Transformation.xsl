<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:application ='http://www.application.com'>
	<xsl:template match="/">
		<xsl:element name="application:person">
			<xsl:element name="application:firstname">
				<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:firstname"/>
			</xsl:element>
			<xsl:element name="application:lastname">
				<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:lastname"/>
			</xsl:element>
			<xsl:element name="application:gpa">
				<xsl:value-of select="sum(/application/program/course/degree) div count(/application/program/course/degree)"/>
			</xsl:element>
			
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>