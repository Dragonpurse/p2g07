using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblStellingspelMap : EntityTypeConfiguration<Stellingspel>
    {
        public tblStellingspelMap()
        {
            // Primary Key
            this.HasKey(t => t.StellingspelID);

            // Properties
            this.Property(t => t.Titel)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Omschrijving)
                .HasMaxLength(255);

            this.Property(t => t.Doelgroep)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Kernwoorden)
                .IsRequired()
                .IsFixedLength()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblStellingspel");
            this.Property(t => t.StellingspelID).HasColumnName("StellingspelID");
            this.Property(t => t.Titel).HasColumnName("Titel");
            this.Property(t => t.Omschrijving).HasColumnName("Omschrijving");
            this.Property(t => t.Doelgroep).HasColumnName("Doelgroep");
            this.Property(t => t.Kernwoorden).HasColumnName("Kernwoorden");
        }
    }
}
