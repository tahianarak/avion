package avion.controller;


import avion.model.ModeleAvion;
import avion.model.User;
import avion.service.UserService;
import mg.ituprom16.affloader.ModelView;
import mg.ituprom16.annotation.*;
import mg.ituprom16.annotation.authentification.ConfiguredAuth;
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
            User user=(new UserService()).verifyLogin(email,mdp);
            session.add("user",user);
            if(user.isStatus())
            {
                session.add("role","admin");
                session.add("connected",true);
            }
            else if(!user.isStatus())
            {
                session.add("role","Authentified");
                session.add("connected",true);
            }
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
