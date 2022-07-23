package negocio.academico.plan;
import java.text.Normalizer;

public class Normalizador {

    public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD); //convertimos texto a su forma "canonical decomposition"
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); //expresion regular, de utf-8 de u+0300 hasta u+036f
        return texto;
    }
    
}
