package org.project.moroz.domain;

import javax.persistence.*;

@Entity
public class GiftRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gift gift;
    private String uuidReq;

    public Long getId() {
        return id;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public String getUuidReq() {
        return uuidReq;
    }

    public void setUuidReq(String uuidReq) {
        this.uuidReq = uuidReq;
    }
}
