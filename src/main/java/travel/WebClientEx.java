/*package travel;

public class WebClientEx extends WebClient
{
    public CookieContainer CookieContainer { get; private set; }

    public WebClientEx()
    {
        CookieContainer = new CookieContainer();
    }

    protected override WebRequest GetWebRequest(Uri address)
    {
        var request = base.GetWebRequest(address);
        if (request is HttpWebRequest)
        {
            (request as HttpWebRequest).CookieContainer = CookieContainer;
        }
        return request;
    }
}
*/