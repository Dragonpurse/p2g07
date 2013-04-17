using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Web;

namespace Project2Groep7.Models
{ 
    public class MedewerkerRepository : IMedewerkerRepository
    {
        p2g7Context context = new p2g7Context();

        public IQueryable<Medewerker> All
        {
            get { return context.Medewerkers; }
        }

        public IQueryable<Medewerker> AllIncluding(params Expression<Func<Medewerker, object>>[] includeProperties)
        {
            IQueryable<Medewerker> query = context.Medewerkers;
            foreach (var includeProperty in includeProperties) {
                query = query.Include(includeProperty);
            }
            return query;
        }

        public Medewerker Find(int id)
        {
            return context.Medewerkers.Find(id);
        }

        public Medewerker FindByEmail(string email)
        {
            return context.Medewerkers.SingleOrDefault(g => g.Email == email);
        }

        public void InsertOrUpdate(Medewerker medewerker)
        {
            if (medewerker.MedewerkerID == default(int)) {
                // New entity
                context.Medewerkers.Add(medewerker);
            } else {
                // Existing entity
                context.Entry(medewerker).State = EntityState.Modified;
            }
        }

        public void Delete(int id)
        {
            var medewerker = context.Medewerkers.Find(id);
            context.Medewerkers.Remove(medewerker);
        }

        public void Save()
        {
            context.SaveChanges();
        }

        public void Dispose() 
        {
            context.Dispose();
        }
    }

    public interface IMedewerkerRepository : IDisposable
    {
        IQueryable<Medewerker> All { get; }
        IQueryable<Medewerker> AllIncluding(params Expression<Func<Medewerker, object>>[] includeProperties);
        Medewerker Find(int id);
        Medewerker FindByEmail(string email);

        void InsertOrUpdate(Medewerker medewerker);
        void Delete(int id);
        void Save();


    }
}