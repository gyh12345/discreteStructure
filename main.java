import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
                final JFrame m = new JFrame("Prime Number and Prime Factorization");
                JLabel a = new JLabel("Input a number : ");
                JLabel b = new JLabel("Choose an operation from below to perform : ");
                JCheckBox checkBox1 = new JCheckBox("Check whether the number is a prime number.");
                JCheckBox checkBox2 = new JCheckBox("Count prime factorization for the number.");
                JButton count = new JButton("Count");
                JTextField t1 = new JTextField(null);
                JTextArea t2=new JTextArea();
                a.setBounds(50, 20, 300, 60);
                b.setBounds(50, 60, 300, 60);
                checkBox1.setBounds(50, 100, 300, 60);
                checkBox2.setBounds(50, 140, 300, 60);
                count.setBounds(130, 220, 100, 40);
                t1.setBounds(150,30, 200,40);
                t2.setBounds(400,30, 550,500);
                JScrollPane scroll = new JScrollPane(t2);


        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input =  Integer.parseInt(t1.getText());  // user input
                String output = "";

                if (input > 1) {
                    if (checkBox1.isSelected()) // calculate prime number
                        output = calcPrimeNumber(input);
                    else if (checkBox2.isSelected()) // calculate prime factorization
                        output = calcPrimeFactor(input);
                    System.out.println(output);
                    t2.setText(output);
                    if (checkBox1.isSelected()&&checkBox2.isSelected())
                        JOptionPane.showMessageDialog(null,
                                "Please select only one operation !",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                } else if (input <= 1) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter positive integer that greater than 1 !",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
                else { // any other input
                    JOptionPane.showMessageDialog(null,
                            "Input not valid ! Please enter positive integer that greater than 1 !",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        m.add(a);
        m.add(b);
        m.add(checkBox1);
        m.add(checkBox2);
        m.add(count);
        m.add(t1);
        m.add(t2);
        m.add(scroll);


        m.setSize(1000, 600);
        m.setResizable(false);
        m.setLocationRelativeTo(null);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static String calcPrimeNumber (int n) {
        boolean solved = false;
        boolean isPrime = false;
        StringBuilder sb = new StringBuilder("Calculate if " + n + " is a prime number:\n" + "Performing Steps...\n\n");

        // Step 1
        sb.append("Step 1: Check whether the number is 2.\n");
        if (n == 2){ // if the number is 2
            sb.append("\tThe number is 2, therefore it is a prime number.");
            isPrime = true;
            solved = true;
        } else {
            sb.append("\tThe number is not 2, proceed to Step 2.\n\n");
        } // Step 1 end

        // Step 2
        if (!solved) { // if step 1 did not solve the number, then start step 2
            sb.append("Step 2: Check whether " + n + " can be divided by 2.\n\t");
            if (n % 2 == 0) { // if the number can be divided by 2
                sb.append(n + " can be divided by 2. (Remainder = 0)\n\t" + "Therefore, " + n + " is not a prime number.");
                solved = true;
            } else {
                sb.append(n + " cannot be divided by 2. (Remainder = 1)\n\t" + "Proceed to Step 3.\n\n");
            }
        } // Step 2 end

        // Step 3
        if (!solved) { // if step 2 did not solve the number, then start step 3 following by step 4
            sb.append("Step 3: Calculate the square root of " + n + ".\n\t");
            double sqrtN = Math.sqrt(n); // count square root of the number
            sb.append("The square root of " + n + " is " + String.format("%.2f", sqrtN) +
                    "\n\t** REMINDER: Round off to the smaller number **" +
                    "\n\tTherefore k = " + (int)Math.floor(sqrtN) + "\n\tProceed to Step 4.\n\n");
            // Step 3 end

            // Step 4
            sb.append("Step 4: Check whether " + n + " can be divided by any\n\t\todd number(D) between 3 and k, where k = " +
                    (int)Math.floor(sqrtN) + ".");
            ArrayList<Integer> oddBetweenSqrt = new ArrayList(); // to save the odd number(s) between 3 and k
            for (int i = 3; i <= sqrtN; i++){ // to detect and save odd number
                if (i % 2 == 1)
                    oddBetweenSqrt.add(i);
            }
            sb.append("\n\tD = ");
            for (int i = 0; i < oddBetweenSqrt.size(); i++) { // to display odd number(s) between 3 and k
                if (i % 10 == 0 && i != 0)
                    sb.append("\n\t\t");
                if (i == oddBetweenSqrt.size()-1) {
                    sb.append(oddBetweenSqrt.get(i) + "\n\t");
                    break;
                }
                sb.append(oddBetweenSqrt.get(i) + ", ");
            }
            int dividedCount = 0;
            for (int i = 0; i < oddBetweenSqrt.size(); i++){
                if (oddBetweenSqrt.get(i) % n == 0) {
                    dividedCount++; // count++ when any odd number can divide it.
                }
            }
            if (dividedCount == 0){ // if none of the odd numbers can divide the input number
                sb.append(n + " cannot be divided by any value D between "+ (int)Math.floor(sqrtN) +"." +
                        "\n\tTherefore, " + n + " is a prime number.");
                isPrime = true;
            } else {
                sb.append(n + " can be divided by certain value D between "+ (int)Math.floor(sqrtN) +"." +
                        "\n\tTherefore, " + n + " is not a prime number.");
            } // Step 4 end
        }

        if (isPrime == true)
            sb.append("\n\nANSWER: " + n + " IS A PRIME NUMBER.");
        else
            sb.append("\n\nANSWER: " + n + " IS NOT A PRIME NUMBER.");

        return sb.toString(); // return output answer
    }

    /**
     * This method is used to calculate prime factorization of user input.
     * @param n is input from user
     * @return output String
     */
    public static String calcPrimeFactor (int n) {
        StringBuilder sb = new StringBuilder("Finding the Prime Factorization of " + n + ":\n" + "Performing Steps...\n\n");
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();
        boolean isPrime = false;
        int number = n;
        answer.add(n);

        for (int i = 1; i <= 10000; i++) { // Count prime numbers between 2 - 100 and save to ArrayList
            int counter = 0;
            for(int num = i; num >= 1; num--) {
                if(i % num == 0) {
                    counter = counter + 1;
                }
            }
            if (counter == 2) {
                primeNumbers.add(i);
            }
        }

        for (int x : primeNumbers) {
            if (x == n) {
                isPrime = true;
            }
        }

        if (isPrime == true) {
            sb.append(n + " is a prime number.\nA prime number can only be divided by 1 or itself,\n" +
                    "therefore it cannot be factorized further!");
        } else {
            sb.append("Step 1: Divide "+ n +" by prime numbers until the remainder is a prime number.\n");
            for (int i = 0; i < primeNumbers.size(); i++){
                sb.append("\tAttempt to divide " + number + " by " + primeNumbers.get(i) + ":\n\t");
                if (number % primeNumbers.get(i) == 0){
                    sb.append(number + " / " + primeNumbers.get(i) + " = ");
                    answer.add(primeNumbers.get(i)); // add the prime number to answer list
                    number = number / primeNumbers.get(i); // number = number / prime number
                    sb.append(number + "\n\n");

                    for (int x : primeNumbers){
                        if (number == x) {
                            answer.add(number);
                            sb.append("\tThe Remainder "+ number +" is a Prime Number\n\tProceed to Step 2\n");
                            sb.append("\nStep 2: Write the number as a product of prime numbers.");
                            sb.append("\nANSWER: Prime Factorization of " + answer.get(0) + " = ");
                            for (int j = 1; j < answer.size(); j++) {
                                for (int k = j + 1; j < answer.size(); k++) {
                                    if (k != answer.size()) {
                                        if (answer.get(j) > answer.get(k)) {
                                            int temp = answer.get(j);
                                            answer.set(j, answer.get(k));
                                            answer.set(k, temp);
                                        }
                                    } else break;
                                }
                                if (j == answer.size()-1)
                                    sb.append(answer.get(j));
                                else
                                    sb.append(answer.get(j) + " x ");
                            }
                            i = primeNumbers.size()-1;
                        }
                    }
                } else {
                    sb.append(number + " cannot be divided by " + primeNumbers.get(i) + "\n\n");
                }
            }
        }

        return sb.toString();
    }
}
