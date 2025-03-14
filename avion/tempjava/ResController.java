package avion.controller;

import avion.model.*;
import avion.service.ReservationService;
import avion.service.VolService;
import jakarta.servlet.http.Part;
import mg.ituprom16.affloader.ModelView;
import mg.ituprom16.annotation.*;
import mg.ituprom16.annotation.authentification.ConfiguredAuth;
import mg.ituprom16.session.MySession;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ResController
{
    @RestApi
    @URL(valeur = "/reservationsRest")
    @ConfiguredAuth(value = "Authentified")
    public List<Reservation> getAllReservationsRest(MySession session)throws Exception
    {
        return ReservationService.getAllByIDUser(((User)session.get("user")).getIdUser());
    }
    @URL(valeur = "/reservations")
    @ConfiguredAuth(value = "Authentified")
    public ModelView getAllReservations(MySession session)throws Exception
    {
        List<Reservation> reservations=ReservationService.getAllByIDUser(((User)session.get("user")).getIdUser());
        ModelView mv=new ModelView("/listeReservation.jsp");
        mv.addObject("reservations",reservations);
        return mv;
    }
    @URL(valeur = "/annulerReservation")
    @ConfiguredAuth(value = "Authentified")
    public ModelView annulerReservation(@Match(param = "idReservation") int idReservation,MySession session)throws Exception
    {
        try {
            ReservationService.annulerReservation(idReservation);
            return getAllReservations(session);
        }
        catch (Exception e)
        {
            ModelView mv=new ModelView("/error.jsp");
            mv.addObject("error",e.getMessage());
            return mv;
        }
    }
    @URL(valeur = "/selectionSiege")
    @ConfiguredAuth(value = "Authentified")
    public ModelView selectionSiegeReservation(@Match(param="idVol") int idVol)throws  Exception
    {
        HashMap<Integer, VolPrixTypeSiege> volPrixTypeSiegeHashMap= VolService.getPrixForVol(idVol);
        Vol vol=VolService.getVolByID(idVol);
        ModelView mv=new ModelView("/selectionSiege.jsp");
        mv.addObject("prix",volPrixTypeSiegeHashMap);
        mv.addObject("vol",vol);
        return mv;
    }
    @Post
    @URL(valeur = "/insertReservation")
    @ConfiguredAuth(value = "Authentified")
    public ModelView insertReservation(@Match(param = "res") Reservation reservation, MySession session,@Match(param="file") Part file)throws Exception
    {
        try {
            java.util.Date dateDepart = new java.util.Date();
            Timestamp date = new Timestamp(dateDepart.getTime());
            int idUser = ((User) session.get("user")).getIdUser();

            reservation.setIdUser(idUser);
            reservation.setDateReservation(date);
            reservation.setPhotoPassport(file);

            ReservationService.insertReservation(reservation);
            return new ModelView("/accueil.jsp");
        }
        catch (Exception e){
            e.printStackTrace();
            ModelView mv=new ModelView("/error.jsp");
            mv.addObject("error",e.getMessage());
            return  mv;
        }

    }

    @Get
    @URL (valeur = "/insertHeureRes")
    @ConfiguredAuth(value = "admin")
    public ModelView insertRes()
    {
        return  new ModelView("/formHRes.jsp");
    }
    @Post
    @URL (valeur = "/insertHeureRes")
    @ConfiguredAuth(value = "admin")
    public ModelView insertRes(@Match(param = "heureAvRes")String heureAvRes,@Match(param = "heureApRes")String heureApRes)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


            Date date1 = sdf.parse(heureAvRes);
            Time time1 = new Time(date1.getTime());

            Date date2 = sdf.parse(heureApRes);
            Time time2 = new Time(date2.getTime());

            HeureAvantApresRes res=new HeureAvantApresRes(time1,time2);

            ReservationService.insertHeureRes(res);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  insertRes();
    }
}
