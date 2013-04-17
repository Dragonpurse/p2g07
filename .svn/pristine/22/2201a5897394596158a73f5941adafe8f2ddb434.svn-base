using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Stellingspel
    {
        public Stellingspel()
        {
            this.Stellingen = new List<Stelling>();
            this.TrajectOnderdelen = new List<TrajectOnderdeel>();
        }

        [Key]
        public int StellingspelID { get; set; }
        public string Titel { get; set; }
        public string Omschrijving { get; set; }
        public string Doelgroep { get; set; }
        public string Kernwoorden { get; set; }
        public virtual ICollection<Stelling> Stellingen { get; set; }
        public virtual ICollection<TrajectOnderdeel> TrajectOnderdelen { get; set; }
    }
}
