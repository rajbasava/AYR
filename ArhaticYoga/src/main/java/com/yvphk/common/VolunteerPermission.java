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
    Vol("voln","Volunteer");

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
            allVolunteerPermissions.put(VolunteerPermission.RegVol.getKey(),
                    VolunteerPermission.RegVol.getName());
            allVolunteerPermissions.put(VolunteerPermission.Vol.getKey(),
                    VolunteerPermission.Vol.getName());
        }

        return allVolunteerPermissions;
    }

    public static String getName (String key)
    {
        return allVolunteerPermissions.get(key);
    }
    
}
