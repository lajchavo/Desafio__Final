package forohub.alura.api.consulta;

import forohub.alura.api.topico.Consulta;

import javax.swing.*;
import java.sql.Timestamp;

public record DatosListadoConsulta(Long id, String titulo, String mensaje, String fecha ,String id_Usuario, String nombre_curso) {

    public DatosListadoConsulta (Consulta consulta){
        this(consulta.getId(), consulta.getTitulo(),consulta.getMensaje(), String.valueOf(consulta.getFecha()),consulta.getId_Usuario(),consulta.getNombre_Curso());
    }

}
