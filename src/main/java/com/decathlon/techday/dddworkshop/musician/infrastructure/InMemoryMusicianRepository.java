package com.decathlon.techday.dddworkshop.musician.infrastructure;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMusicianRepository implements MusicianRepository {

  private List<Musician> musicians = List.of();

  @Override
  public void save(Musician musician) {
    musicians = Stream.concat(musicians.stream(), Stream.of(musician)).toList();
  }

  @Override
  public List<Musician> findAll() {
    return musicians;
  }

  @Override
  public Optional<Musician> findById(MusicianId id) {
    return musicians.stream().filter(m -> m.getId().equals(id)).findFirst();
  }
}
