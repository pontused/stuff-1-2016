<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
	xmlns:transcript ="http://www.persontranscript.com"
	xsi:schemaLocation='http://www.persontranscript.com schemas/TranscriptTransformationOutput.xsd'>
    <xsl:template match="/">
		<xsl:element name="transcript:persontranscript">
			<xsl:element name="transcript:person">
				<xsl:element name="transcript:firstname">
					<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:firstname"/>
				</xsl:element>
				<xsl:element name="transcript:lastname">
					<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:lastname"/>
				</xsl:element>
				<xsl:element name="transcript:gpa">
					<xsl:value-of select="sum(/transcript:persontranscript/transcript:person/transcript:program/transcript:course/transcript:degree) div count(/transcript:persontranscript/transcript:person/transcript:program/transcript:course/transcript:degree)"/>
				</xsl:element>
				<!--
				Om vi inte vill ha med ssn i det transformerade dokumentet
				<xsl:element name="transcript:ssn">
					<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:ssn"/>
				</xsl:element>
				-->
				<xsl:element name="transcript:program">
					<xsl:element name="transcript:programName">
						<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:program/transcript:programName"/>
					</xsl:element> 
					<xsl:element name="transcript:universityName">
						<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:program/transcript:universityName"/>
					</xsl:element>
					<xsl:for-each select="/transcript:persontranscript/transcript:person/transcript:program/transcript:course"> 
						<xsl:element name="transcript:course">
						    <xsl:element name="transcript:courseName">
								<xsl:value-of select="transcript:courseName"/>
							</xsl:element>  
							<xsl:element name="transcript:courseNumber">
								<xsl:value-of select="transcript:courseNumber"/>
							</xsl:element>
							<xsl:element name="transcript:degree">
								<xsl:value-of select="transcript:degree"/>
							</xsl:element>
							<xsl:element name="transcript:startDate">
								<xsl:value-of select="transcript:startDate"/>
							</xsl:element>
							<xsl:element name="transcript:finishedDate">
								<xsl:value-of select="transcript:finishedDate"/>
							</xsl:element>
						</xsl:element>
					</xsl:for-each> 
					<xsl:element name="transcript:startDate">
						<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:program/transcript:startDate"/>
					</xsl:element> 
					<xsl:element name="transcript:finishDate">
						<xsl:value-of select="/transcript:persontranscript/transcript:person/transcript:program/transcript:finishDate"/>
					</xsl:element> 
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
