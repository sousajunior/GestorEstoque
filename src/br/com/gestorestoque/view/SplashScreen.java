package br.com.gestorestoque.view;

import br.com.gestorestoque.util.FRMUtil;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UnsupportedLookAndFeelException;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Matheus
 */
public class SplashScreen extends JWindow {

    AbsoluteLayout absoluto;
    AbsoluteConstraints absBarra, absImagem, absMensagem;
    ImageIcon imagem;
    JLabel label = new JLabel();
    JProgressBar barra = new JProgressBar();

    ;
    public SplashScreen() {

        absoluto = new AbsoluteLayout();
        absImagem = new AbsoluteConstraints(0, 0);
        absBarra = new AbsoluteConstraints(0, 310);
        imagem = new ImageIcon(this.getClass().getResource("Imagens//gestao-de-estoque.png"));

        barra.setPreferredSize(new Dimension(591, 20));
        java.awt.Color cor = new java.awt.Color(164, 36, 41);
        barra.setForeground(cor);
        barra.setBorderPainted(false);
        barra.setBackground(java.awt.Color.WHITE);
        label.setIcon(imagem);
        this.getContentPane().setLayout(absoluto);
        this.getContentPane().add(label, absImagem);
        this.getContentPane().add(barra, absBarra);

        new Thread() {
            @Override
            public void run() {

                try {
                    barra.setStringPainted(true);
                    barra.setString("Inicializando o programa ...");
                    for (int i = 0; i <= 100; i++) {
                        barra.setValue(i);
                        try {
                            sleep(30);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FRMPrincipal janelaPrincipal = new FRMPrincipal();
                    FRMUtil.alterarLookAndFeel("com.alee.laf.WebLookAndFeel", janelaPrincipal);
                    janelaPrincipal.setVisible(true);
                    SplashScreen.this.setVisible(false);

                } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {

                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", 0);

                }

            }
        }.start();
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
