using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblVraagDooMap : EntityTypeConfiguration<VraagDoos>
    {
        public tblVraagDooMap()
        {
            // Primary Key
            this.HasKey(t => new { t.DoosID, t.Vraag });

            // Properties
            this.Property(t => t.DoosID)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            this.Property(t => t.Vraag)
                .IsRequired()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblVraagDoos");
            this.Property(t => t.DoosID).HasColumnName("DoosID");
            this.Property(t => t.Vraag).HasColumnName("Vraag");

            // Relationships
            this.HasRequired(t => t.doos)
                .WithMany(t => t.VraagDoos)
                .HasForeignKey(d => d.DoosID);

        }
    }
}
