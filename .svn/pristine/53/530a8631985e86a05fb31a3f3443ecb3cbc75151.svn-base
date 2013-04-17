using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Project2Groep7.Models;
using MvcContrib.Pagination;
using ICSharpCode.SharpZipLib.Zip;
using System.IO;
using System.Net;
using System.Text.RegularExpressions;


namespace Project2Groep7.Controllers
{
    [Authorize]
    public class OnderdeelController : Controller
    {
        private readonly IOnderdeelRepository onderdeelRepository;
        public IEnumerable<TrajectOnderdeel> trajectOnderdelen { get; set; }

        public OnderdeelController(IOnderdeelRepository onderdeelRepository)
        {
            this.onderdeelRepository = onderdeelRepository;
        }

        public ViewResult Index(int onderdeelID, string type)
        {
            TrajectOnderdeel onderdeel = onderdeelRepository.FindById(onderdeelID, type);
            return View(onderdeel);
        }

        public ActionResult Download(string url)
        {
            string fileName = System.IO.Path.GetFileName(url);
            

            if (url.Contains("://"))
            {
                using (WebClient Client = new WebClient())
                {
                    var byteArr = Client.DownloadData(url);
                    return File(byteArr, System.Net.Mime.MediaTypeNames.Application.Octet, fileName);
                }
            }
            else
                return File(url, System.Net.Mime.MediaTypeNames.Application.Octet, fileName);
        }






        //byte[] GetFile(string s)
        //{
        //    System.IO.FileStream fs = System.IO.File.OpenRead(s);
        //    byte[] data = new byte[fs.Length];
        //    int br = fs.Read(data, 0, data.Length);
        //    if (br != fs.Length)
        //        throw new System.IO.IOException(s);
        //    return data;
        //}
    }
}

