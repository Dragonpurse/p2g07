using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblDooMap : EntityTypeConfiguration<Doos>
    {
        public tblDooMap()
        {
            // Primary Key
            this.HasKey(t => t.DoosID);

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
                .HasMaxLength(255);

            this.Property(t => t.Thema)
                .IsRequired()
                .HasMaxLength(50);

            // Table & Column Mappings
            this.ToTable("tblDoos");
            this.Property(t => t.DoosID).HasColumnName("DoosID");
            this.Property(t => t.Titel).HasColumnName("Titel");
            this.Property(t => t.Omschrijving).HasColumnName("Omschrijving");
            this.Property(t => t.Doelgroep).HasColumnName("Doelgroep");
            this.Property(t => t.Kernwoorden).HasColumnName("Kernwoorden");
            this.Property(t => t.Thema).HasColumnName("Thema");
        }
    }
}