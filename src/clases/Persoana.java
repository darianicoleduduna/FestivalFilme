package clases;

public class Persoana {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;

    public Persoana() {
        this.nume = "";
        this.prenume = "";
        this.email = "";
        this.telefon = "";
    }

    public Persoana(String nume, String prenume, String email, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getTelefon() {
        return telefon;

    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void afisare() {
        System.out.println(nume + " " + prenume + " " + email + " " + telefon);
    }

}
