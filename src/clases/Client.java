package clases;

import java.util.ArrayList;

public class Client extends Persoana {
    private int ClientID;
    private RestrictiiFilme permisiuneFilme;
    private String dataNasterii;
    private ArrayList<Bilet> bilete_disponibile;

public Client(String nume, String prenume,String email, String telefon,int clientID, RestrictiiFilme permisiuneFilme, String dataNasterii) {
    super(nume, prenume, email, telefon);
    this.ClientID = clientID;
    this.permisiuneFilme = permisiuneFilme;
    this.dataNasterii = dataNasterii;
    this.bilete_disponibile = new ArrayList<Bilet>();

}
public int getClientID() {
    return ClientID;
}
public RestrictiiFilme getPermisiuneFilme() {
    return permisiuneFilme;
}
public String getDataNasterii() {
    return dataNasterii;
}

}
