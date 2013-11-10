
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.eliasnogueira.selenium.evidence.EvidenceReport;
import com.eliasnogueira.selenium.evidence.EvidenceType;
import com.eliasnogueira.selenium.evidence.SeleniumEvidence;
import com.eliasnogueira.selenium.evidence.report.GenerateEvidenceReport;


public class EvidenceTest {

	WebDriver driver = null;
	List<SeleniumEvidence> evidenceList = null;
	EvidenceReport report = null;
	String errorMessage = null;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		evidenceList = new ArrayList<SeleniumEvidence>();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void generatEvidence() throws IOException {
		try {
			driver.get("http://seleniumhq.org");
			evidenceList.add(new SeleniumEvidence("Selenium page", takeScreenshot(driver)));
			
			driver.findElement(By.linkText("Download")).click();
			evidenceList.add(new SeleniumEvidence("Click in Download link", takeScreenshot(driver)));		
			
		} catch (Exception e) {
			
		} finally {
			EvidenceReport report = new EvidenceReport(evidenceList, "MyReportOK", "Elias", "ProjectTest", null);
			GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.DOC);
		}
	}

	@Test
	public void generatEvidenceNOK() throws Exception {		
		try {
			driver.get("http://seleniumhq.org");
			evidenceList.add(new SeleniumEvidence("Selenium page", takeScreenshot(driver)));
			
			driver.findElement(By.linkText("DownloadDDD")).click();
			evidenceList.add(new SeleniumEvidence("Click in Download link", takeScreenshot(driver)));		
			
		} catch (Exception e) {
			evidenceList.add(new SeleniumEvidence("Error", takeScreenshot(driver)));
			errorMessage = e.getMessage();
		} finally {
			report = new EvidenceReport(evidenceList, "MyReportNOK", "Elias", "ProjectTest", errorMessage);
			GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.DOC);
		}
	}	
	
	
	private String takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
