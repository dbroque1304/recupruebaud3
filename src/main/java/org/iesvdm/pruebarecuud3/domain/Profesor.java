package org.iesvdm.pruebarecuud3.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    private int id;
    @Pattern(regexp = "((([X-Z])|([LM])){1}([-]?)((\\d){7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]))", message = "{msg_nif_pattern}")
    private String nif;
    @NotBlank(message = "{msg_nombre_blank}")
    private String nombre;
    private String apellido1;
    private String apellido2;
    @Size(max = 25, message = "{msg_ciudad_max}")
    private String ciudad;
    private String direccion;
    private String telefono;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;
    private String tipo;
    private Departamento departamento;
}
