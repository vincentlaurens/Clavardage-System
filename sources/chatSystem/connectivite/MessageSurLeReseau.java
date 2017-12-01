package connectivite;

public class MessageSurLeReseau {

    public Entete entete;
    public Object contenu;



    public MessageSurLeReseau(Entete lEntete, Object leContenu){
        this.entete = lEntete;
        this.contenu = leContenu;
    }


    @Override
    public String toString() {
        return entete+" "+contenu;
    }
}
