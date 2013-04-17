using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblDocumentMap : EntityTypeConfiguration<Document>
    {
        public tblDocumentMap()
        {
            // Primary Key
            this.HasKey(t => t.DocumentID);

            // Properties
            this.Property(t => t.Titel)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Omschrijving)
                .HasMaxLength(255);

            this.Property(t => t.Locatie)
                .IsRequired()
                .HasMaxLength(255);

            this.Property(t => t.Doelgroep)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Kernwoorden)
                .IsRequired()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblDocument");
            this.Property(t => t.DocumentID).HasColumnName("DocumentID");
            this.Property(t => t.Titel).HasColumnName("Titel");
            this.Property(t => t.Omschrijving).HasColumnName("Omschrijving");
            this.Property(t => t.Locatie).HasColumnName("Locatie");
            this.Property(t => t.Doelgroep).HasColumnName("Doelgroep");
            this.Property(t => t.Kernwoorden).HasColumnName("Kernwoorden");
        }
    }
}
