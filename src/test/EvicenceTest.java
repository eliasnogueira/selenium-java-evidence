import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

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


public class EvicenceTest {

	WebDriver driver = null;
	ArrayList<SeleniumEvidence> evidences = null;
	EvidenceReport report = null;
	String errors = null;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		evidences = new ArrayList<SeleniumEvidence>();
	}
	

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void basicEvidenceNoError() throws IOException {
		try {
			driver.get("https://github.com/eliasnogueira/selenium-java-evidence");
			
			evidences.add(new SeleniumEvidence("Accessing project page", getScrrenshotAsBase64(driver)));
			
		} catch (Exception e) {
			setError(e, driver);
		} catch (AssertionError ae) {
			setError(ae, driver);
		} finally {
			report = new EvidenceReport(evidences, "BasicEvidenceReportNoError", "Tester", "My Project", errors);
			GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF);
		}
	}

	@Test
	public void basicEvidenceWithAnyException() throws IOException {
		try {
			driver.get("https://github.com/eliasnogueira/selenium-java-evidence");
			
			driver.findElement(By.id("ID_DOESENT_EXISTS"));
			evidences.add(new SeleniumEvidence("Accessing project page", getScrrenshotAsBase64(driver)));
			
		} catch (Exception e) {
			setError(e, driver);
		} catch (AssertionError ae) {
			setError(ae, driver);
		} finally {
			report = new EvidenceReport(evidences, "BasicEvidenceReportWithAnyException", "Tester", "My Project", errors);
			GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF);
		}		
	}
	
	@Test
	public void basicEvidenceWithAnyAssertionError() throws IOException {
		try {
			driver.get("https://github.com/eliasnogueira/selenium-java-evidence");
			evidences.add(new SeleniumEvidence("Accessing project page", getScrrenshotAsBase64(driver)));
			
			String user = driver.findElement(By.cssSelector("span[itemprop='title']")).getText();
			assertEquals("eliasnogueiera", user); // this assertion has an error
			
		} catch (Exception e) {
			setError(e, driver);
		} catch (AssertionError ae) {
			setError(ae, driver);
		} finally {
			report = new EvidenceReport(evidences, "basicEvidenceWithAnyAssertionError", "Tester", "My Project", errors);
			GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF);
		}		
	}	
	
	/**
	 * Returns a screenshot in Base64
	 */
	private String getScrrenshotAsBase64(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	
	/**
	 * Set error if occurs in any catch
	 */
	private void setError(Throwable t, WebDriver driver)  {
		try {
			evidences.add(new SeleniumEvidence(t.getLocalizedMessage(),getScrrenshotAsBase64(driver)));
			errors = t.toString();
			TestCase.fail(t.getMessage());			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
