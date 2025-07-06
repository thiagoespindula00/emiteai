package thiagoespindula00.emiteai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thiagoespindula00.emiteai.backend.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByCpf(String cpf);
}
