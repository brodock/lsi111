package analise.domain;

import lsi111.analise.Token;

/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public class Identificador extends Token {
    public int nivel;
    public Tipo tipo;
    public Categoria categoria;
    public SubCategoria subCategoria;
    public String valor;

    public Identificador(int id, String lexeme, int position){
        super(id,lexeme,position);
    }

    public Identificador(Token token){
        super(token.getId(),token.getLexeme(),token.getPosition());
    }
}
