package com.decathlon.techday.dddworkshop.studio.persistency;

import com.decathlon.techday.dddworkshop.studio.domain.InstrumentDbEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringInstrumentRepository extends JpaRepository<InstrumentDbEntity, UUID> {

  List<InstrumentDbEntity> findAllByMusicianId(UUID musicianId);

}
