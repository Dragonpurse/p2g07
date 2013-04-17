﻿using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Web;

namespace Project2Groep7.Models
{
    public class OnderdeelRepository : IOnderdeelRepository
    {
        p2g7Context context = new p2g7Context();

        public IQueryable<TrajectOnderdeel> All
        {
            get { return context.TrajectOnderdelen; }
        }

        public TrajectOnderdeel FindById(int onderdeelID, string type)
        {
            switch(type){
                case "Document":
                    //return this.All.Include(l => l.document).SingleOrDefault(o => o.OnderdeelID == onderdeelID);
                    return this.All.Include(l => l.document).First(o => o.OnderdeelID == onderdeelID);
                case "Casus":
                    return this.All.Include(l => l.casus).First(o => o.OnderdeelID == onderdeelID);
                case "Doos":
                    return this.All.Include(l => l.doos).First(o => o.OnderdeelID == onderdeelID);
                case "Stellingspel":
                    return this.All.Include(l => l.stellingspel).First(o => o.OnderdeelID == onderdeelID);
                default:
                    return null;
            }

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

    public interface IOnderdeelRepository : IDisposable
    {
        IQueryable<TrajectOnderdeel> All { get; }
        TrajectOnderdeel FindById(int onderdeelID, string type);
        void Save();
    }
}