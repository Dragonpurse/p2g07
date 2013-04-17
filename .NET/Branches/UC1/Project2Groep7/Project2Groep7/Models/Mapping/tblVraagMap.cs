using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblVraagMap : EntityTypeConfiguration<Vraag>
    {
        public tblVraagMap()
        {
            // Primary Key
            this.HasKey(t => t.VraagID);

            // Properties
            this.Property(t => t.VraagTekst)
                .IsRequired()
                .HasMaxLength(1000);

            // Table & Column Mappings
            this.ToTable("tblVraag");
            this.Property(t => t.VraagID).HasColumnName("VraagID");
            this.Property(t => t.VraagTekst).HasColumnName("VraagTekst");
        }
    }
}
