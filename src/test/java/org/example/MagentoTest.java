package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.data.DataProduct;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MagentoTest {
    private static WebDriver driver;

    public static String firstname = "Asidik";
    public static String lastname = "Alamsyah";

    public static String company = "PT. Software Testing Board";
    public static String address1 = "RT.01";
    public static String address2 = "RW.01";
    public static String address3 = "Jalan Jalan";
    public static String city = "New York";
    public static String state = "Barisal Division";
    public static String zip = "12345";
    public static String country = "Bangladesh";
    public static String telephone = "087293299012";

    public static String methode = "ko_unique_3";

    public static String shipping_checkout_detail = " Asidik Alamsyah " + address1 + ", " + address2 + ", " + address3 + " " + city + ", " + state + " " + zip + " " + country + " " + telephone;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public static ArrayList<DataProduct> dataProduct = new ArrayList<>();

    @Test
    @Order(1)
    void createAccount() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = homePage.clickSignUp();
        String email = StringUtils.getRandomString() + "@gmail.com";
        createAccountPage.setFirstName("Asidik");
        createAccountPage.setLastName("Alamsyah");
        createAccountPage.setEmail(email);
        createAccountPage.setPassword("Qwerty1234567890");
        createAccountPage.setConfirmPassword("Qwerty1234567890");
        ProfilePage profilePage = createAccountPage.clickCreateAccount();
        assertTrue(Pattern.compile("(.*)" + " Asidik Alamsyah " + email + " " + "(.*)", Pattern.CASE_INSENSITIVE).matcher(profilePage.getBoxInformation()).find());
    }

    @Test
    @Order(2)
    void testAddToCart() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.gotoDashboard();
        ProductPage productPage = dashboardPage.clickBagsWithHover();
        productPage.clickProduct(0);
        productPage.setQty(3);
        dataProduct.add(new DataProduct(productPage.getTitle(), productPage.getQty()));
        productPage.addToCart("3");
        productPage.clickProduct(1);
        productPage.setQty(2);
        dataProduct.add(new DataProduct(productPage.getTitle(), productPage.getQty()));
        productPage.addToCart("5");
        productPage.clickIconCart();
        assertEquals(dataProduct.size(), productPage.getCart().size());
        for (int i = 0; i < dataProduct.size(); i++) {
            assertEquals(dataProduct.get(i).getName(), productPage.getCart().get(i).getName());
            assertEquals(dataProduct.get(i).getQty(), productPage.getCart().get(i).getQty());
        }
        productPage.clickCheckout();
    }

    @Test
    @Order(3)
    void testCheckout() {
        ShippingPage shippingPage = new ShippingPage(driver);
        shippingPage.setCompany(company);
        shippingPage.setAddress1(address1);
        shippingPage.setAddress2(address2);
        shippingPage.setAddress3(address3);
        shippingPage.setCity(city);
        shippingPage.setZip(zip);
        shippingPage.selectCountry(country);
        shippingPage.setState(state);
        shippingPage.setTelephone(telephone);
        shippingPage.setMethode(methode);
        PaymentPage paymentPage = shippingPage.clickContinue();
        assertTrue(Pattern.compile("(.*)" + shipping_checkout_detail + "(.*)", Pattern.CASE_INSENSITIVE).matcher(paymentPage.getShippingDetail()).find());
        SuccessPage successCheckoutPage = paymentPage.clickSubmit();
        successCheckoutPage.clickOrderNumber();
    }

    @Test
    @Order(4)
    void testCheckoutSuccess() {
        SuccessPage successPage = new SuccessPage(driver);
        assertEquals(dataProduct.size(), successPage.getListOrder().size());
        for (int i = 0; i < dataProduct.size(); i++) {
            assertEquals(dataProduct.get(i).getName(), successPage.getListOrder().get(i).getName());
            assertEquals(dataProduct.get(i).getQty(), successPage.getListOrder().get(i).getQty());
        }
    }

    @Test
    @Order(5)
    void testSignOutLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignOut();
    }

    @Test
    @Order(6)
    void testLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLogin();
        String email = "asidikalamsyah@gmail.com";
        String pass = "Qwerty1234567890";
        ProfilePage profilePage = loginPage.login(email, pass);
        assertTrue(Pattern.compile("(.*)" + " Asidik Alamsyah " + email + " " + "(.*)", Pattern.CASE_INSENSITIVE).matcher(profilePage.getBoxInformation()).find());
    }
}