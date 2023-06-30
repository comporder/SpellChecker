/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.datastructuressecondproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author emir
 */
public class SpellChecker extends javax.swing.JFrame {

    /**
     * Creates new form SpellChecker
     */
    BinarySearchTree search = new BinarySearchTree();

    // The constructor initializes the GUI components, sets the window location to the center of the screen,
    // and loads the words from a text file named "dictionary.txt" into the BinarySearchTree instance named "search".
    public SpellChecker() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Create a new instance of BinarySearchTree
        search.readFromFile("dictionary.txt");
    }

    // This method checks the text in the text area for punctuation and capitalization errors, 
    //and replaces the words containing such errors with the corrected versions.
    private void controlOfPunctuation() {
        String text = textArea.getText();
        ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(text.split("\\s+")));
        ArrayList<String> wrongWords = new ArrayList<String>();
        for (String word : wordsList) {
            if (word.matches(".*[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]+.*")
                    || word.matches(".*[A-Za-z]+.*[A-Z]+.*")
                    || word.matches(".*[A-Za-z]+.*[a-z]+.*")) {
                String newWord = word.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").toLowerCase();
                wrongWords.add(word);
                text = text.replaceAll(word, newWord);
            }
        }
        textArea.setText(text);
    }

    // The code checks the spelling of words in a given text by searching for them in a binary search tree data structure 
    //and adds any misspelled words to a separate text area for display.
    private void controlOfSpelling() {
        String text = textArea.getText();
        ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(text.split("\\s+")));
        ArrayList<String> wrongWords = new ArrayList<String>();
        for (String word : wordsList) {
            if (search.search(word) == false) {
                wrongWords.add(word);
            }
        }
        addStringsToTextArea(wrongWords, wrongsTextArea);
    }
    // This code defines a method that takes an ArrayList of Strings and a JTextArea as input, 
//creates a StringBuilder, appends each string in the list to the StringBuilder with a newline character, 
//and sets the text of the JTextArea to the resulting string.

    public void addStringsToTextArea(ArrayList<String> list, JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append("\n");
        }
        textArea.setText(sb.toString());
    }
// This method reads a dictionary file into a BinarySearchTree,
// then uses it to find suggestions for a misspelled word based on the Levenshtein distance algorithm.

    private BinarySearchTree<String> suggest(String word) {
        BinarySearchTree spell = new BinarySearchTree();
        spell.readFromFile("dictionary.txt");
        BinarySearchTree<String> suggestions = new BinarySearchTree<String>();
        if (spell.search(word) == false) {
            // Suggest the closest word in the tree based on Levenshtein distance
            suggestions = spell.suggestion(word);
        }
        return suggestions;

    }
//  This code completes the last word of a text area by finding all words in a binary search tree that start 
//with the given prefix and updating the text area with the completed word.

    private void complete() {
        // Son kelimeyi almak için, JTextArea içindeki metni alın
        String text = textArea.getText();
        String temp = text;
        // Metni sondan başa doğru tarayın ve son kelimeyi bulun
        int lastSpaceIndex = temp.lastIndexOf(" ");
        String lastWord = temp.substring(lastSpaceIndex + 1);
        BinarySearchTree spell = new BinarySearchTree();
        spell.readFromFile("dictionary.txt");
        // Find all words in the tree that start with the given prefix
        String complete = spell.auto(lastWord);
        if (!complete.equals(null) && !lastWord.equals("")) {
            String newText = temp.substring(0, lastSpaceIndex + 1) + complete;
            // JTextArea'yı yeni metinle güncelleyin
            textArea.setText(newText);
        } else {
            textArea.setText(text);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        pnl_spellChecker = new javax.swing.JPanel();
        lbl_spellChecker = new javax.swing.JLabel();
        scrollTextArea = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        pnl_description = new javax.swing.JPanel();
        lbl_tab2 = new javax.swing.JLabel();
        lbl_select2 = new javax.swing.JLabel();
        pnl_select = new javax.swing.JPanel();
        lbl_select = new javax.swing.JLabel();
        pnl_tab = new javax.swing.JPanel();
        lbl_tab = new javax.swing.JLabel();
        pnl_controls = new javax.swing.JPanel();
        btn_reset = new javax.swing.JButton();
        btn_control = new javax.swing.JButton();
        scrollWrongsTextArea = new javax.swing.JScrollPane();
        wrongsTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        popup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popupMouseClicked(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");
        popup.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnl_spellChecker.setMaximumSize(new java.awt.Dimension(597, 470));
        pnl_spellChecker.setMinimumSize(new java.awt.Dimension(597, 470));
        pnl_spellChecker.setPreferredSize(new java.awt.Dimension(597, 470));
        pnl_spellChecker.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_spellChecker.setFont(new java.awt.Font("Imprint MT Shadow", 0, 24)); // NOI18N
        lbl_spellChecker.setForeground(new java.awt.Color(153, 153, 0));
        lbl_spellChecker.setText("SPELL CHECKER");
        pnl_spellChecker.add(lbl_spellChecker, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 230, 40));

        textArea.setColumns(20);
        textArea.setForeground(new java.awt.Color(102, 102, 0));
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 2, true), "Text Area", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        textArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textAreaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textAreaMouseReleased(evt);
            }
        });
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaKeyPressed(evt);
            }
        });
        scrollTextArea.setViewportView(textArea);

        pnl_spellChecker.add(scrollTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 500, 120));

        pnl_description.setBackground(new java.awt.Color(255, 255, 255));
        pnl_description.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 2, true));
        pnl_description.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_tab2.setText("Press tab button from your keyboard to auto-complete");
        pnl_description.add(lbl_tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 310, -1));

        lbl_select2.setText("Select the word and right click to see suggest corrections");
        pnl_description.add(lbl_select2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 310, -1));

        pnl_select.setBackground(new java.awt.Color(153, 153, 0));
        pnl_select.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_select.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_select.setForeground(new java.awt.Color(255, 255, 255));
        lbl_select.setText("Select");
        pnl_select.add(lbl_select, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        pnl_description.add(pnl_select, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 90, 20));

        pnl_tab.setBackground(new java.awt.Color(153, 153, 0));
        pnl_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_tab.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tab.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tab.setText("Tab");
        pnl_tab.add(lbl_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 20, -1));

        pnl_description.add(pnl_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 20));

        pnl_spellChecker.add(pnl_description, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 500, 80));

        pnl_controls.setBackground(new java.awt.Color(255, 255, 255));
        pnl_controls.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 2, true));
        pnl_controls.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_reset.setBackground(new java.awt.Color(153, 153, 0));
        btn_reset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setText("Reset the Text Area");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        pnl_controls.add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 220, 30));

        btn_control.setBackground(new java.awt.Color(153, 153, 0));
        btn_control.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_control.setForeground(new java.awt.Color(255, 255, 255));
        btn_control.setText("Control");
        btn_control.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_controlActionPerformed(evt);
            }
        });
        pnl_controls.add(btn_control, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 30));

        wrongsTextArea.setColumns(20);
        wrongsTextArea.setForeground(new java.awt.Color(255, 51, 51));
        wrongsTextArea.setLineWrap(true);
        wrongsTextArea.setRows(5);
        wrongsTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 0), 2), "Mispelled Wrong Words According to Dictionary", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        scrollWrongsTextArea.setViewportView(wrongsTextArea);

        pnl_controls.add(scrollWrongsTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 50, 450, 80));

        pnl_spellChecker.add(pnl_controls, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 500, 150));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        pnl_spellChecker.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 470, 20));

        getContentPane().add(pnl_spellChecker);

        pack();
    }// </editor-fold>//GEN-END:initComponents
