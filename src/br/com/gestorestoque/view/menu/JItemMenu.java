
package br.com.gestorestoque.view.menu;

import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.view.FRMPrincipal;
import br.com.gestorestoque.view.menu.fabrica.CriadorFrame;
import br.com.gestorestoque.view.menu.fabrica.EnumFRM;
import java.awt.Cursor;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

/**
 *
 * @author Matheus
 */
public class JItemMenu extends JMenuItem {
   
    private final String iconURL,titulo;
    private final EnumFRM tipoFrame;

    public JItemMenu(String iconURL, String titulo, EnumFRM tipoFrame) {
        this.iconURL = iconURL;
        this.titulo = titulo;
        this.tipoFrame = tipoFrame;        
        montarItemMenu();
    }
    
    
    

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource(iconURL))); // NOI18N
        setText(titulo);

        addActionListener((e) -> {
            FRMPrincipal principal = FRMPrincipal.getInstance();
            principal.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            JDialog janela = CriadorFrame.getFrame(tipoFrame);
            principal.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            janela.setVisible(true);
        });

    }
}
