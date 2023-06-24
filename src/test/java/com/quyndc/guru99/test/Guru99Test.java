/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.quyndc.guru99.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ACER NITRO FPT
 */
public class Guru99Test {

    private static WebDriver myBrowser; // Trỏ đến 1 tab trình duyệt sẽ đc new
    private static WebDriver myDataBrowser;
    // Xài chung cho mọi hàm ở dưới

    // Hàm này là tiền trạm dò đường
    // Hàm này chạy đầu tiên trong tất cả các hàm của class này
    // Mọi hàm @Test phải đợi hàm này chạy xong trước
    // Hàm này dùng để chuẩn bị data cho các hàm @Test
    // Hàm tạo môi trường, biến, connection cho các @Test dưới
    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        myBrowser = new ChromeDriver();
        myDataBrowser = new ChromeDriver();
    }

    // Hàm này là bọc hậu, khóa đuôi
    // Hàm này chạy sau cùng
    // Phải đợi tất cả các hàm @Test chạy xong trước
    // Hàm này dùng để dọn dẹp rác, Ram, database do các @Test tạo ra, đóng connection, tập tin
    @AfterAll
    public static void tearDownClass() throws InterruptedException {
        myBrowser.quit();
        myDataBrowser.quit();
    }

    @Test // Test chức năng login như cảu môn JavaWeb
    // user/password đưa vào, login thành công, HELLO <FULLNAME>
    //                                   ko đc  HELLO <USERNAME>
    // Chơi với 2 page: login, dashboard/menu/main menu
    public void TestGuru99() throws InterruptedException {
        
        
        
        myDataBrowser.get("https://demo.guru99.com/");
        
        String Email = "ledinhhoailong@gmail.com";
     // myBrowser.manage().window().maximize(); // Maximize window on launch
        
        WebElement txtEmail = myDataBrowser.findElement(By.cssSelector("input[name='emailid']"));
        txtEmail.sendKeys(Email);
        
        WebElement btnEmail = myDataBrowser.findElement(By.cssSelector("input[value='Submit']"));
        btnEmail.click();
        
        
        WebElement userName = myDataBrowser.findElement(By.cssSelector("tbody tr:nth-child(4) td:nth-child(2)"));
        WebElement password = myDataBrowser.findElement(By.cssSelector("tbody tr:nth-child(5) td:nth-child(2)"));
        
        Thread.sleep(2000);
        
        
        myBrowser.get("https://demo.guru99.com/v4/");
        
        // Find username, password box and insert username, password
        WebElement txtUsername = myBrowser.findElement(By.name("uid"));
        txtUsername.sendKeys(userName.getText());
        
        WebElement txtPassword = myBrowser.findElement(By.name("password"));
        txtPassword.sendKeys(password.getText());

        WebElement btnLogin = myBrowser.findElement(By.name("btnLogin"));
        btnLogin.click(); // Press Login button 
        // Login thành công thì sẽ chuyển trang
        // Khi chuyển trang, hết sức lưu ý: trang mới do trình duyệt tải về
        // Tải về máy tính trang MAIN MENU, DASHBOARD chứa welcome mesage
        // Tải về có độ trễ do mạng lag
        // Nhưng câu lệnh myBrowser.findElement() cứ tiếp tục tìm ngay thẻ
        // welcome nhưng cũ, chưa có trang mới đem về --> Exception
        // Wait, chờ trang tải xuống
        Thread.sleep(3000);

        // Tìm thẻ welcome kiểm tra có đúng hello username ko
        WebElement lblWelcome = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));

        System.out.println("Welcome text: " + lblWelcome.getText());
        
        // Đoạn code này lấy đc value của 1 cái tag đc gọi là
        // Tool cào data, crawler, crawl
        // Đoạn code trên cũng tự động click , tool auto-click
        // Đoạn code trên nếu search youtube video, kéo video tự động, tool cày view
        assertEquals("Manger Id : " + userName.getText(), lblWelcome.getText());
    }

    public static Object[][] initData(){
 
        return new Object[][]{
                               {"mngr510828", "nAzuvyq"}, 
                               {"mngr510529", "UqAdapy"}
                             };
    }
    
    @ParameterizedTest
    @MethodSource("initData")
    public void TestGuru99_(String username, String password) throws InterruptedException {
        myBrowser.get("https://demo.guru99.com/v4/");
        myBrowser.manage().window().maximize(); // Maximize window on launch

        // Find username and insert username
        WebElement txtUsername = myBrowser.findElement(By.name("uid"));
        txtUsername.sendKeys(username);
        
        // Find password and insert password
        WebElement txtPassword = myBrowser.findElement(By.name("password"));
        txtPassword.sendKeys(password);

        WebElement btnLogin = myBrowser.findElement(By.name("btnLogin"));
        btnLogin.click(); // Press Login button

        Thread.sleep(3000);

        WebElement lblWelcome = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
 
        assertEquals("Manger Id : " + username, lblWelcome.getText());
    }

}
