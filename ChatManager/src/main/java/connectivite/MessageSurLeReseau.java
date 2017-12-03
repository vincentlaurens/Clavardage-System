package connectivite;

public class MessageSurLeReseau {

    public Entete entete;
    public Object contenu;



    public MessageSurLeReseau(Entete lEntete, Object leContenu){
        this.entete = lEntete;
        this.contenu = leContenu;
    }

    public Entete enteteDuMessage(){

        return this.entete;
    }

    public Object getContenu() {

        return this.contenu;
    }

    @Override
    public String toString() {

        return entete+"$"+contenu;
    }

    public byte[] getBytes(){
        String leMessageSurLeReseauAsString = this.toString();
        byte[] leTableauDOctet = leMessageSurLeReseauAsString.getBytes();
        return leTableauDOctet;

    }
}
