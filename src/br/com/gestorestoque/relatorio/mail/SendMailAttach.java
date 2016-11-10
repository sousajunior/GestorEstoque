/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.relatorio.mail;

import br.com.gestorestoque.relatorio.excel.ExportadorExcel;
import br.com.gestorestoque.util.RelatorioUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import jxl.write.WriteException;

/**
 *
 * @author DG
 */
public class SendMailAttach {

    
    
    JFrame frame;
    Relatorio relatorio;
    JTable tabela;
    String enderecos;
    public SendMailAttach(JFrame frame, Relatorio relatorio, JTable tabela,String enderecos) throws WriteException, IOException {
        this.frame = frame;
        this.relatorio = relatorio;
        this.tabela = tabela;
        this.enderecos = enderecos;
        new ExportadorExcel(frame, tabela, relatorio, true);
        enviar();
    }

    
    public void enviar() {
        try {

            //usuario e senha do seu gmail
            final String usuario = "gestoque.naoresponda@gmail.com";
            final String senha = "gestoque123";

            //config. do gmail
            Properties mailProps = new Properties();
            mailProps.put("mail.transport.protocol", "smtp");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.host", "smtp.gmail.com");
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.user", usuario);
            mailProps.put("mail.debug", "true");
            mailProps.put("mail.smtp.port", "465");
            mailProps.put("mail.smtp.socketFactory.port", "465");
            mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailProps.put("mail.smtp.socketFactory.fallback", "false");

            //eh necessario autenticar
            Session mailSession = Session.getInstance(mailProps, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, senha);
                }
            });
            mailSession.setDebug(false);

            //config. da mensagem
            Message mailMessage = new MimeMessage(mailSession);

            //remetente
            mailMessage.setFrom(new InternetAddress(usuario));

            //destinatario
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(enderecos));

            //mensagem que vai no corpo do email
            MimeBodyPart mbpMensagem = new MimeBodyPart();
            mbpMensagem.setText("Segue em anexo, relatório gerado pelo sistema de gerenciamento de estoque.");

            //partes do email
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbpMensagem);

            //JFileChooser fc = new JFileChooser();
            //fc.showOpenDialog(frame);
            //JOptionPane.showMessageDialog(null, fc.getSelectedFile().toString());
            
            //setando o anexo
            MimeBodyPart mbpAnexo = new MimeBodyPart();
            //mbpAnexo.setDataHandler(new DataHandler(new ByteArrayDataSource(is2, "application/image")));
             mbpAnexo.attachFile("C:/Users/Public/"+RelatorioUtil.retornarNomeRelatorio(relatorio)+".xls");//new File(fc.getSelectedFile().toString())); 
            
            mbpAnexo.setFileName(RelatorioUtil.retornarNomeRelatorio(relatorio)+".xls");
            mp.addBodyPart(mbpAnexo);

            //assunto do email
            mailMessage.setSubject("Relatório gerado e enviado pelo sistema de gerenciamento de estoque.");

            //seleciona o conteudo
            mailMessage.setContent(mp);

            //envia o email
            Transport.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
