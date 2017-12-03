package com.dhanggas.entiry;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "m_anggota")
public class Anggota {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(nullable = false)
    private String nama;

    @Column(name = "univ", nullable = false)
    private String universitas;

    @Column(name = "jur", nullable = false)
    private String jurusan;

    @Column(nullable = false)
    private String memberSejak;
    
    @Column(nullable = false)
    private String masaAktif;
    
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "anggota",orphanRemoval = true,fetch =FetchType.EAGER)
    private List<PinjamBuku> daftarPinjam =new ArrayList<>();

    public List<PinjamBuku> getDaftarPinjam() {
        return daftarPinjam;
    }

    public void setDaftarPinjam(List<PinjamBuku> daftarPinjam) {
        this.daftarPinjam = daftarPinjam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUniversitas() {
        return universitas;
    }

    public void setUniversitas(String universitas) {
        this.universitas = universitas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getMemberSejak() {
        return memberSejak;
    }

    public void setMemberSejak(String memberSejak) {
        this.memberSejak = memberSejak;
    }

    public String getMasaAktif() {
        return masaAktif;
    }

    public void setMasaAktif(String masaAktif) {
        this.masaAktif = masaAktif;
    }

    
}
