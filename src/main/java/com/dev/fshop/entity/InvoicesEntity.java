package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Invoices")
public class InvoicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceId")
    private int invoiceId;
    @Column(name = "paymentAmount")
    private float paymentAmount;
    @Column(name = "invoiceDate")
    private Date invoiceDate;
    @Column(name = "paymentDate")
    private Date paymentDate;
    @Column(name = "invoice_details")
    private  String invoice_details;
    @Column(name = "status")
    private boolean status;
}
