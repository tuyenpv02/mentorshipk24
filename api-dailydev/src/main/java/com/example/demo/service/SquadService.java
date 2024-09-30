package com.example.demo.service;

import com.example.demo.entity.Squad;
import com.example.demo.repository.SquadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadService {
    @Autowired
    private SquadRepository repository;

    public List<Squad> getAll() {
        return repository.findAll();
    }
    public Squad findById(Long id) {
        Optional<Squad> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }
    public Squad add(Squad Squad) {
        return repository.save(Squad);
    }

    public Squad update(Long id, Squad newSquad) {
        Optional<Squad> optional = repository.findById(id);
        return optional.map(o -> {
            o.setName(newSquad.getName());
            return repository.save(o);
        }).orElse(null);
    }

    public Squad deleteById(Long id) {
        Optional<Squad> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
