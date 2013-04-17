
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Document// : TrajectOnderdeel
    {
        public Document()
        {
            this.TrajectOnderdelen = new List<TrajectOnderdeel>();
        }

        public int DocumentID { get; set; }
        public string Titel { get; set; }
        public string Omschrijving { get; set; }
        public string Locatie { get; set; }
        public string Doelgroep { get; set; }
        public string Kernwoorden { get; set; }
        public virtual ICollection<TrajectOnderdeel> TrajectOnderdelen { get; set; }
        public string getYoutubeVideoUrl(string url) { return Utils.getYoutubeVideoUrl(url); }

    }
}
