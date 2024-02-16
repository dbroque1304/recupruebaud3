package org.iesvdm.pruebarecuud3.service;

import org.iesvdm.pruebarecuud3.dao.ProfesorDAO;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorDAO profesorDAO;

    public List<Profesor> listAll(){
        return profesorDAO.getAll();
    }
    public void newProfesor(Profesor profesor){
        profesorDAO.create(profesor);
    }
    public List<Departamento> getDepartamentos() {
        return profesorDAO.getAllDepartamentos();
    }
    public Optional<Departamento> getDepartamento(Integer id_profesor) {
        return profesorDAO.getDepartamento(id_profesor);
    }
}
