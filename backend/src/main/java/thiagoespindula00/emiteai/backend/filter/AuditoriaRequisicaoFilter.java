package thiagoespindula00.emiteai.backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import thiagoespindula00.emiteai.backend.model.Requisicao;
import thiagoespindula00.emiteai.backend.repository.RequisicaoRepository;

import java.io.IOException;

@Component
public class AuditoriaRequisicaoFilter extends OncePerRequestFilter {
    private final RequisicaoRepository requisicaoRepository;

    public AuditoriaRequisicaoFilter(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);

        requisicaoRepository.save(new Requisicao(
                request.getMethod(),
                request.getRequestURI()
        ));
    }
}
