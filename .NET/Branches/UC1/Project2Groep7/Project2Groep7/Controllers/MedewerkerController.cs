using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Project2Groep7.Models;

namespace Project2Groep7.Controllers
{   
    public class MedewerkerController : Controller
    {
        private readonly IMedewerkerRepository medewerkerRepository;

		// If you are using Dependency Injection, you can delete the following constructor
        public MedewerkerController() : this(new MedewerkerRepository())
        {
        }

        public MedewerkerController(IMedewerkerRepository medewerkerRepository)
        {
			this.medewerkerRepository = medewerkerRepository;
        }

        //
        // GET: /Medewerkers/

        public ViewResult Index()
        {
            return View(medewerkerRepository.AllIncluding(medewerker => medewerker.Leertrajecten));
        }

        //
        // GET: /Medewerkers/Details/5

        public ViewResult Details(int id)
        {
            return View(medewerkerRepository.Find(id));
        }

        //
        // GET: /Medewerkers/Create

        public ActionResult Create()
        {
            return View();
        } 

        //
        // POST: /Medewerkers/Create

        [HttpPost]
        public ActionResult Create(Medewerker medewerker)
        {
            if (ModelState.IsValid) {
                medewerkerRepository.InsertOrUpdate(medewerker);
                medewerkerRepository.Save();
                return RedirectToAction("Index");
            } else {
				return View();
			}
        }
        
        //
        // GET: /Medewerkers/Edit/5
 
        public ActionResult Edit(int id)
        {
             return View(medewerkerRepository.Find(id));
        }

        //
        // POST: /Medewerkers/Edit/5

        [HttpPost]
        public ActionResult Edit(Medewerker medewerker)
        {
            if (ModelState.IsValid) {
                medewerkerRepository.InsertOrUpdate(medewerker);
                medewerkerRepository.Save();
                return RedirectToAction("Index");
            } else {
				return View();
			}
        }

        //
        // GET: /Medewerkers/Delete/5
 
        public ActionResult Delete(int id)
        {
            return View(medewerkerRepository.Find(id));
        }

        //
        // POST: /Medewerkers/Delete/5

        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {
            medewerkerRepository.Delete(id);
            medewerkerRepository.Save();

            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing) {
                medewerkerRepository.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}

