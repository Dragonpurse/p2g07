using Project2Groep7.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Deelnemer
    {
        public Deelnemer()
        {
            this.Leertrajecten = new List<Leertraject>();
        }

        [Key]
        public string Email { get; set; }
        public string Voornaam { get; set; }
        public string Naam { get; set; }
        public string Wachtwoord { get; set; }
        public virtual ICollection<Leertraject> Leertrajecten { get; set; }
    }
}
