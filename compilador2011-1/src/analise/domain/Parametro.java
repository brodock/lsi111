package analise.domain;

import lsi111.analise.Token;

/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public class Parametro extends Identificador {
    public MetodoPassagemParametro metodoPassagemParametro;
    public Metodo metodo;
    public boolean configurado;

    public Parametro(Token t){        
        super(t);
        configurado = false;
    }
}
