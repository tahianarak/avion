package com.gestion.ticketing.service;

import com.gestion.ticketing.model.AgeEnfantMaxRemise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class EnfantService
{
    @Autowired
    DataSource dataSource;

    public void insertAgeRemise(AgeEnfantMaxRemise ageEnfantMaxRemise)throws  Exception
    {
        try(Connection connection=dataSource.getConnection())
        {
            AgeEnfantMaxRemise.insert(ageEnfantMaxRemise,connection);
        }
    }
}
