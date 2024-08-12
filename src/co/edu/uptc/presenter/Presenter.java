package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.model.Numbers;
import co.edu.uptc.view.View;

public class Presenter {

  public static void main(String[] args) {
    Numbers numbers = new Numbers();
    View view = new View();
    ArrayList<String> nums = new ArrayList<>();

    String num1 = "246913578024691357802";
    String num2 = "1234500000222";
    String num3 = "12121212124545454545";
    String num4 = "36363636367878787878";

    nums.add(num1);
    nums.add(num2);
    nums.add(num3);
    nums.add(num4);

    numbers.fillStacks(nums);
    numbers.add();

    String result = numbers.printQueue();
    view.showMessage(result);

    // view.showMessage("POR FAVOR DIGITE LOS NÚMEROS, TECLEE 'SALIR' PARA DETENERSE
    // :");
    // String number = null;
    // while (true) {
    // number = view.readData();
    // if (number.equals("SALIR")) {
    // break;
    // }
    // nums.add(number);
    // }

    // numbers.fillStacks(nums);
    // numbers.add();
    // String result = numbers.printQueue();
    // view.showMessage("El resultado es: \n" + result);

  }
}
