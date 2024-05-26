package midianet.crud.resource;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import midianet.crud.model.Produto;
import midianet.crud.service.ProdutoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Validated
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    private final ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final Produto produto, final HttpServletResponse response) {
        final var created = service.create(produto);
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri()
                .toString());
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable final String id, @Valid @RequestBody final Produto produto){
        service.update(id,produto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        service.delete(id);
    }

    @GetMapping
    public List<Produto> list() {
        return service.list();
    }

    @GetMapping(path = "/{id}")
    public Produto get(@PathVariable final String id) {
        return service.findById(id);
    }

}
