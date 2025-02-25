package avion.controller;

import avion.model.*;
import avion.service.AvionService;
import avion.service.VilleDesservieService;
import avion.service.VolService;
import jakarta.servlet.http.HttpServletRequest;
import mg.ituprom16.affloader.ModelView;
import mg.ituprom16.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.Date;

import mg.ituprom16.annotation.authentification.ConfiguredAuth;
import mg.ituprom16.session.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static avion.service.VolService.insertVolPrix;

@Controller
public class VolController
{
    @URL(valeur = "/volsFiltres")
    @ConfiguredAuth(value = "Authentified")
    public ModelView volsFiltres(MySession session,@Match(param = "dateMin") String dateMin,@Match(param = "dateMax") String dateMax,@Match(param = "avion") int idModeleAvion,@Match(param = "villeDepart") int villeDepart) throws Exception {
        ModelView mv=getAllVols(session);
        List<Vol> vols=VolService.getAllVolfiltered(java.sql.Date.valueOf(dateMin),java.sql.Date.valueOf(dateMax),idModeleAvion,villeDepart);
        mv.addObject("vols",vols);
        return  mv;
    }
    @URL(valeur = "/vols")
    @ConfiguredAuth(value = "Authentified")
    public ModelView getAllVols(MySession session) throws Exception {

            ModelView mv=new ModelView("/listeVol.jsp");
            List<VilleDesservie> villeDesservies = VilleDesservieService.getAllVille();
            List<ModeleAvion> avions= AvionService.getAllModeleAvion();

            mv.addObject("vols",VolService.getAllVol());
            mv.addObject("villes",villeDesservies);
            mv.addObject("avions",avions);
            return  mv;
    }


    @URL(valeur = "/volFormUpdate")
    @ConfiguredAuth(value = "admin")
    public ModelView volFormUpdate(@Match(param = "idVol") int idVol,MySession session)
    {
        ModelView mv=new ModelView("/formulaireVol.jsp");
        try
        {
            if(((User)session.get("user")).isStatus()==false)
            {throw new Exception("oops quelque chose s'est mal passée");}
            List<VilleDesservie> villeDesservies = VilleDesservieService.getAllVille();
            List<Avion> avions= AvionService.getAllAvion();
            Vol vol=VolService.getVolByID(idVol);
            List<TypeSiege> typeSieges=AvionService.getAllTypeSiege();
            HashMap<Integer, Promotion> promotions=VolService.getPromByVol(idVol);
            HashMap<Integer, VolPrixTypeSiege> volPrixTypeSieges=VolService.getPrixForVol(idVol);
            mv.addObject("vol",vol);
            mv.addObject("villes",villeDesservies);
            mv.addObject("avions",avions);
            mv.addObject("sieges",typeSieges);
            mv.addObject("promotions",promotions);
            mv.addObject("prix",volPrixTypeSieges);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ModelView mvr=new ModelView("/error.jsp");
            mvr.addObject("error",e.getMessage());
            return  mvr;

        }
        return  mv;
    }
    @URL(valeur = "/volForm")
    @ConfiguredAuth(value = "admin")
    public ModelView volForm(MySession session)
    {
        ModelView mv=new ModelView("/formulaireVol.jsp");
        try
        {
            if(((User)session.get("user")).isStatus()==false)
            {throw new Exception("oops quelque chose s'est mal passée");}
            List<VilleDesservie> villeDesservies = VilleDesservieService.getAllVille();
            List<Avion> avions= AvionService.getAllAvion();
            List<TypeSiege> typeSieges=AvionService.getAllTypeSiege();
            mv.addObject("villes",villeDesservies);
            mv.addObject("avions",avions);
            mv.addObject("sieges",typeSieges);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ModelView mvr=new ModelView("/error.jsp");
            mvr.addObject("error",e.getMessage());
            return  mvr;

        }
        return  mv;

    }
    @Post
    @URL(valeur = "/insertVol")
    @ConfiguredAuth(value = "admin")
    public ModelView insertVol(HttpServletRequest request,MySession session,@Match(param = "vol") Vol vol) {
        try
        {
            if(((User)session.get("user")).isStatus()==false)
            {throw new Exception("oops quelque chose s'est mal passée");}

            List<TypeSiege> typeSieges=AvionService.getAllTypeSiege();
            VolService service=new VolService();
            if(request.getParameter("idVol")==null)
            {
                service.createVolWithPrixAndPromos(vol,typeSieges,request);
            }
            else
            {
                int idVol=Integer.valueOf(request.getParameter("idVol"));
                vol.setIdVol(idVol);
                service.updateVolWithPrixAndPromos(request,typeSieges,vol);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ModelView mvr=new ModelView("/error.jsp");
            mvr.addObject("error",e.getMessage());
            return  mvr;
        }
        return volForm(session);
    }
}
