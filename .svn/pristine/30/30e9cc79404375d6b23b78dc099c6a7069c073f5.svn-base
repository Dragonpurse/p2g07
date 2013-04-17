using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblStellingMap : EntityTypeConfiguration<Stelling>
    {
        public tblStellingMap()
        {
            // Primary Key
            this.HasKey(t => t.StellingID);

            // Properties
            this.Property(t => t.stelling)
                .IsRequired()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblStelling");
            this.Property(t => t.StellingspelID).HasColumnName("StellingspelID");
            this.Property(t => t.stelling).HasColumnName("Stelling");
            this.Property(t => t.StellingID).HasColumnName("StellingID");

            // Relationships
            this.HasRequired(t => t.stellingspel)
                .WithMany(t => t.Stellingen)
                .HasForeignKey(d => d.StellingspelID);

        }
    }
}
