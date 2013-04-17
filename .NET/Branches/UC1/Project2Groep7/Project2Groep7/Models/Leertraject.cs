using Project2Groep7.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public class LeertrajectenModel
    {
        public List<Leertraject> leertrajecten { set; get; }
    }

    public partial class Leertraject
    {
        public Leertraject()
        {
            this.TrajectOnderdelens = new List<TrajectOnderdeel>();
            this.Deelnemers = new List<Deelnemer>();
            this.Medewerkers = new List<Medewerker>();
        }

        public string LeertrajectCode { get; set; }
        public string Titel { get; set; }
        public string Omschrijving { get; set; }
        public System.DateTime StartDatum { get; set; }
        public string Doelgroep { get; set; }
        public System.DateTime BeschikbaarheidVan { get; set; }
        public System.DateTime BeschikbaarheidTot { get; set; }
        public bool Actief { get; set; }
        public bool Gearchiveerd { get; set; }
        public virtual ICollection<TrajectOnderdeel> TrajectOnderdelens { get; set; }
        public virtual ICollection<Deelnemer> Deelnemers { get; set; }
        public virtual ICollection<Medewerker> Medewerkers { get; set; }
    }
}
