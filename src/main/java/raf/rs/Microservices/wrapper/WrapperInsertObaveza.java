package raf.rs.Microservices.wrapper;

public class WrapperInsertObaveza {

    String student;
    String predmet;
    String obaveza;
    int poeni ;

    public WrapperInsertObaveza(String student, String predmet, String obaveza, int poeni) {
        this.student = student;
        this.predmet = predmet;
        this.obaveza = obaveza;
        this.poeni = poeni;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getObaveza() {
        return obaveza;
    }

    public void setObaveza(String obaveza) {
        this.obaveza = obaveza;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }
}
