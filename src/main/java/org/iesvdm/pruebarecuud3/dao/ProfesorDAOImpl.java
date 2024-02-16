package org.iesvdm.pruebarecuud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProfesorDAOImpl implements ProfesorDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Profesor profesor){
        jdbcTemplate.update("INSERT INTO persona (id, nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, tipo)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", profesor.getId(), profesor.getNif(), profesor.getNombre(), profesor.getApellido1(),
                profesor.getApellido2(), profesor.getCiudad(), profesor.getDireccion(), profesor.getTelefono(), profesor.getFecha_nacimiento(),
                profesor.getTipo());
        jdbcTemplate.update("INSERT INTO profesor (id_profesor, id_departamento) VALUES (?, ?)", profesor.getId(), profesor.getDepartamento().getId());
        log.info("Insertados {} registros", 1);
    }

    @Override
    public List<Profesor> getAll(){
        List<Profesor> profesores = jdbcTemplate.query("" +
                "SELECT * from persona JOIN profesor on persona.id = profesor.id_profesor JOIN departamento on profesor.id_departamento = departamento.id where tipo = 'profesor' || tipo = 'catedratico'",
                (rs, rowNum) -> new Profesor(rs.getInt("id"), rs.getNString("nif"),
                        rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
                        rs.getString("ciudad"), rs.getString("direccion"), rs.getString("telefono"),
                        rs.getDate("fecha_nacimiento"), rs.getString("tipo"), new Departamento(rs.getInt("id_departamento"), rs.getString("departamento.nombre"))
        ));
        log.info("Devueltos {} registros.", profesores.size());

        return profesores;
    }

    @Override
    public List<Departamento> getAllDepartamentos(){
        List<Departamento> departamentos = jdbcTemplate.query("" +
                        "SELECT * from departamento",
                (rs, rowNum) -> new Departamento(rs.getInt("id"), rs.getNString("nombre")
        ));
        log.info("Devueltos {} registros.", departamentos.size());

        return departamentos;
    }

    @Override
    public Optional<Departamento> getDepartamento(Integer id_departamento){
        Departamento dep = jdbcTemplate.queryForObject(
                "SELECT * FROM departamento WHERE id = ?",
                (rs, rowNum) -> new Departamento(rs.getInt("id"), rs.getString("nombre")), id_departamento);
        if (dep != null) {
            return Optional.of(dep);
        } else {
            log.info("Departamento no encontrado");
            return Optional.empty();
        }
    }

}
