package forohub.alura.api.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarConsulta(
        @NotNull Long id,
        String mensaje) {

}
