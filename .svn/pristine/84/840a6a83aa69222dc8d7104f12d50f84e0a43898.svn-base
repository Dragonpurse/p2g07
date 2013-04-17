using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Project2Groep7.Models;
using MvcContrib.Pagination;


namespace Project2Groep7.Controllers
{
    [Authorize]
    public class LeertrajectController : Controller
    {
		private readonly ILeertrajectRepository leertrajectRepository;
        private readonly IMedewerkerRepository medewerkerRepository;
        private readonly IDeelnemerRepository deelnemerRepository;
        public IEnumerable<Leertraject> leertrajecten { get; set; }

        public LeertrajectController(ILeertrajectRepository leertrajectRepository, IMedewerkerRepository medewerkerRepository, IDeelnemerRepository deelnemerRepository)
        {
            this.leertrajectRepository = leertrajectRepository;
            this.medewerkerRepository = medewerkerRepository;
            this.deelnemerRepository = deelnemerRepository;
        }

        //
        // GET: /tblLeertrajects/

        public ViewResult Index(int? page)
        {
            var pagedLeertrajecten = GetLeertrajecten().AsPagination(page ?? 1, 10);
            return View(pagedLeertrajecten);
        }

        public ActionResult ViewLeertraject(string leertrajectCode)
        {
            Leertraject traject = leertrajectRepository.FindById(leertrajectCode);
            return View(traject);
        }

        public IEnumerable<Leertraject> GetLeertrajecten()
        {
            IEnumerable<Leertraject> leertrajecten = null;
            if(User.IsInRole("Medewerker")){
                Medewerker medewerker = medewerkerRepository.FindByEmail(User.Identity.Name);
                leertrajecten = medewerker.Leertrajecten.OrderBy(l => l.StartDatum);
            }
            if (User.IsInRole("Deelnemer"))
            {
                Deelnemer deelnemer = deelnemerRepository.FindByEmail(User.Identity.Name);
                //leertrajecten = deelnemer.Leertrajecten.OrderBy(l => l.LeertrajectCode);
                leertrajecten = deelnemer.Leertrajecten.Where(l => l.BeschikbaarheidVan <= DateTime.Today && l.BeschikbaarheidTot >= DateTime.Today).OrderBy(l => l.LeertrajectCode);
            }

            return leertrajecten;
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing) {
                leertrajectRepository.Dispose();
            }
            base.Dispose(disposing);
        }


    }
}

