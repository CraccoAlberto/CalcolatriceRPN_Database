import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JOptionPane;

public class CalcolatriceRPN
{
    private JPanel pMain;
    private JTextField txtIn;
    private JTextField txtOut;
    private JButton b7;
    private JButton b1;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b8;
    private JButton b2;
    private JButton b3;
    private JButton bTimes;
    private JButton bPlus;
    private JButton bCanc;
    private JButton bDivision;
    private JButton bMinus;
    private JButton bEqual;
    private JButton bOpen;
    private JButton bClose;
    private JButton b9;

    void OnButtonClick(JButton b)
    {
        txtIn.setText(txtIn.getText()+b.getText());
    }

    String convertToRPN(String input)
    {
        String s = "";
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray())
        {
            if (Character.isDigit(c))
            {
                s += c;
            }
            else if (c == '(')
            {
                stack.push(c);
            }
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    s += " " + stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
            else
            {
                if (!s.isEmpty() && s.charAt(s.length() - 1) != ' ') {
                    s += " ";
                }
                while (!stack.isEmpty() && comparePriority(stack.peek(), c)) {
                    s += stack.pop() + " ";
                }

                stack.push(c);
            }
        }

        while(!stack.isEmpty())
        {
            s+=" "+stack.pop();
        }
        return s;
    }

    float solveRPN(String input)
    {
        Stack<Float> st = new Stack<>();
        float n1, n2;
        String[] arr = input.split("\\s+");

        for(String s:arr)
        {
            float res=0;
            if(s.matches("\\d+")){
                st.push(Float.parseFloat(s));
            }
            else
            {
                n2=st.pop();
                n1=st.pop();

                if(s.equals("+"))
                {
                    res=n1+n2;
                }
                else if(s.equals("-"))
                {
                    res=n1-n2;
                }
                else if(s.equals("/") || s.equals("÷"))
                {
                    res=n1/n2;
                }
                else if(s.equals("*") || s.equals("×"))
                {
                    res=n1*n2;
                }
                st.push(res);
            }
        }
        return st.pop();
    }

    int operatorPriority(char c){
        if(c=='*' || c=='×' || c=='/' || c=='÷'){
            return 2;
        }
        else if(c=='+' || c=='-'){
            return 1;
        }
        return 0;
    }

    boolean comparePriority(char c1, char c2){
        if(operatorPriority(c1) >= operatorPriority(c2)){
            return true;
        }
        return false;
    }

    boolean isPostfix(String input){
        char lastChar=input.charAt(input.length()-1);
        if(lastChar=='+'||lastChar=='-'||lastChar=='*'||lastChar=='/'||lastChar=='×'||lastChar=='÷'){
            return true;
        }
        return false;
    }

    public CalcolatriceRPN()
    {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b1);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b2);
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b3);
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b4);
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b5);
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b6);
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b7);
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b8);
            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(b9);
            }
        });

        bPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bPlus);
            }
        });

        bMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bMinus);
            }
        });

        bTimes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bTimes);
            }
        });

        bDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bDivision);
            }
        });

        bCanc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bCanc);
            }
        });

        bOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bOpen);
            }
        });

        bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick(bClose);
            }
        });

        bEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String inputText=txtIn.getText();
                if(!inputText.isBlank() && !inputText.isEmpty())
                {
                    if (isPostfix(inputText))
                    {
                        txtOut.setText(Float.toString(solveRPN(inputText)));
                    }
                    else
                    {
                        txtOut.setText(Float.toString(solveRPN(convertToRPN(inputText))));
                    }
                }
            }
        });
    }

    public static void main() {
        JFrame frame = new JFrame("CalcolatriceRPN");
        frame.setContentPane(new CalcolatriceRPN().pMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
