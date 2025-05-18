package com.abinesh.fieldguard.workers.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentType;

    @Lob
    @Column(name= "document_file", columnDefinition = "LONGBLOB")
    private byte[] documentFile;
    private String fileName;
    private String fileType;

    @ManyToOne
    @JoinColumn(name="worker_id")
    private Worker worker;

}
