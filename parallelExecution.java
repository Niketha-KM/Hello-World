package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
 
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
 
public class parallelExecution implements Runnable {
   
    String port;
    String udid;
   
    public parallelExecution(String portNumber, String udid) {
        this.port = portNumber;
        this.udid = udid;
    }
   
   /* @AndroidFindBy(xpath="//android.widget.TextView[@text='Camera']")
    private WebElement hamburgerIcon;
   
    @AndroidFindBy(id="cin.amazon.mShop.android.shopping:id/gno_drawer_item")
    private WebElement hello;
   
    @AndroidFindBy(id="ap_email")
    private WebElement email;
   
    @AndroidFindBy(id="ap_password")
    private WebElement password;*/
    
    @AndroidFindBy(id="com.google.android.dialer:id/search_box_start_search")
    private WebElement searchContact;
    public void dialContact()
    {
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("refphone");
    	driver.findElement(By.id("com.google.android.dialer:id/cliv_name_textview")).click();
    	
    	
    	
    }
    
    	
    public void receiveCall() throws InterruptedException 
    {
    	Thread.sleep(5000);
    	driver.swipe(335, 225, 225, 334, 1000);
    	System.out.println("Pass");
    }
    
    
    AndroidDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
   
   
    private void openAppAndPerformSomeActions() throws InterruptedException {
        capabilities.setCapability("deviceName", "My Mobile Device");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("platformVersion", "7.0.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.dialer");
        capabilities.setCapability("appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");
       
        try {
            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
            Thread.sleep(10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        searchContact.click();
        //r1.dialContact();
        
        receiveCall();
        
        
        
        //hamburgerIcon.click();
        //hello.click();
    }
   
    public static void main(String args[]) {
        Runnable r1 = new parallelExecution("4723", "NE1GAD56C2900337"); //device id of first mobile device
        Runnable r2 = new parallelExecution("5000", "NE1GAP1730300615"); //device id of second mobile device
       new Thread(r1).start();
       ((parallelExecution) r1).dialContact();
       
     
        new Thread(r2).start();
       
        
    }
 
    @Override
    public void run() {
        try {
			openAppAndPerformSomeActions();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    }
}




