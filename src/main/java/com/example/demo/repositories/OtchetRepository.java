package com.example.demo.repositories;


import com.example.demo.models.Otchet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OtchetRepository extends CrudRepository<Otchet,Long> {
    Optional<Otchet> findById(Long id);

}
