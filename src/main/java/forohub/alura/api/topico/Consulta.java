package forohub.alura.api.topico;


import forohub.alura.api.consulta.DatosActualizarConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private String id_Usuario;

    private String mensaje;

    private String nombre_Curso;

    private String titulo;

    private Timestamp fecha;

    private Boolean activo;

    private Boolean status_respondido;

    public Consulta(DatosDeRegistroConsulta datosDeRegistroConsulta) {
        this.id_Usuario = datosDeRegistroConsulta.id_Usuario();
        this.mensaje = datosDeRegistroConsulta.mensaje();
        this.nombre_Curso = datosDeRegistroConsulta.nombre_Curso();
        this.titulo = datosDeRegistroConsulta.titulo();
        this.activo = true;
        this.status_respondido =true;
        // El campo 'fecha' se establecerá en el método 'prePersist'
    }

    @PrePersist
    protected void prePersist() {
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    public void actualizarDatosConsulta(DatosActualizarConsulta datosActualizarConsulta) {
        if (datosActualizarConsulta.mensaje()!= null){
            this.mensaje = datosActualizarConsulta.mensaje();
        }
        this.mensaje = datosActualizarConsulta.mensaje();
    }

    public void desactivarConsulta() {
        this.activo = false;
    }




    public void desactivarStatusRespondido() {
        this.status_respondido = false;
    }
}