package ar.edu.iua.modelo;

public abstract class Objeto implements Cloneable {

    abstract public int hashCode();

    abstract public boolean equals(Object obj);

    abstract public String toString();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
