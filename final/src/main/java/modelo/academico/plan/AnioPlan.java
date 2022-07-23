package modelo.academico.plan;

import java.util.ArrayList;
import java.util.List;

public class AnioPlan extends ar.edu.iua.modelo.Objeto {
    /*@JsonIgnore
    private Plan plan;*/
    private Integer numero;
    private String nombre;

    private Preceptor preceptor;

    public Preceptor getPreceptor() {
        return preceptor;
    }

    public void setPreceptor(Preceptor preceptor) {
        this.preceptor = preceptor;
    }

    private List<Materia> materias = new ArrayList<>();

    public AnioPlan(Integer numero, String nombre) {
        //this.plan = plan;
        this.numero = numero;
        this.nombre = nombre;
        this.preceptor = new Preceptor();
    }

    public AnioPlan(Integer numero, String nombre, List<Materia> materias, Preceptor preceptor) {
        this.numero = numero;
        this.nombre = nombre;
        this.materias = materias;
    }

    public AnioPlan() {
    }




    /*public Plan getPlan() {
        return plan;
    }*/

    /*void setPlan(Plan plan) {
        this.plan = plan;
    }*/

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = (nombre != null && nombre.trim().length() > 0) ? nombre.trim() : null;
        this.nombre = nombre;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    // ----

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnioPlan other = (AnioPlan) obj;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    public String toString() {
        return nombre != null ? nombre : (numero != null ? "Año " + numero : "Año sin identificación");
    }

    public Object clone() throws CloneNotSupportedException {
        AnioPlan cloned = (AnioPlan) super.clone();
        List<Materia> aux = new ArrayList<>();
        for (Materia materia : this.materias) {
            if (materia != null) {
                //materia.setAnio(this);
                aux.add((Materia) materia.clone());
            }
        }
        cloned.setMaterias(aux);
        return cloned;
    }

}
