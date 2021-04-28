package com.libraryreact.libraryspringboot.models.entity.dataBuku;

// import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.libraryreact.libraryspringboot.models.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kode_buku")
public class KodeBuku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String kodeBuku;

    @Basic
    private Timestamp createdAt;

    @Column
    private Boolean isAvailable;

    @Column
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "idDonatur", nullable = true)
    private Users donatur;

    @ManyToOne
    @JoinColumn(name = "idBuku")
    private Buku buku;
}
