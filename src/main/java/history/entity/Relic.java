package history.entity;

import history.History;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Relic extends History {
    private String place;
    private String province;

    private String certifacte;

    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCertifacte() {
        return certifacte;
    }

    public void setCertifacte(String certifacte) {
        this.certifacte = certifacte;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public List<Relic> loadDataJson() {
        return null;
    }
}