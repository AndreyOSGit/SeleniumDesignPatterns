public class Frame extends BasePage{

    @FindBy(css = "[href='#home']")
    WebElement depositOrMyAccount;

    @FindAll( {
            @FindBy(id = "iFrame"),
            @FindBy(css = "#frame iframe"),
            @FindBy(xpath = "//*[@id=\"content\"]/iframe"),
            @FindBy(xpath = "//iframe"),
            @FindBy(css = "iframe.account")
    })
    List<WebElement> frame;

    public Frame(){
        driver.switchTo().frame(frame.get(0));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public  void switchToDefaultContent(){
        System.out.println(":> switched to default");
        driver.switchTo().defaultContent();
    }

    public boolean isDepositAuthorised(){
//        wait.until(ExpectedConditions.invisibilityOf(creditCardBtn));
        return depositOrMyAccount.isDisplayed();
    }

    public boolean isMyAccountAuthorised(){
//        wait.until(ExpectedConditions.invisibilityOf(creditCardBtn));
        return depositOrMyAccount.isDisplayed();
    }

}

