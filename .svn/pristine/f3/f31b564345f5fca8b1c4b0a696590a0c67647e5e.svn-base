using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblTrajectOnderdelenMap : EntityTypeConfiguration<TrajectOnderdeel>
    {
        public tblTrajectOnderdelenMap()
        {
            // Primary Key
            this.HasKey(t => new { t.LeertrajectCode, t.OnderdeelID, t.Type });

            // Properties
            this.Property(t => t.LeertrajectCode)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.OnderdeelID)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            this.Property(t => t.Type)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.DisplayNaam)
                .IsRequired()
                .HasMaxLength(255);

            this.Property(t => t.DisplayOmschrijving)
                .IsRequired()
                .HasMaxLength(255);

            // Table & Column Mappings
            this.ToTable("tblTrajectOnderdelen");
            this.Property(t => t.LeertrajectCode).HasColumnName("LeertrajectCode");
            this.Property(t => t.OnderdeelID).HasColumnName("OnderdeelID");
            this.Property(t => t.Type).HasColumnName("Type");
            this.Property(t => t.DisplayNaam).HasColumnName("DisplayNaam");
            this.Property(t => t.DisplayOmschrijving).HasColumnName("DisplayOmschrijving");
            this.Property(t => t.BeschikbaarVan).HasColumnName("BeschikbaarVan");
            this.Property(t => t.BeschikbaarTot).HasColumnName("BeschikbaarTot");

            // Relationships
            this.HasRequired(t => t.casus)
                .WithMany(t => t.TrajectOnderdelen)
                .HasForeignKey(d => d.OnderdeelID);
            this.HasRequired(t => t.document)
                .WithMany(t => t.TrajectOnderdelen)
                .HasForeignKey(d => d.OnderdeelID);
            this.HasRequired(t => t.doos)
                .WithMany(t => t.TrajectOnderdelen)
                .HasForeignKey(d => d.OnderdeelID);
            this.HasRequired(t => t.leertraject)
                .WithMany(t => t.TrajectOnderdelens)
                .HasForeignKey(d => d.LeertrajectCode);
            this.HasRequired(t => t.stellingspel)
                .WithMany(t => t.TrajectOnderdelen)
                .HasForeignKey(d => d.OnderdeelID);

        }
    }
}