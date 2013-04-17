using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace Project2Groep7.Models.Mapping
{
    public class tblMedewerkerMap : EntityTypeConfiguration<Medewerker>
    {
        public tblMedewerkerMap()
        {
            // Primary Key
            this.HasKey(t => t.MedewerkerID);

            // Properties
            this.Property(t => t.Email)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Wachtwoord)
                .IsRequired()
                .HasMaxLength(50);

            this.Property(t => t.Voornaam)
                .HasMaxLength(25);

            this.Property(t => t.Naam)
                .HasMaxLength(50);

            this.Property(t => t.TelefoonNr)
                .HasMaxLength(50);

            this.Property(t => t.Organisatie)
                .HasMaxLength(50);

            // Table & Column Mappings
            this.ToTable("tblMedewerker");
            this.Property(t => t.MedewerkerID).HasColumnName("MedewerkerID");
            this.Property(t => t.Email).HasColumnName("Email");
            this.Property(t => t.Wachtwoord).HasColumnName("Wachtwoord");
            this.Property(t => t.Voornaam).HasColumnName("Voornaam");
            this.Property(t => t.Naam).HasColumnName("Naam");
            this.Property(t => t.TelefoonNr).HasColumnName("TelefoonNr");
            this.Property(t => t.Organisatie).HasColumnName("Organisatie");
            this.Property(t => t.Intern).HasColumnName("Intern");
            
        }
    }
}
