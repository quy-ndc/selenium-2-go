/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.quyndc.selenium.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author ACER NITRO FPT
 */
public class Selenium2Go {

    public static void main(String[] args) {
        playWithGoogleSearch();
    }
    
    // Functional Testing - Test chức năng của 1 app chạy đúng hay sai
    // KO quan tâm tới code, chỉ test chức năng 
    // Việc code mà ko quan tâm đến code, chuyện gì xảy ra đằng sau nút bấm gọi là BLACK-BOX TESTING
    // Ngược lại BLACK-BOX thì là WHITE-BOX, cho xem bên trong
    // JUnit, TESTING, Unit Testing là WHITE_BOX TESTING vì phải nhìn code để thiết kế test case
    
    // Để kiểm thử chức năng, hiệu năng thì DEV, QC phải thiết kế các test case với mục đích xem 
    // test case đã bao phủ bao nhiêu tình huống xài app/hàm còn để biết bao nhiêu test case sai/đúng khi run hàm
    
    // Unit test phải có test case!!
    // Functional Test phải có test case
    // Bộ data input vào và expected value và step để dùng hàm, dùng app
    
    // Ví dụ: test hàm getF() có data: 0, 1, -1, 5, 20 
    // Xài hàm getF(0), getF(1)
    // Expected: 0! = 1, 1! = 1, -1! = EXCEPTION
    
    // Nếu ta test app thì test case như thế nào?
    
    // Test Case #1: Check if Google Search runs well or not with a keyword 
    // Step/Procedures
    // 1. Open a certain browser (e.g. Chrome)
    // 2. Type the following url: https://google.com
    // 3. Hit Enter to reach Google search page
    // 4. Type a certain keyword in the Search box, e.g Trang nemo
    // 5. Hit enter to execute the Search Engine
    
    // Expected result:
    // 3.1 The Google Search page ís shown
    // 5.1 All pages with the keyword "Trang nemo" included are shown
    
    // Làm khoảng 5-10 test case kiểu này 
    
    // Người đọc lại các bước trên và làm lại bằng tay --> manual testing
    // Nếu các bước trên làm bằng SELENIUM IDE, KATALON, AKA TEST, RANOREX, TELERIK, TEST COMPLETE --> AUTOMATION TEST
    // Các tool này có cơ chế record lại các thao tác trên web app để so sánh value xử lý của app có đúng kì vọng ko
    // hoặc generate ra code để chủ động kiến Web app chạy bằng code --> TEST SCRIPT
    
    public static void playWithGoogleSearch() {
        WebDriver myBrowser; // Đây là biến object sẽ trỏ vào 1 tab trình duyệt
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); 
        // Tải phần mềm trung gian giúp điều khiển trình duyệt qua code
        // thay người vào trong RAM. Nếu coi browser là database (chứa tags)
        // thì JDBC tương đương với 1 cái gọi là Web Driver
        // ~~ hàm Class.forName() của JDBC, load driver vô JVM
        
        ChromeOptions opt = new ChromeOptions(); // Configurate Google on launch
    
        opt.addArguments("--incognito"); // Run chrome in incognito mode
      //opt.addArguments("--lang=ja-JP"); // Run chrome in Japanese or any Language http://lingoes.net/en/translator/langcode.htm
      //opt.addArguments("");
                
        myBrowser = new ChromeDriver(opt); // opt = use option above
        myBrowser.get("https://google.com"); // Decide what website you're opening
        
        myBrowser.manage().window().maximize(); // Maximize window on launch
        
        // Ta định vị 1 cái tag trong myBrowser
        // Mỗi tag đc xem là 1 project, UI object; ta định vị 1 tag
        // Bằng hàm      findElement by()
        
        //                                          Chuỗi định vị 1 tag
        //                                          Where ra 1 tag trong cây DOM
        WebElement searchBox = myBrowser.findElement(By.name("q")); // find search box via name using SelectorHub
        searchBox.sendKeys("giao.lang"); // What is in the search box
        searchBox.submit(); // press Enter
        
        
        
        myBrowser.close();
       
        
    }
}
