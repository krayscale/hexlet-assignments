package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;
    public List<AuthorDTO> getAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public AuthorDTO getById(Long id) {
        var item = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        return mapper.map(item);
    }

    public AuthorDTO create(AuthorCreateDTO createDTO) {
        var result = mapper.map(createDTO);
        return mapper.map(repository.save(result));
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO updateDTO) {
        var item = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        mapper.update(updateDTO, item);
        return mapper.map(repository.save(item));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
