using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Web;

namespace Project2Groep7.Models
{
    public class DeelnemerRepository : IDeelnemerRepository
    {
        p2g7Context context = new p2g7Context();

        public IQueryable<Deelnemer> All
        {
            get { return context.Deelnemers; }
        }

        public IQueryable<Deelnemer> AllIncluding(params Expression<Func<Deelnemer, object>>[] includeProperties)
        {
            IQueryable<Deelnemer> query = context.Deelnemers;
            foreach (var includeProperty in includeProperties)
            {
                query = query.Include(includeProperty);
            }
            return query;
        }

        //public Deelnemer Find(int id)
        //{
        //    return context.Deelnemers.Find(id);
        //}

        public Deelnemer FindByEmail(string email)
        {
            return context.Deelnemers.SingleOrDefault(g => g.Email == email);
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

    public interface IDeelnemerRepository : IDisposable
    {
        IQueryable<Deelnemer> All { get; }
        IQueryable<Deelnemer> AllIncluding(params Expression<Func<Deelnemer, object>>[] includeProperties);
        Deelnemer FindByEmail(string email);

        void Save();


    }
}