package main;

import connectivite.ProtocoleDeCommunication;
import model.ListeDesUsagers;

public class ClavardageManager {
    private ProtocoleDeCommunication protocoleDeCommunication = new ProtocoleDeCommunication(this);
    private final ListeDesUsagers listeDesUsagers = new ListeDesUsagers(this);




    public ListeDesUsagers accesALaListeDesUsagers(){
        return listeDesUsagers;
    }






}


