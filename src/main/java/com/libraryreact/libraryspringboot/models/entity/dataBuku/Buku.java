package com.libraryreact.libraryspringboot.models.entity.dataBuku;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.libraryreact.libraryspringboot.models.entity.dataMaster.Genre;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Kategori;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Lokasi;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Penerbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
public class Buku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String pengarang;

    @Column(length = 4, nullable = false)
    private String tahunTerbit;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Double harga;

    @Column(nullable = false)
    private String deskripsi;

    @Column
    private String sampul;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "idKategori")
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name = "idPenerbit")
    private Penerbit penerbit;

    @ManyToOne
    @JoinColumn(name = "idLokasi")
    private Lokasi lokasi;

    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genre genre;
}
