package clases;

public class Persoana {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;

    //constructori
    public Persoana(String nume, String prenume, String email, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
    }

    //getters
    public String getNume() {
        return nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefon() {
        return telefon;

    }


    //setters
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public void setEmail(String email) {
        this.email = email;

    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }


}
