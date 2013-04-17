using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblDeelnemerMap : EntityTypeConfiguration<Deelnemer>
    {
        public tblDeelnemerMap()
        {
            // Primary Key
            this.HasKey(t => t.Email);

            // Properties
            this.Property(t => t.Email)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Voornaam)
                .IsRequired()
                .HasMaxLength(25);

            this.Property(t => t.Naam)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Wachtwoord)
                .IsRequired()
                .IsFixedLength()
                .HasMaxLength(8);

            // Table & Column Mappings
            this.ToTable("tblDeelnemer");
            this.Property(t => t.Email).HasColumnName("Email");
            this.Property(t => t.Voornaam).HasColumnName("Voornaam");
            this.Property(t => t.Naam).HasColumnName("Naam");
            this.Property(t => t.Wachtwoord).HasColumnName("Wachtwoord");

            // Relationships
            this.HasMany(t => t.Leertrajecten)
                .WithMany(t => t.Deelnemers)
                .Map(m =>
                    {
                        m.ToTable("tblLeertrajectDeelnemer");
                        m.MapLeftKey("Email");
                        m.MapRightKey("LeertrajectCode");
                    });


        }
    }
}