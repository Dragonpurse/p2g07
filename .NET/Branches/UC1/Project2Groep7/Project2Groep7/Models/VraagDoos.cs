using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Project2Groep7.Models
{
    public partial class VraagDoos
    {
        [Key]
        public int DoosID { get; set; }
        public string Vraag { get; set; }
        public virtual Doos doos { get; set; }
    }
}
