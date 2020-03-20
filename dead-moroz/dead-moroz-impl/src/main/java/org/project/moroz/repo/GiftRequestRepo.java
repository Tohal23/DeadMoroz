package org.project.moroz.repo;

import org.project.moroz.domain.Gift;
import org.project.moroz.domain.GiftRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRequestRepo extends JpaRepository<GiftRequest, Long> {

    GiftRequest findByGiftAndUuidReq(Gift gift, String uuidReq);

}
