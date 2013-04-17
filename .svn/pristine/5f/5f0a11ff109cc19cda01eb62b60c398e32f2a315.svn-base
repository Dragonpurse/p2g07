using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using Project2Groep7.Models.Mapping;

namespace Project2Groep7.Models
{
    public partial class p2g7Context : DbContext
    {
        static p2g7Context()
        {
            Database.SetInitializer<p2g7Context>(null);
        }

        public p2g7Context()
            : base("Name=p2g7Context")
        {
        }

        public DbSet<AntwoordCasus> AntwoordCasus { get; set; }
        public DbSet<AntwoordStelling> AntwoordStelling { get; set; }
        public DbSet<Casus> Casussen { get; set; }
        public DbSet<Deelnemer> Deelnemers { get; set; }
        public DbSet<Document> Documenten { get; set; }
        public DbSet<Doos> Dozen { get; set; }
        public DbSet<Leertraject> Leertrajecten { get; set; }
        public DbSet<Medewerker> Medewerkers { get; set; }
        public DbSet<Stelling> Stellingen { get; set; }
        public DbSet<Stellingspel> Stellingspellen { get; set; }
        public DbSet<TrajectOnderdeel> TrajectOnderdelen { get; set; }
        public DbSet<Vraag> Vragen { get; set; }
        public DbSet<VraagDoos> VraagDoos { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new tblAntwoordCasuMap());
            modelBuilder.Configurations.Add(new tblAntwoordStellingMap());
            modelBuilder.Configurations.Add(new tblCasuMap());
            modelBuilder.Configurations.Add(new tblDeelnemerMap());
            modelBuilder.Configurations.Add(new tblDocumentMap());
            modelBuilder.Configurations.Add(new tblDooMap());
            modelBuilder.Configurations.Add(new tblLeertrajectMap());
            modelBuilder.Configurations.Add(new tblMedewerkerMap());
            modelBuilder.Configurations.Add(new tblStellingMap());
            modelBuilder.Configurations.Add(new tblStellingspelMap());
            modelBuilder.Configurations.Add(new tblTrajectOnderdelenMap());
            modelBuilder.Configurations.Add(new tblVraagMap());
            modelBuilder.Configurations.Add(new tblVraagDooMap());
        }

    }
}
