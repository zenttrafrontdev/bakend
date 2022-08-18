package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FileBusinessTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "archivos_negocio")
public class FileBusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_archivo_id", nullable = false, updatable = false)
    private FileBusinessTypeEntity fileBusinessType;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "tipo_contenido")
    private String contentType;
    @Column(name = "tamano")
    private Long size;
    @Column(name = "url")
    private String url;
}
