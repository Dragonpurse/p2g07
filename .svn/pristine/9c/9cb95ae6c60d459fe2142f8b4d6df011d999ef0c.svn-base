using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Web;

namespace Project2Groep7.Models
{ 
    public class LeertrajectRepository : ILeertrajectRepository
    {
        p2g7Context context = new p2g7Context();


        public IQueryable<Leertraject> All
        {
            get { return context.Leertrajecten; }
        }

        public IQueryable<Leertraject> AllIncluding(params Expression<Func<Leertraject, object>>[] includeProperties)
        {
            IQueryable<Leertraject> query = context.Leertrajecten;
            foreach (var includeProperty in includeProperties) {
                query = query.Include(includeProperty);
            }
            return query;
        }

        public Leertraject FindById(string leertrajectCode)
        {
            return this.All.Include(l => l.TrajectOnderdelens).SingleOrDefault(t => t.LeertrajectCode == leertrajectCode);
        }


        public void InsertOrUpdate(Leertraject tblleertraject)
        {
            if (tblleertraject.LeertrajectCode == default(string)) {
                // New entity
                context.Leertrajecten.Add(tblleertraject);
            } else {
                // Existing entity
                context.Entry(tblleertraject).State = EntityState.Modified;
            }
        }

        public void Delete(string id)
        {
            var tblleertraject = context.Leertrajecten.Find(id);
            context.Leertrajecten.Remove(tblleertraject);
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

    public interface ILeertrajectRepository : IDisposable
    {
        IQueryable<Leertraject> All { get; }
        IQueryable<Leertraject> AllIncluding(params Expression<Func<Leertraject, object>>[] includeProperties);
        Leertraject FindById(string id);
        void InsertOrUpdate(Leertraject tblleertraject);
        void Delete(string id);
        void Save();
    }
}