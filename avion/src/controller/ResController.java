package avion.controller;

import avion.model.HeureAvantApresRes;
import avion.service.ReservationService;
import mg.ituprom16.affloader.ModelView;
import mg.ituprom16.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ResController
{
    @Get
    @URL (valeur = "/insertHeureRes")
    public ModelView insertRes()
    {
        return  new ModelView("/formHRes.jsp");
    }
    @Post
    @URL (valeur = "/insertHeureRes")
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
