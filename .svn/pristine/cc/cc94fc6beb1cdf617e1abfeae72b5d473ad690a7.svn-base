using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblAntwoordCasuMap : EntityTypeConfiguration<AntwoordCasus>
    {
        public tblAntwoordCasuMap()
        {
            // Primary Key
            this.HasKey(t => t.AntwoordID);

            // Properties
            this.Property(t => t.AntwoordTekst)
                .IsRequired()
                .HasMaxLength(255);

            this.Property(t => t.AntwoordBeschrijving)
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblAntwoordCasus");
            this.Property(t => t.AntwoordID).HasColumnName("AntwoordID");
            this.Property(t => t.AntwoordTekst).HasColumnName("AntwoordTekst");
            this.Property(t => t.AntwoordBeschrijving).HasColumnName("AntwoordBeschrijving");
            this.Property(t => t.VolgendeVraag).HasColumnName("VolgendeVraag");
            this.Property(t => t.VraagID).HasColumnName("VraagID");

            // Relationships
            this.HasOptional(t => t.vraag)
                .WithMany(t => t.AntwoordCasussen)
                .HasForeignKey(d => d.VolgendeVraag);
            this.HasRequired(t => t.vraag1)
                .WithMany(t => t.AntwoordCasussen1)
                .HasForeignKey(d => d.VraagID);

        }
    }
}
