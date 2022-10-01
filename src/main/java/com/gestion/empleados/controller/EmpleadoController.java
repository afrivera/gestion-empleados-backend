package com.gestion.empleados.controller;

import com.gestion.empleados.exceptions.ResourceNotFoundException;
import com.gestion.empleados.model.Empleado;
import com.gestion.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository repo;

    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosEmpleados(){
        return repo.findAll();
    }

    // este metodo sirve para guardar un empleado
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
        return repo.save(empleado);
    }

    // este metodo busca un empleado por Id
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = repo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Existe el empleado con el id: " + id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleado){
        Empleado empleado = repo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Existe el Empleado con el id: " + id ));
        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado = repo.save(empleado);

        return ResponseEntity.ok(empleadoActualizado);
    }
}
