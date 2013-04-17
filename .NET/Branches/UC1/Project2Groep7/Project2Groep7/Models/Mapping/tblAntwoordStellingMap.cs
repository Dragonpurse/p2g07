using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblAntwoordStellingMap : EntityTypeConfiguration<AntwoordStelling>
    {
        public tblAntwoordStellingMap()
        {
            // Primary Key
            this.HasKey(t => new { t.StellingID, t.Antwoord });

            // Properties
            this.Property(t => t.StellingID)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            this.Property(t => t.Antwoord)
                .IsRequired()
                .HasMaxLength(255);

            this.Property(t => t.Kleur)
                .IsRequired()
                .HasMaxLength(50);

            // Table & Column Mappings
            this.ToTable("tblAntwoordStelling");
            this.Property(t => t.StellingID).HasColumnName("StellingID");
            this.Property(t => t.Antwoord).HasColumnName("Antwoord");
            this.Property(t => t.Kleur).HasColumnName("Kleur");

            // Relationships
            this.HasRequired(t => t.stelling)
                .WithMany(t => t.AntwoordStellingen)
                .HasForeignKey(d => d.StellingID);

        }
    }
}