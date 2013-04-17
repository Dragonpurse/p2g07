using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Doos
    {
        public Doos()
        {
            this.TrajectOnderdelen = new List<TrajectOnderdeel>();
            this.VraagDoos = new List<VraagDoos>();
        }

        public int DoosID { get; set; }
        public string Titel { get; set; }
        public string Omschrijving { get; set; }
        public string Doelgroep { get; set; }
        public string Kernwoorden { get; set; }
        public string Thema { get; set; }
        public virtual ICollection<TrajectOnderdeel> TrajectOnderdelen { get; set; }
        public virtual ICollection<VraagDoos> VraagDoos { get; set; }
    }
}
