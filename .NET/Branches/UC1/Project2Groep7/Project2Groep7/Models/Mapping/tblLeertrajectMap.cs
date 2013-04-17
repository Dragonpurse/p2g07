using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblLeertrajectMap : EntityTypeConfiguration<Leertraject>
    {
        public tblLeertrajectMap()
        {
            // Primary Key
            this.HasKey(t => t.LeertrajectCode);

            // Properties
            this.Property(t => t.LeertrajectCode)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Titel)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Omschrijving)
                .HasMaxLength(255);

            this.Property(t => t.Doelgroep)
                .IsRequired()
                .HasMaxLength(50);

            // Table & Column Mappings
            this.ToTable("tblLeertraject");
            this.Property(t => t.LeertrajectCode).HasColumnName("LeertrajectCode");
            this.Property(t => t.Titel).HasColumnName("Titel");
            this.Property(t => t.Omschrijving).HasColumnName("Omschrijving");
            this.Property(t => t.StartDatum).HasColumnName("StartDatum");
            this.Property(t => t.Doelgroep).HasColumnName("Doelgroep");
            this.Property(t => t.BeschikbaarheidVan).HasColumnName("BeschikbaarheidVan");
            this.Property(t => t.BeschikbaarheidTot).HasColumnName("BeschikbaarheidTot");
            this.Property(t => t.Actief).HasColumnName("Actief");
            this.Property(t => t.Gearchiveerd).HasColumnName("Gearchiveerd");

            // Relationships
            this.HasMany(t => t.Medewerkers)
                .WithMany(t => t.Leertrajecten)
                .Map(m =>
                    {
                        m.ToTable("tblLeertrajectMedewerker");
                        m.MapLeftKey("LeertrajectCode");
                        m.MapRightKey("MedewerkerID");
                    });


        }
    }
}
