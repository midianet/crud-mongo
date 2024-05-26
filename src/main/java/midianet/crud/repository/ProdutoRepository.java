package midianet.crud.repository;

import midianet.crud.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

}
