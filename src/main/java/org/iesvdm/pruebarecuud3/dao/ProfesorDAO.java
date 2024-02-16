package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorDAO {
    public void create(Profesor profesor);

    public List<Profesor> getAll();

    public List<Departamento> getAllDepartamentos();

    public Optional<Departamento> getDepartamento(Integer id_departamento);
}
