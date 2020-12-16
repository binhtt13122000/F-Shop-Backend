package com.dev.fshop.repositories;

import com.dev.fshop.entity.InvoicesEntity;
import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItemEntity;
import com.dev.fshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface InvoicesRepository extends JpaRepository<InvoicesEntity, Integer> {

    @Query(value = "SELECT SUM(total_days) FROM MyEntity", nativeQuery = true)
    public float viewRevenue();

    @Query("delete from InvoicesEntity u where u.invoiceId = :invoiceId ")
    public boolean deleteInvoices(int invoiceId);

    @Modifying(clearAutomatically = true)
    @Query("update InvoicesEntity u set u.paymentAmount = :paymentAmount, u.invoiceDate = :invoiceDate, u.paymentDate = :paymentDate, u.status = :status where u.invoiceId = :invoiceId ")
    public InvoicesEntity updateInvoices(Integer invoiceId,Float paymentAmount, Date invoiceDate, Date paymentDate, boolean status);

    @Transactional
    public InvoicesEntity insertInvoicesWithEntityManager(InvoicesEntity invoicesEntity);
}
