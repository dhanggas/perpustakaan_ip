
package com.dhanggas.entiry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="m_buku")
public class Buku {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(nullable = false,unique =true)
    private String isbn;
    
    @Column(nullable=false)
    private String judul;
    
    @JoinColumn(name="kode_penerbit", nullable=false)
    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
    private Penerbit penerbit;
    
    private String pengarang;
    
    private Integer jumlah;
    
    @Column(name="tanggal_terbit",nullable=true)
    private Date tanggalTerbit;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buku", fetch = FetchType.EAGER,orphanRemoval = true )
    private List<PinjamBuku> daftarTransaksi = new ArrayList<PinjamBuku>();

    public List<PinjamBuku> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public void setDaftarTransaksi(List<PinjamBuku> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Penerbit getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(Penerbit penerbit) {
        this.penerbit = penerbit;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

   

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    

    public Date getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(Date tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }
     
    
    
}
