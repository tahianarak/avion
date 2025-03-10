package avion.service;

import avion.model.*;

import java.sql.Connection;
import java.util.List;

public class AvionService
{
    public static  List<TypeSiege> getAllTypeSiege()throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return TypeSiege.getAll(connection);
        }
    }
    public static  List<ModeleAvion> getAllModeleAvion()throws Exception
    {
        try(Connection connection= DatabaseConnection.getConnection())
        {
               return ModeleAvion.getAll(connection);
        }
    }
      public static  List<Avion> getAllAvion()throws Exception
      {
          try(Connection connection= DatabaseConnection.getConnection())
          {
              return Avion.getAll(connection);
          }
      }

      public static  List<ModeleSiege> getAllByIdAvion(int idAvion)throws Exception
      {
          List<ModeleSiege> modeleSieges=null;
          try(Connection connection=DatabaseConnection.getConnection())
          {
              Avion avion=Avion.getByIdAvion(connection,idAvion);
              modeleSieges=ModeleSiege.getByModele(connection,avion.getIdModeleAvion());
          }
          return  modeleSieges;
      }
}
