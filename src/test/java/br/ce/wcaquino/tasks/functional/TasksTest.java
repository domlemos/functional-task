package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException   {
		DesiredCapabilities cap = DesiredCapabilities.chrome();		
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), cap);
		driver.navigate().to("localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@Test
	public void testeAmbiente() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = acessarAplicacao();
		try {
			driver.navigate().to("http://192.168.5.95:8001/tasks");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			

			driver.findElement(By.id("addTodo")).click();
			

			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Sucess!", message);
		} finally {
			driver.quit();
		}
	}
	
	
	 @Test public void naoDeveSalvarTarefaSemDescricao() throws
	  MalformedURLException { 
		  //WebDriver driver = new ChromeDriver(); 
		  WebDriver driver = acessarAplicacao();
		  try {
		  driver.navigate().to("http://192.168.5.95:8001/tasks");
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  driver.findElement(By.id("addTodo")).click();
		  
		  driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		  
		  driver.findElement(By.id("saveButton")).click();
		  
		  String message = driver.findElement(By.id("message")).getText();
	  
	  Assert.assertEquals("Fill the task description", message); 
	  } finally {
		  	driver.quit(); 
	  	} 
	  }
	 
}


