package thiagoespindula00.emiteai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thiagoespindula00.emiteai.backend.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
