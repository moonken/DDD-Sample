package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.purchase.domain.InboundTO;
import com.example.autopartsmall.purchase.infrastructure.convertor.PurchasePOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "inbound_to")
public class InboundTOPO {
    @Id
    @GeneratedValue
    Long id;

    Long purchaseOrderId;

    @Enumerated(EnumType.STRING)
    InboundTO.Status status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    List<InboundTOLinePO> orderLines;

    InboundTO toDomain() {
        return PurchasePOConverter.INSTANCE.toDomain(this);
    }

    static InboundTOPO fromDomain(InboundTO inboundTO) {
        return PurchasePOConverter.INSTANCE.toPO(inboundTO);
    }
}
