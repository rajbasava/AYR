/*
    Copyright (c) 2012-2015 Yoga Vidya Pranic Healing Foundation of Karnataka.
    All rights reserved. Patents pending.
 
    Responsible: byummadisingh
*/
package com.yvphk.common;

public class Util
{
    public static boolean nullOrEmptyOrBlank (String toValidate)
    {
        if (toValidate == null || toValidate.equalsIgnoreCase("")) {
            return true;
        }

        return false;
    }
}