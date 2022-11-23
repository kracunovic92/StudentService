package raf.rs.Microservices.wrapper;

public class SortedStudents {

    private String ime;
    private String prezime;
    private Integer poeni;

    public SortedStudents(String ime, String prezime, Integer poeni) {
        this.ime = ime;
        this.prezime = prezime;
        this.poeni = poeni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getPoeni() {
        return poeni;
    }

    public void setPoeni(Integer poeni) {
        this.poeni = poeni;
    }
}
