package avion.controller;


import avion.model.ModeleAvion;
import avion.model.User;
import mg.ituprom16.affloader.ModelView;
import mg.ituprom16.annotation.*;
import mg.ituprom16.session.*;

@Controller
public class LoginController
{

    @URL(valeur = "/login")
    public  ModelView getLoginPage()
    {
        return  new ModelView("/login.jsp");
    }

    @URL(valeur = "/verifyLogin")
    public ModelView verifyLogin(@Match(param = "email") String email, @Match(param = "mdp") String mdp,MySession session)
    {
        try
        {
            ModelView mv=new ModelView("/accueil.jsp");
            User user=User.authenticate(null,email,mdp);
            session.add("user",user);
            return  mv;
        }
        catch (Exception e)
        {
            ModelView mv=new ModelView("/error.jsp");
            mv.addObject("error",e.getMessage());
            return  mv;
        }
    }


}
