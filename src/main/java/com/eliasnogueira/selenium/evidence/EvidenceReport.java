package com.eliasnogueira.selenium.evidence;

import java.util.List;

public class EvidenceReport {

	private List<SeleniumEvidence> evidenceList = null;
	private String reportName = null;
	private String tester = null;
	private String project = null;
	private String exceptionString = null;
	
	public EvidenceReport(List<SeleniumEvidence> evidenceList, String reportName, String tester,
			String project, String exceptionString) {
		this.evidenceList = evidenceList;
		this.reportName = reportName;
		this.tester = tester;
		this.project = project;
		this.exceptionString = exceptionString;
	}
	
	/**
	 * @return the evidenceList
	 */
	public List<SeleniumEvidence> getEvidenceList() {
		return evidenceList;
	}
	
	/**
	 * @param evidenceList the evidenceList to set
	 */
	public void setEvidenceList(List<SeleniumEvidence> evidenceList) {
		this.evidenceList = evidenceList;
	}
	
	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}
	
	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	
	public String getTester() {
		return tester;
	}
	
	public void setTester(String tester) {
		this.tester = tester;
	}
	
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	
	/**
	 * @return the exceptionString
	 */
	public String getExceptionString() {
		return exceptionString;
	}
	
	/**
	 * @param exceptionString the exceptionString to set
	 */
	public void setExceptionString(String exceptionString) {
		this.exceptionString = exceptionString;
	}
	
	
}
