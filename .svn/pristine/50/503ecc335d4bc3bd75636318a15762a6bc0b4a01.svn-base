using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;

namespace Project2Groep7.Models
{
    public static class Utils
    {
        public static String getYoutubeVideoUrl(string url)
        {
            Regex YoutubeVideoRegex = new Regex(@"youtu(?:\.be|be\.com)/(?:(.*)v(/|=)|(.*/)?)([a-zA-Z0-9-_]+)", RegexOptions.IgnoreCase);
            Match youtubeMatch = YoutubeVideoRegex.Match(url);
            string id = string.Empty;

            if (youtubeMatch.Success)
                id = youtubeMatch.Groups[4].Value;

            return "http://www.youtube.com/embed/" + id;
        }
    }
}
