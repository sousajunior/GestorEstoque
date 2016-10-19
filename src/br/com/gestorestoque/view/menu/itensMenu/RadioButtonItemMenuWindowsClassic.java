package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author DG
 */
public class RadioButtonItemMenuWindowsClassic extends JRadioButtonMenuItem {

    public RadioButtonItemMenuWindowsClassic() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        
        setText("Windows Classic");
        addActionListener((e) -> {
              try {                
                
                FRMPrincipal fRMPrincipal = FRMPrincipal.getInstance();
                FRMUtil.alterarLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", fRMPrincipal);
                FRMUtil.uncheck((JPopupMenu)this.getParent());
                this.setSelected(true);
                
                
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
            }

        });

    }

}
