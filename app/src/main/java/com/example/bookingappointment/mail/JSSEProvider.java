package com.example.bookingappointment.mail;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

public class JSSEProvider extends Provider {

    public JSSEProvider(){

        super("HarmonyJSSE" , 1.0 , "Harmony JSSE Provider");

    }

}
