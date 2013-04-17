using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblCasuMap : EntityTypeConfiguration<Casus>
    {
        public tblCasuMap()
        {
            // Primary Key
            this.HasKey(t => t.CasusID);

            // Properties
            this.Property(t => t.Titel)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Omschrijving)
                .HasMaxLength(255);

            this.Property(t => t.LocatieFilm)
                .IsFixedLength()
                .HasMaxLength(255);

            this.Property(t => t.Doelgroep)
                .IsRequired()
                .IsFixedLength()
                .HasMaxLength(50);

            this.Property(t => t.Situatieschets)
                .IsRequired()
                .IsFixedLength()
                .HasMaxLength(255);

            this.Property(t => t.Kernwoorden)
                .IsRequired()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblCasus");
            this.Property(t => t.CasusID).HasColumnName("CasusID");
            this.Property(t => t.Titel).HasColumnName("Titel");
            this.Property(t => t.Omschrijving).HasColumnName("Omschrijving");
            this.Property(t => t.LocatieFilm).HasColumnName("LocatieFilm");
            this.Property(t => t.Doelgroep).HasColumnName("Doelgroep");
            this.Property(t => t.Situatieschets).HasColumnName("Situatieschets");
            this.Property(t => t.EersteVraag).HasColumnName("EersteVraag");
            this.Property(t => t.Kernwoorden).HasColumnName("Kernwoorden");

            // Relationships
            this.HasRequired(t => t.vraag)
                .WithMany(t => t.Casussen)
                .HasForeignKey(d => d.EersteVraag);

        }
    }
}
