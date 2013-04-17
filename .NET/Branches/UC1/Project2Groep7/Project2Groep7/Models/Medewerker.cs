using System;
using System.Collections.Generic;

namespace Project2Groep7.Models
{
    public partial class Medewerker
    {
        public Medewerker()
        {
            this.Leertrajecten = new List<Leertraject>();
        }

        public int MedewerkerID { get; set; }
        public string Email { get; set; }
        public string Wachtwoord { get; set; }
        public string Voornaam { get; set; }
        public string Naam { get; set; }
        public string TelefoonNr { get; set; }
        public string Organisatie { get; set; }
        public bool Intern { get; set; }
        //public bool RememberMe { get; set; }
        public virtual ICollection<Leertraject> Leertrajecten { get; set; }
    }
}
