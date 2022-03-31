package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	@Test
	public void testeAmbiente() {
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks");
			
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
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks");
			
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


