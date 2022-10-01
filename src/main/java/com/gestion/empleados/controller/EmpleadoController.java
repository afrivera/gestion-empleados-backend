package com.gestion.empleados.controller;

import com.gestion.empleados.model.Empleado;
import com.gestion.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
