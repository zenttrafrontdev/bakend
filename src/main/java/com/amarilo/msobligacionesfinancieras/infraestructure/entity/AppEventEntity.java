package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "registro_eventos")
public class AppEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo_evento")
    private String eventType;
    @Column(name = "fecha_evento")
    private LocalDateTime eventDate;
    @Column(name = "id_registro")
    private String recordId;
    @Column(name = "proceso")
    private String process;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "log")
    private String log;
}
