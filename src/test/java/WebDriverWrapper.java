public class Wrapper extends RemoteWebDriver implements WebDriver, JavascriptExecutor {

    private static WebDriver driver;
    public static RemoteWebDriver remoteWebDriver;
    private WebDriverWait wait;

    public Wrapper(WebDriver driver){
        Wrapper.driver =  driver;
        remoteWebDriver = (RemoteWebDriver) driver;
        wait = new WebDriverWait(driver, 15);
    }

    @Override
    public void get(String s) {
        driver.get(s);
        driver.manage().timeouts().setScriptTimeout(10,  TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {

        if (driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) driver).executeScript(script, args);
        } else {
            throw new WebDriverException("Wrapped webdriver does not implement JavascriptExecutor: " + driver);
        }
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {

        if (driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
        } else {
            throw new WebDriverException("Wrapped webdriver does not implement JavascriptExecutor: " + driver);
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return remoteWebDriver.getScreenshotAs(outputType);
    }


}
