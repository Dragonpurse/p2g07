using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using WebMatrix.WebData;
using Project2Groep7.Models;

namespace Project2Groep7.Models
{
    public class MyWebSecurity
    {
        public IMedewerkerRepository mRepos { get; set; }
        public IDeelnemerRepository dRepos { get; set; }

        public MyWebSecurity(IMedewerkerRepository mRepos, IDeelnemerRepository dRepos)
        {
            this.mRepos = mRepos;
            this.dRepos = dRepos;
        }

        public bool IsValidAndMedewerker(string email, string wachtwoord)
        {
            Medewerker medewerker = mRepos.FindByEmail(email);
            if (medewerker == null)
            {
                return false;
            }
            if (medewerker.Wachtwoord == wachtwoord && medewerker.Intern == true)
            {
                return true;
            }
            return false;
        }

        public bool IsValidAndDeelnemer(string email, string wachtwoord)
        {
            Deelnemer deelnemer = dRepos.FindByEmail(email);
            if (deelnemer == null)
            {
                return false;
            }
            if (deelnemer.Wachtwoord == wachtwoord)
            {
                return true;
            }
            return false;
        }

        public int GetUserId(string email)
        {
            Medewerker medewerker = mRepos.FindByEmail(email);
            return medewerker.MedewerkerID;
        }

        public bool ChangePassword(string name, string oldPassword, string newPassword)
        {
            throw new NotImplementedException();
        }

        public void CreateAccount(string name, string newPassword)
        {
            throw new NotImplementedException();
        }
    }
}