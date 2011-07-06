package lsi111;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import lsi111.analise.LexicalError;
import lsi111.analise.Lexico;
import lsi111.analise.SemanticError;
import lsi111.analise.Semantico;
import lsi111.analise.Sintatico;
import lsi111.analise.SyntaticError;
import lsi111.analise.Token;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lsi111.utils.LSIFilter;
import lsi111.view.View;

/**
 *
 * @author Gabriel Gonçalves Nunes Mazetto
 * @author Luis Artur Ribeiro de Lima
 */
public class Controller {

    private View tela;
    public Controller ()
    {
        this.tela = new View(this);
        tela.setVisible(true);
    }

    public void lexicalAnalisys(String input) {
        tela.output.append("\n--------------------------------\n");
        tela.output.append("Iniciando análise léxica...\n");
        
        StringReader sr = new StringReader(input);
        Lexico lex = new Lexico(sr);
        Token token = null;

        try {
            token = lex.nextToken();
            while (token != null) {
                token = lex.nextToken();
            }
            tela.output.append("Nenhum erro léxico encontrado.\n");
            JOptionPane.showMessageDialog(tela, "Nenhum erro léxico encontrado.", "Análise Léxica", JOptionPane.INFORMATION_MESSAGE);
        } catch (LexicalError ex) {
            tela.output.append("ERRO LÉXICO: " + ex.getMessage() + " na posição " + ex.getPosition() + " depois de '" + token.getLexeme() + "'" + "\n");
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Análise Léxica", JOptionPane.ERROR_MESSAGE);
            tela.editor.setCaretPosition(ex.getPosition());
        }
        tela.editor.requestFocusInWindow();

    }

    public void syntaticAnalisys(String input) {
        tela.output.append("\n--------------------------------\n");
        tela.output.append("Iniciando análise sintática...\n");
        
        StringReader sr = new StringReader(input);
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();
    
        try {    
            sintatico.parse(new Lexico(sr), semantico);

            tela.output.append("Nenhum erro sintático encontrado.\n");
            JOptionPane.showMessageDialog(tela, "Nenhum erro sintático encontrado.", "Análise Sintática", JOptionPane.INFORMATION_MESSAGE);
        } catch (SyntaticError ex) {
            tela.output.append("ERRO SINTÁTICO: " + ex.getMessage() + " na posição " + ex.getPosition() + " antes de '" + sintatico.getCurrentToken().getLexeme() + "'" + "\n");
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Análise Sintática", JOptionPane.ERROR_MESSAGE);
            tela.editor.setCaretPosition(ex.getPosition() > 0 ? ex.getPosition() : 0);
        } catch (LexicalError ex) {
            tela.output.append("ERRO LÉXICO: " + ex.getMessage() + " na posição " + ex.getPosition() + " antes de '" + sintatico.getCurrentToken().getLexeme() + "'" + "\n");
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Análise Léxica", JOptionPane.ERROR_MESSAGE);
            tela.editor.setCaretPosition(ex.getPosition() > 0 ? ex.getPosition() : 0);
        } catch (SemanticError ex) {
            tela.output.append("ERRO SEMÂNTICO: " + ex.getMessage() + " na posição " + ex.getPosition() + " antes de '" + sintatico.getCurrentToken().getLexeme() + "'" + "\n");
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Análise Semântica", JOptionPane.ERROR_MESSAGE);
            tela.editor.setCaretPosition(ex.getPosition() > 0 ? ex.getPosition() : 0);
        }
        tela.editor.requestFocusInWindow();
    }
    
    public void open_file() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new LSIFilter());
        int response = chooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION){
            String filePath = chooser.getSelectedFile().getPath();
            try {
                FileInputStream fr = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder buffer = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                reader.close();

                tela.editor.setText(buffer.toString());
            } catch (IOException e) {
                tela.output.append(e.getMessage());
            }
        }
    }
    
    public void save_as() {
        JFileChooser filesave = new JFileChooser();
        filesave.setFileFilter(new LSIFilter());
        int response = filesave.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filesave.getSelectedFile()));
                    tela.editor.write(writer);
                    writer.close();
            } catch (IOException ex) {
                tela.output.append(ex.getMessage());
            }
        }
    }

}
