package forohub.alura.api.controller;

import forohub.alura.api.consulta.DatosActualizarConsulta;
import forohub.alura.api.consulta.DatosListadoConsulta;
import forohub.alura.api.consulta.DatosRespuestaConsulta;
import forohub.alura.api.topico.ConsultaRepository;
import forohub.alura.api.topico.Consulta;
import forohub.alura.api.topico.DatosDeRegistroConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/foro")

public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaConsulta> registrarTopico(@RequestBody DatosDeRegistroConsulta datosDeRegistroConsulta,
                                          UriComponentsBuilder uriComponentsBuilder) {
        Consulta consulta = consultaRepository.save(new Consulta(datosDeRegistroConsulta));
        DatosRespuestaConsulta datosRespuestaConsulta = new DatosRespuestaConsulta(consulta.getId(), consulta.getId_Usuario(), consulta.getMensaje(), consulta.getStatus_respondido().toString(), consulta.getFecha().toString());
        URI url = uriComponentsBuilder.path("/foro/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaConsulta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoConsulta>> datosListadoConsultas(@PageableDefault(size = 10,sort = "fecha") Pageable paginacion){
        //return consultaRepository.findAll(paginacion).map(DatosListadoConsulta::new);
        return ResponseEntity.ok(consultaRepository.findByActivoTrue(paginacion).map(DatosListadoConsulta::new));

    }

    //@PutMapping
//    public ResponseEntity<?> actualizarConsulta(@RequestBody DatosActualizarConsulta datosActualizarConsulta) {
//        Consulta consulta = consultaRepository.findById(datosActualizarConsulta.id())
//                .orElseThrow(() -> new IllegalArgumentException("Consulta no encontrada"));
//
//        consulta.actualizarDatosConsulta(datosActualizarConsulta);
//        consultaRepository.save(consulta);
//
//        return ResponseEntity.ok().build();
//    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarConsulta (@RequestBody @Valid DatosActualizarConsulta datosActualizarConsulta) {
        Consulta consulta = consultaRepository.getReferenceById(datosActualizarConsulta.id());
        consulta.actualizarDatosConsulta(datosActualizarConsulta);
        return ResponseEntity.ok(new DatosRespuestaConsulta(consulta.getId(),consulta.getId_Usuario(),consulta.getMensaje(),consulta.getStatus_respondido().toString(),consulta.getFecha().toString()));
    }

    //@DeleteMapping("/{id}")
    // @Transactional
    //public void eliminarConsulta  (@PathVariable Long id ){
        //Consulta consulta = consultaRepository.getReferenceById(id);
        //consultaRepository.delete(consulta);

    //}

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarConsulta  (@PathVariable Long id ){
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.desactivarConsulta();
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/desactivarStatusRespondido/{id}")
    @Transactional
    public void desactivarStatusRespondido(@PathVariable Long id) {
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.desactivarStatusRespondido();
    }

    @GetMapping("/{id}")

    public ResponseEntity<DatosRespuestaConsulta> retornaDatosConsulta  (@PathVariable Long id ){
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.desactivarConsulta();
        var datosConsulta = new DatosRespuestaConsulta(consulta.getId(),
                consulta.getId_Usuario(),
                consulta.getMensaje(),
                consulta.getStatus_respondido().toString(),
                consulta.getFecha().toString());
        return ResponseEntity.ok(datosConsulta);

    }






}



