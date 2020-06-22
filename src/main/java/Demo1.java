import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo1 {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            // Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();
          /*  System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
            WebDriver driver = new ChromeDriver();*/
           // driver.navigate().to("http://the-internet.herokuapp.com/login");

            // Create local HTTP context
            HttpClientContext localContext = HttpClientContext.create();
            // Bind custom cookie store to the local context
            localContext.setCookieStore(cookieStore);

            HttpGet httpget = new HttpGet("http://the-internet.herokuapp.com/login");
            System.out.println("Executing request " + httpget.getRequestLine());

            httpclient.start();

            // Pass local context as a parameter
            Future<HttpResponse> future = httpclient.execute(httpget, localContext, null);

            // Please note that it may be unsafe to access HttpContext instance
            // while the request is still being executed

            System.out.println("Shutting down");

            HttpPost httpPost = new HttpPost("http://the-internet.herokuapp.com/authenticate");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", "tomsmith"));
            params.add(new BasicNameValuePair("password", "SuperSecretPassword!"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            future = httpclient.execute(httpPost,localContext,null);
          // future.
            HttpResponse response = future.get();
            System.out.println(response);
            System.out.println("Response: " + response.getStatusLine());
            List<Cookie> cookies = cookieStore.getCookies();
            cookies = cookieStore.getCookies();

            System.out.println("cookies :::  "+cookies);

            System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.navigate().to("http://the-internet.herokuapp.com/login");

            org.openqa.selenium.Cookie c;
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("Local cookie: " + cookies.get(i));
                c = new org.openqa.selenium.Cookie(cookies.get(i).getName(),cookies.get(i).getValue());
                driver.manage().addCookie(c);
                System.out.println(c);
            }
            driver.navigate().to("http://the-internet.herokuapp.com/secure");


        } finally {
           // httpclient.close();
        }

    }


}
