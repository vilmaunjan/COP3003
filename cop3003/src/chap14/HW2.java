package chap14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HW2 extends JFrame implements KeyListener {

    private JLabel arabicLabel;
    private JLabel romanLabel;
    private JLabel blank;
    private JTextField arabicText;
    private JTextField romanText;
    private String roman;
    private int arabic;

    public HW2() {
        super("Roman <--> Arabic");
        setLayout(new FlowLayout());
        Container container = getContentPane();
        arabicLabel = new JLabel("Arabic Numural                        ");
        arabicText = new JTextField(10);
        romanLabel = new JLabel("Roman Numural                       ");
        romanText = new JTextField(10);

        container.add(arabicLabel);
        container.add(arabicText);
        container.add(romanLabel);
        container.add(romanText);

        arabicText.addKeyListener(this);
       
    }

    public static void main(String args[]) {
        HW2 homework = new HW2();
        homework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homework.setSize(290, 90); // set frame size
        homework.setResizable(false);
        homework.setVisible(true); // display frame    
    }

    public String arabicToRoman(int arabic) {
        if (arabic < 1 || arabic > 3999) {
            return "Invalid Roman Number Value";
        }
        roman = "";
        while (arabic >= 1000) {
            roman += "M";
            arabic -= 1000;
        }
        while (arabic >= 900) {
            roman += "CM";
            arabic -= 900;
        }
        while (arabic >= 500) {
            roman += "D";
            arabic -= 500;
        }
        while (arabic >= 400) {
            roman += "CD";
            arabic -= 400;
        }
        while (arabic >= 100) {
            roman += "C";
            arabic -= 100;
        }
        while (arabic >= 90) {
            roman += "XC";
            arabic -= 90;
        }
        while (arabic >= 50) {
            roman += "L";
            arabic -= 50;
        }
        while (arabic >= 40) {
            roman += "XL";
            arabic -= 40;
        }
        while (arabic >= 10) {
            roman += "X";
            arabic -= 10;
        }
        while (arabic >= 9) {
            roman += "IX";
            arabic -= 9;
        }
        while (arabic >= 5) {
            roman += "V";
            arabic -= 5;
        }
        while (arabic >= 4) {
            roman += "IV";
            arabic -= 4;
        }
        while (arabic >= 1) {
            roman += "I";
            arabic -= 1;
        }
        return roman;
    }

    public int romanToArabic(String roman) {
        int plus = 0;
        int minus = 0;

        int[] nums = {1, 5, 10, 50, 100, 500, 1000};

        for (int i = 0; i < roman.length() - 1; i++) {
            int index1 = roman.indexOf(roman.charAt(i));
            int index2 = roman.indexOf(roman.charAt(i + 1));
            if (index1 < index2) {
                minus = minus + nums[index1];
            } else {
                plus = plus + nums[index1];
            }
        }
        arabic = plus - minus + nums[roman.indexOf(roman.charAt(roman.length() - 1))];
        return arabic;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        try {
            arabic = Integer.parseInt(arabicText.getText());

            if (arabic > 3999) {
                arabicText.setText(arabicText.getText().substring(0,
                        arabicText.getText().length() - 1));
            }

            arabicToRoman(arabic);
            romanText.setText(roman);

        } catch (NumberFormatException exception) {
            arabicText.setText("");
            romanText.setText("");
        }
    }
}
