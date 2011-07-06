package analise.domain;

/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public enum Operador {
    Soma,
    Subtracao,
    Multiplicacao,
    Divisao,
    Ou,
    E,
    Igual,
    Diferente,
    Menor,
    Maior,
    MenorIgual,
    MaiorIgual;
    
    public static boolean compativelComTipo(Operador oper, Tipo tipo1, Tipo tipo2){
        return (Operador.compativelComTipo(oper, tipo1) && Operador.compativelComTipo(oper, tipo2));
    }

    public static boolean compativelComTipo(Operador oper, Tipo tipo){
        switch(oper) {
            case Soma: {
                return ((tipo == Tipo.Inteiro) || (tipo == Tipo.Real));
            }
            case Subtracao: {
                return ((tipo == Tipo.Inteiro) || (tipo == Tipo.Real));
            }
            case Multiplicacao: {
                return ((tipo == Tipo.Inteiro) || (tipo == Tipo.Real));
            }
            case Divisao: {
                return ((tipo == Tipo.Inteiro) || (tipo == Tipo.Real));
            }
            case Ou: {
                return ((tipo == Tipo.Booleano));
            }
            case E: {
                return ((tipo == Tipo.Booleano));
            }
            case Igual: {
                return tipo != Tipo.Void;
            }
            case Diferente: {
                return tipo != Tipo.Void;
            }
            case Menor: {
                return tipo != Tipo.Void && tipo != Tipo.Booleano;
            }
            case Maior: {
                return tipo != Tipo.Void && tipo != Tipo.Booleano;
            }
            case MenorIgual: {
                return tipo != Tipo.Void && tipo != Tipo.Booleano;
            }
            case MaiorIgual: {
                return tipo != Tipo.Void && tipo != Tipo.Booleano;
            }
        }
        return false;
    }



}
