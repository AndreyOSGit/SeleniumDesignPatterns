public class PageObject {
    public class AccountPage extends BasePage {

        @FindAll( {
                @FindBy(css = ".icon-hamburger"),
                @FindBy(css = ".mobile-menu-icon"),
                @FindBy(css = "a.btn")
        } )
        List<WebElement> menu;

        @FindBy(id = "LoggedOutDiv2-mob")
        WebElement signIn;

        @FindBy(css = "input#email")
        WebElement email;

        @FindBy(css = "input#password")
        WebElement password;

        @FindBy(css = "button[type='submit']")
        WebElement submit;

        @FindAll({
                @FindBy(css = ".b-btns-download > div:nth-child(1) a"),
                @FindBy(css = ".b-btns-download > div:nth-child(3) a")}
        )
        List<WebElement> linkToPlatform;

        public AccountPage(){}

        public void openMenu(){
            try {
                menu.get(0).click();
            }catch (TimeoutException ex){
                System.out.println(":> No Menu button");
            }
        }

        public void signIn(){
            signIn.click();
        }

        public void login(){
            email.sendKeys("testeg@gmail.com");
            password.sendKeys("Qazwsx123");
            submit.click();
        }

    }
