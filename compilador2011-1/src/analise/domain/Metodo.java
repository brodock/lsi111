package analise.domain;

import lsi111.analise.Token;
import java.util.*;
/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public class Metodo extends Identificador {
    public List<Parametro> parametros;
    //public MetodoPassagemParametro metodoPassagemParametro;
    public boolean temRetorno;
    public boolean temRetornoDeclarado;
    public boolean temLocais;

    public Metodo(Token t){
        super(t);
        parametros = new ArrayList<Parametro>();
    }

}
