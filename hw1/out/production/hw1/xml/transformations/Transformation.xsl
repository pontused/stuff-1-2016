<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:application ='http://www.applicationschema.com'>
	<xsl:template match="/">
		<xsl:element name="application:person">
			<xsl:element name="application:firstname">
				<xsl:value-of select="/application:application/application:firstname"/>
			</xsl:element>
			<xsl:element name="application:lastname">
				<xsl:value-of select="/application:application/application:lastname"/>
			</xsl:element>
			<xsl:element name="application:gpa">
				<xsl:value-of select="sum(/application:application/application:program/application:course/application:degree) div count(/application:application/application:program/application:course/application:degree)"/>
			</xsl:element>
			<xsl:element name="letter">
				<xsl:value-of select="/application:application/application:letter"/>
			</xsl:element>
			<xsl:element name="application:program">
				<xsl:element name="application:programName">
					<xsl:value-of select="/application:application/application:program/application:programName"/>
				</xsl:element>
				<xsl:element name="application:programName">
					<xsl:value-of select="/application:application/application:program/application:universityName"/>
				</xsl:element>
				<xsl:for-each select="/application:application/application:program/application:course">
					<xsl:element name="application:course">
						<xsl:element name="application:courseName">
							<xsl:value-of select="application:courseName"/>
						</xsl:element>
						<xsl:element name="application:courseNumber">
							<xsl:value-of select="application:courseNumber"/>
						</xsl:element>
						<xsl:element name="application:degree">
							<xsl:value-of select="application:degree"/>
						</xsl:element>
						<xsl:element name="application:startDate">
							<xsl:value-of select="application:startDate"/>
						</xsl:element>
						<xsl:element name="application:finishedDate">
							<xsl:value-of select="application:finishedDate"/>
						</xsl:element>
					</xsl:element>
				</xsl:for-each>
			</xsl:element>
			<xsl:for-each select="/application:application/application:workhistory">
				<xsl:element name="application:workhistory">
					<xsl:element name="application:companyName">
						<xsl:value-of select="application:companyName"/>
					</xsl:element>
					<xsl:element name="application:orgNumber">
						<xsl:value-of select="application:orgNumber"/>
					</xsl:element>
					<xsl:element name="application:employmentRole">
						<xsl:value-of select="application:employmentRole"/>
					</xsl:element>
					<xsl:element name="application:startDate">
						<xsl:value-of select="application:startDate"/>
					</xsl:element>
					<xsl:element name="application:finishedDate">
						<xsl:value-of select="application:finishedDate"/>
					</xsl:element>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>