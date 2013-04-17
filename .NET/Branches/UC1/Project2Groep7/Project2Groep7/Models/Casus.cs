using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Casus
    {
        public Casus()
        {
            this.TrajectOnderdelen = new List<TrajectOnderdeel>();
        }

        public int CasusID { get; set; }
        public string Titel { get; set; }
        public string Omschrijving { get; set; }
        public string LocatieFilm { get; set; }
        public string Doelgroep { get; set; }
        public string Situatieschets { get; set; }
        public int EersteVraag { get; set; }
        public string Kernwoorden { get; set; }
        public virtual ICollection<TrajectOnderdeel> TrajectOnderdelen { get; set; }
        public virtual Vraag vraag { get; set; }
    }
}
