package analise.domain;

import lsi111.analise.SemanticError;
/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public enum Tipo {
    Inteiro,
    Real,
    Booleano,
    Caracter,
    Cadeia,
    Void,
    Literal,
    Vetor;

    public static boolean compativeis(Tipo tipoEsq, Tipo tipoDir) {
        switch(tipoEsq) {
            case Inteiro:
                return (tipoDir == Tipo.Inteiro);
            case Real: {
                return ((tipoDir == Tipo.Inteiro) || (tipoDir == Tipo.Real));
            }
            case Booleano: {
                return (tipoDir == Tipo.Booleano);
            }
            case Caracter: {
                return (tipoDir == Tipo.Caracter);
            }
            case Cadeia:
            case Literal: {
                return ((tipoDir == Tipo.Cadeia) || (tipoDir == Tipo.Caracter) || (tipoDir == Tipo.Literal));
            }
            case Vetor: {
                return (tipoDir == Tipo.Vetor);
            }
        }
        
        return false;
    }

    public static boolean compativelComMetodoPassagemParametro(Categoria categoria, Tipo tipo, MetodoPassagemParametro mpp){
        if (tipo == Tipo.Cadeia || tipo == Tipo.Vetor) return false;

        switch(mpp) {
            case Referencia : {
                return categoria == Categoria.Parametro || categoria == Categoria.Variavel;
            }
            case Valor : {                
                return true;
            }
        }
        return true;//só pra poder compilar lol
    }

    public static Tipo resultadoOperacao(Tipo tipo1, Tipo tipo2) throws SemanticError {
//        if (!Tipo.compativeis(tipo1, tipo2)) {
//            throw new SemanticError("Tipos dos operandos " + tipo1 + " e " + tipo2 + " incompatíveis.");
//        }
        switch(tipo1) {
            case Inteiro: {
                if (tipo2 == Tipo.Inteiro)
                    return Tipo.Inteiro;
                if (tipo2 == Tipo.Real)
                    return Tipo.Real;
                break;
            }
            case Real: {
                return Tipo.Real;
            }
            case Booleano: {
                return Tipo.Booleano;
            } //todo:revisar daqui pra baixo
            case Cadeia: {
                return Tipo.Cadeia;
            }
            case Literal: {
                return Tipo.Literal;
            }
            case Vetor: {
                return Tipo.Vetor;
            }
            case Caracter: {
                return Tipo.Caracter;
            }
        }
        return Tipo.Void;
    }

    public static boolean validoParaLeitura(Tipo tipo, Categoria categoria) {
        return (tipo != Tipo.Booleano) && ((categoria != Categoria.Constante) || (categoria != Categoria.Metodo));
    }
}