// This code creates a new JPopupMenu that appears when the user right-clicks on a selected word in the textArea 
//and suggests possible corrections for the misspelled word based on the suggestions returned by the suggest() method.
    private void textAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textAreaMouseReleased

        JPopupMenu newJPopupMenu = new JPopupMenu();
        ArrayList<JMenuItem> menus = new ArrayList<>();
        if (evt.getButton() == MouseEvent.BUTTON3) {
            String word = textArea.getSelectedText();
            if (!word.isEmpty()) {
                BinarySearchTree<String> sg = suggest(word);
                ArrayList<Node> suggestions = sg.getAllNodes();
                for (int i = 0; i < sg.getNodeCount(); i++) {
                    JMenuItem newItem = new JMenuItem();
                    newItem.setText(suggestions.get(i).word.toString());
                    // ActionListener'ları ayarla
                    newItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String selectedText = textArea.getSelectedText();
                            String replacementText = newItem.getText();
                            textArea.replaceSelection(replacementText);
                        }
                    });
                    menus.add(newItem);
                    newJPopupMenu.add(newItem);
                }
                newJPopupMenu.show(this, textArea.getX() + 250, textArea.getY() + 250);
            }
        }
    }//GEN-LAST:event_textAreaMouseReleased

    // This code maps the "Tab" key to an action that calls the "complete" method when pressed, which completes the last word in the JTextArea.

    private void textAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaKeyPressed
        // 
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "editLastWord");
        textArea.getActionMap().put("editLastWord", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                complete();
            }
        });

    }//GEN-LAST:event_textAreaKeyPressed


    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        textArea.setText("");
        wrongsTextArea.setText("");
    }//GEN-LAST:event_btn_resetActionPerformed

    private void popupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popupMouseClicked
        // TODO add your handling code here:
        JMenuItem item = (JMenuItem) evt.getSource();
        String selectedText = textArea.getSelectedText();
        String replacementText = item.getText();
        textArea.replaceSelection(replacementText);
        textArea.setText("");
    }//GEN-LAST:event_popupMouseClicked

    private void btn_controlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_controlActionPerformed
        // TODO add your handling code here:
        controlOfPunctuation();
        controlOfSpelling();
    }//GEN-LAST:event_btn_controlActionPerformed

    private void textAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textAreaMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_textAreaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpellChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpellChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpellChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpellChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpellChecker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_control;
    private javax.swing.JButton btn_reset;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_select;
    private javax.swing.JLabel lbl_select2;
    private javax.swing.JLabel lbl_spellChecker;
    private javax.swing.JLabel lbl_tab;
    private javax.swing.JLabel lbl_tab2;
    private javax.swing.JPanel pnl_controls;
    private javax.swing.JPanel pnl_description;
    private javax.swing.JPanel pnl_select;
    private javax.swing.JPanel pnl_spellChecker;
    private javax.swing.JPanel pnl_tab;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JScrollPane scrollTextArea;
    private javax.swing.JScrollPane scrollWrongsTextArea;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextArea wrongsTextArea;
    // End of variables declaration//GEN-END:variables

}
