using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class AntwoordCasus
    {
        [Key]
        public int AntwoordID { get; set; }
        public string AntwoordTekst { get; set; }
        public string AntwoordBeschrijving { get; set; }
        public Nullable<int> VolgendeVraag { get; set; }
        public int VraagID { get; set; }
        public virtual Vraag vraag { get; set; }
        public virtual Vraag vraag1 { get; set; }
    }
}
