/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.common;

import java.util.Map;
import java.util.LinkedHashMap;

public enum VolunteerPermission
{
    Admin("admin","Administrator"),
    RegVol("regtvoln","Registration Volunteer"),
    SptRegVol("sptregtvoln","Spot Registration Volunteer"),
    InfVol("infvoln","Info Volunteer");

    private String key;
    private String name;

    private static Map<String,String> allVolunteerPermissions;

    private VolunteerPermission (String key, String name)
    {
        this.key = key;
        this.name = name;
    }

    public String getKey ()
    {
        return key;
    }

    public String getName ()
    {
        return name;
    }

    public static Map<String,String> allVolunteerPermissions ()
    {
        if (allVolunteerPermissions == null) {
            allVolunteerPermissions = new LinkedHashMap<String,String>();

            allVolunteerPermissions.put(VolunteerPermission.Admin.getKey(),
                    VolunteerPermission.Admin.getName());
            allVolunteerPermissions.put(VolunteerPermission.SptRegVol.getKey(),
                    VolunteerPermission.SptRegVol.getName());
            allVolunteerPermissions.put(VolunteerPermission.RegVol.getKey(),
                    VolunteerPermission.RegVol.getName());
            allVolunteerPermissions.put(VolunteerPermission.InfVol.getKey(),
                    VolunteerPermission.InfVol.getName());
        }

        return allVolunteerPermissions;
    }

    public static String getName (String key)
    {
        return allVolunteerPermissions().get(key);
    }
    
}
