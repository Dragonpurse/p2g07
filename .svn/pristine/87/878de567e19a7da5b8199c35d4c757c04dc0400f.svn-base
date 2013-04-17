using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class Vraag
    {
        public Vraag()
        {
            this.AntwoordCasussen = new List<AntwoordCasus>();
            this.AntwoordCasussen1 = new List<AntwoordCasus>();
            this.Casussen = new List<Casus>();
        }

        [Key]
        public int VraagID { get; set; }
        public string VraagTekst { get; set; }
        public virtual ICollection<AntwoordCasus> AntwoordCasussen { get; set; }
        public virtual ICollection<AntwoordCasus> AntwoordCasussen1 { get; set; }
        public virtual ICollection<Casus> Casussen { get; set; }
    }
}
