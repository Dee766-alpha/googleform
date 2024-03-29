package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    static WebDriverWait wait;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        String name="Deepali";
        WebElement nameText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='AgroKb']/div/div/div/div/input")));
        sendtext(nameText, name);
        }

        public void testCase02() throws InterruptedException{
            
            long epoch = System.currentTimeMillis() / 1000;
            WebElement tbox2=driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
            wait.until(ExpectedConditions.elementToBeClickable(tbox2));
            String value ="I want to be the best QA Engineer!";
            sendtext(tbox2, value+epoch);
            
            }
    



    public void testCase03(){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        
        WebElement ques3=driver.findElement(By.xpath("//span[contains(text(),'How much experience ')]"));
        js.executeScript("arguments[0].scrollIntoView(true);",ques3 );
        List<WebElement> list=driver.findElements(By.xpath("//span[@class='aDTYNe snByac OvPDhc OIC90c']"));
        radiobutton(list, "0 - 2",driver);

       }
       
       
    //    for(WebElement item:list){
    //     wait.until(ExpectedConditions.textToBePresentInElement(item,"0 - 2"));

    //    }
    //    if(status){
    //     System.out.println("0-2 is selected ");
    //    }
    //    else
    //    System.out.println("0-2 is not selected ");
       
    public void testCase04() throws InterruptedException{
        JavascriptExecutor js=(JavascriptExecutor)driver;
        WebElement ques4=driver.findElement(By.xpath("//span[contains(text(),'Which of the following have you learned')]"));
        js.executeScript("arguments[0].scrollIntoView(true);",ques4);
        List<WebElement> list4=driver.findElements(By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"));
        String arr[]={"Java","Selenium","TestNG"};
        checkboxselection(list4, arr, driver);
        Thread.sleep(10000);
        }

        public void testCase05(){
            JavascriptExecutor js=(JavascriptExecutor)driver;
            WebElement calender=driver.findElement(By.xpath("//input[@type='date']"));
            js.executeScript("arguments[0].scrollIntoView(true);",calender);
             LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysAgo = currentDate.minusDays(7);
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
 String date = sevenDaysAgo.format(formatter);
 calender.clear();;
 calender.sendKeys(date);
 String calenderdate=calender.getText();
 System.out.println(date+"date selected in calander");
        }

        public void testCase06() throws InterruptedException{
            JavascriptExecutor js=(JavascriptExecutor)driver;
           WebElement hour= driver.findElement(By.xpath("//input[@aria-label='Hour']"));
           WebElement min= driver.findElement(By.xpath("//input[@aria-label='Minute']"));
           js.executeScript("arguments[0].scrollIntoView(true);",hour);
           LocalTime localtime=LocalTime.now();
           //System.out.println(localtime);
           int hr=localtime.getHour();
           int minute=localtime.getMinute();
           //System.out.println(hr+ "    "+min);
           sendtext(hour, String.valueOf(hr));
           sendtext(min, String.valueOf(minute));
           }

           public void testCase07() throws InterruptedException{
            driver.get("https://www.amazon.in/");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            WebElement submit=driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));
             submit.click();
             WebElement response=driver.findElement(By.xpath("//div[@class='vHW8K']"));
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vHW8K']")));
             String res=response.getText();
             System.out.println(res);
           }

    public static void sendtext(WebElement elementpath,String text) throws InterruptedException{
        Thread.sleep(2000);
        elementpath.clear();
        elementpath.sendKeys(text);
        boolean textcorrect=wait.until(ExpectedConditions.textToBePresentInElementValue(elementpath,text));
        if(textcorrect){
            System.out.println(text+" "+"text entered Successfully");
        }
        else
        System.out.println("Failed to enter the text");




    }

    public static void radiobutton(List<WebElement>list,String text,WebDriver driver){
      
       String options;
       for(WebElement items:list){
       wait.until(ExpectedConditions.elementToBeClickable(items));
       options=items.getText();
        if(options.equals(text)){
            items.click();
            break;
        }
    }
        List<WebElement>selectedlist=driver.findElements(By.xpath("//div[@role='radio']"));
        
        for(WebElement value :selectedlist){
            String radiovaluechecked=value.getAttribute("aria-checked");
            if(radiovaluechecked.equals("true")){
                String radiovalue=value.getAttribute("data-value");
                if(radiovalue.equals(text))
                System.out.println(text+"selected successfully");
                else
                System.out.println(text+"not selected successfully");


            }
        }
 }

 public static void checkboxselection(List<WebElement>list,String arr[],WebDriver driver) throws InterruptedException{
    for(String options:arr){
        for(WebElement listitem:list){
            if(listitem.getText().equals(options)){
                listitem.click();
                Thread.sleep(3000);
            }
            

        }
        }
    

    // //List<WebElement> checklist=driver.findElements(By.xpath("//div[@class='rq8Mwb']"));
    // List<WebElement> checklist = driver.findElements(By.xpath("//div[@class='rq8Mwb']"));
    // for(int i=0;i<checklist.size()-1;i++){
    //     System.out.println("jj");
    //     if(checklist.get(i).isSelected()){
    //         String val=driver.findElement(By.xpath("(//span[@class='aDTYNe snByac n5vBHf OIC90c'])[i]")).getText();
    //         System.out.println(val);
    //     }
    // }

 }
 
 


}







