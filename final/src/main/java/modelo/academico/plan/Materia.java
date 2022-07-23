package modelo.academico.plan;

import com.fasterxml.jackson.annotation.*;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
public class Materia extends ar.edu.iua.modelo.Objeto implements Cloneable {
    /*@JsonIgnore
    private AnioPlan anio;*/
    private Integer codigo;
    private String nombre;
    private Double cargaHoraria;

    public Materia(AnioPlan anio, Integer codigo, String nombre, Double cargaHoraria) {
        //this.anio = anio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cargaHoraria = cargaHoraria;
    }

    public Materia() {
    }

    /*public AnioPlan getAnio() {
        return anio;
    }*/

    /*void setAnio(AnioPlan anio) {
        this.anio = anio;
    }*/

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = (nombre != null && nombre.trim().length() > 0 ) ? nombre.trim() : null;
        this.nombre = nombre;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Materia other = (Materia) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    public String toString() {
        String cargaHoraria = this.cargaHoraria != null ? "(" + this.cargaHoraria + ")" : "";
        String nombre = this.nombre != null ? this.nombre : "";

        return (nombre + " " + cargaHoraria).trim();
    }

}
