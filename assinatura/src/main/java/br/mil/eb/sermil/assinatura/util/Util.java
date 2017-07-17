package br.mil.eb.sermil.assinatura.util;

import java.awt.TrayIcon.MessageType;

import javax.swing.JOptionPane;

/**
 * Helper class for data manipulation Classe de ajuda para manipulacao de dados
 * 
 * @author Anselmo S Ribeiro <anselmo.sr@gmail.com>
 * @version 1.0
 * @since 1.0
 * 
 */
public class Util {

  public final static String TITLE = "SERMIL - Assinatura Digital";

  public static void displayErrorMsg(String msg) {
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), msg, TITLE, MessageType.ERROR.ordinal());
  }

  public static void displaySuccessMsg(String msg) {
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), msg, TITLE, MessageType.INFO.ordinal());
  }

  public static void displayWarningMsg(String msg) {
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), msg, TITLE, MessageType.WARNING.ordinal());
  }

}
