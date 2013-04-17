using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Project2Groep7.Models
{
    public partial class Stelling
    {
        public Stelling()
        {
            this.AntwoordStellingen = new List<AntwoordStelling>();
        }

        [Key]
        [Column(Order = 0)]
        public int StellingspelID { get; set; }
        public string stelling { get; set; }
        [Key]
        [Column(Order = 1)]
        public int StellingID { get; set; }
        public virtual ICollection<AntwoordStelling> AntwoordStellingen { get; set; }
        public virtual Stellingspel stellingspel { get; set; }
    }
}
