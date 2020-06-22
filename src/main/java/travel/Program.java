/*package travel;

public class Program {
	
	static void Main()
    {
        var shows = GetSourceForMyShowsPage();
        Console.WriteLine(shows);
    }

    static string GetSourceForMyShowsPage()
    {
        using (var client = new WebClientEx())
        {
            var values = new NameValueCollection
            {
                { "login_name", "xxx" },
                { "login_pass", "xxxx" },
            };
            // Authenticate
            client.UploadValues("http://www.tvrage.com/login.php", values);
            // Download desired page
            return client.DownloadString("http://www.tvrage.com/mytvrage.php?page=myshows");
        }
    }

}
*/