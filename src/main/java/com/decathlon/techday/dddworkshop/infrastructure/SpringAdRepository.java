package com.decathlon.techday.dddworkshop.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringAdRepository extends JpaRepository<AdDBEntity, UUID> {

}
