package forohub.alura.api.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findByActivoTrue(Pageable pageable);
}
