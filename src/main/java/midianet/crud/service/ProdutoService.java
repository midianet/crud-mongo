package midianet.crud.service;

import lombok.RequiredArgsConstructor;
import midianet.crud.model.Produto;
import midianet.crud.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    @Transactional
    public Produto create(@NonNull final Produto produto) {
        return repository.save(produto);
    }

    @Transactional
    public void update(@NonNull final String id, @NonNull final Produto produto) {
        final var persistent = findById(id);
        BeanUtils.copyProperties(persistent, produto, "id");
        repository.save(persistent);
    }

    @Transactional
    public void delete(@NonNull final String id) {
        final var persistent = findById(id);
        repository.delete(persistent);
    }

    public List<Produto> list(){
        return repository.findAll();
    }

    public Produto findById(@NonNull final String id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Produto %s",id)));
    }
    
}
