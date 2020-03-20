package org.project.moroz.repo;

import org.project.moroz.domain.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepo extends JpaRepository<Gift, Long> {

    Gift findGiftByName(String name);

}
