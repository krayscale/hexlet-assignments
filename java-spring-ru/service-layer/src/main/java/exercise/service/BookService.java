package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public List<BookDTO> getAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public BookDTO getById(Long id) {
        var item = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        return mapper.map(item);
    }

    public BookDTO create(BookCreateDTO createDTO) {
        var result = mapper.map(createDTO);
        return mapper.map(repository.save(result));
    }

    public BookDTO update(Long id, BookUpdateDTO updateDTO) {
        var item = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("" + id));
        mapper.update(updateDTO, item);
        return mapper.map(repository.save(item));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
