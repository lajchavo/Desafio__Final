package forohub.alura.api.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

public record DatosDeRegistroConsulta(
        @NotBlank
        @Pattern(regexp ="\\d{4,6}")
        String id_Usuario,
        String mensaje,
        @NotBlank
        String nombre_Curso,
        @NotBlank
        String titulo

) {
}
