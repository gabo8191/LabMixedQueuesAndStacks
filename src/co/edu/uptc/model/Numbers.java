package co.edu.uptc.model;

import java.util.ArrayList;

import co.edu.uptc.structures.MyQueue;
import co.edu.uptc.structures.MyStack;

public class Numbers {

  private ArrayList<MyStack<Integer>> stacks;
  private ArrayList<MyQueue<Integer>> queues;

  public Numbers() {
    stacks = new ArrayList<>();
    queues = new ArrayList<>();
  }

  public void fillStacks(ArrayList<String> nums) {
    for (String num : nums) {
      MyStack<Integer> stack = new MyStack<>();
      for (int i = 0; i < num.length(); i++) {
        stack.push(Integer.parseInt(num.charAt(i) + ""));
      }
      stacks.add(stack);
    }
  }

  public String printStacks() {
    String result = "";
    for (MyStack<Integer> stack : stacks) {
      while (!stack.isEmpty()) {
        result += stack.pop();
      }
      result += "\n";
    }
    return result;
  }

  private MyQueue<Integer> addStacks() {
    MyQueue<Integer> queue = new MyQueue<Integer>();
    int add = 0;
    int auxAdd = 0;
    int[] maxMin = this.calculateMaxMin(stacks.get(0), stacks.get(1));
    for (int i = 0; i < maxMin[1]; i++) {
      int numStack0 = stacks.get(0).pop();
      int numStack1 = stacks.get(1).pop();
      add = numStack0 + numStack1 + auxAdd;
      auxAdd = 0;
      if (add > 9) {
        auxAdd = add / 10;
        add = add % 10;
      }
      queue.push(add);
    }
    for (int i = 0; i < (maxMin[0] - maxMin[1]); i++) {
      if (!stacks.get(0).isEmpty()) {
        queue.push(stacks.get(0).pop() + auxAdd);
      } else if (!stacks.get(1).isEmpty()) {
        queue.push(stacks.get(1).pop() + auxAdd);
      }
      auxAdd = 0;
    }
    queues.add(queue);
    return queue;
  }

  private int[] calculateMaxMin(MyStack<Integer> stack1, MyStack<Integer> stack2) {
    int[] maxMin = new int[2];
    int maxSize = 0;
    int minSize = 0;
    if (stack1.size() < stack2.size()) {
      maxSize = stack2.size();
      minSize = stack1.size();
    } else {
      maxSize = stack1.size();
      minSize = stack2.size();
    }
    maxMin[0] = maxSize;
    maxMin[1] = minSize;
    return maxMin;
  }

  public String printQueue() {
    String result = "";
    for (MyQueue<Integer> queue : queues) {
      while (!queue.isEmpty()) {
        result = queue.poll() + result;
      }
      result += "\n";
    }
    return result;
  }

  public MyQueue<Integer> add() {
    MyQueue<Integer> queue = new MyQueue<Integer>();
    this.addStacks();
    for (int i = 0; i < stacks.size() - 2; i++) {
      queue = this.addQueueStack(i);
    }
    return queue;
  }

  private MyQueue<Integer> addQueueStack(int number) {
    MyQueue<Integer> queue = new MyQueue<Integer>();
    int add = 0;
    int auxAdd = 0;
    int min = this.calculateMin(queues.get(number), stacks.get(number + 2));
    int max = this.calculateMax(queues.get(number), stacks.get(number + 2));
    for (int j = 0; j < min; j++) {
      int num1 = stacks.get(number + 2).pop();
      int num2 = queues.get(number).poll();
      add = num1 + num2 + auxAdd;
      auxAdd = 0;
      if (add > 9) {
        auxAdd = add / 10;
        add = add % 10;
      }
      queue.push(add);
    }

    for (int i = 0; i < (max - min); i++) {
      if (!stacks.get(number + 2).isEmpty()) {
        queue.push(stacks.get(number + 2).pop() + auxAdd);
      } else if (!queues.get(number).isEmpty()) {
        queue.push(queues.get(number).poll() + auxAdd);
      }
    }
    auxAdd = 0;
    queues.add(queue);
    return queue;
  }

  private int calculateMin(MyQueue<Integer> queue, MyStack<Integer> stack) {
    int minSize = 0;
    if (queue.size() > stack.size()) {
      minSize = stack.size();
    } else {
      minSize = queue.size();
    }
    return minSize;
  }

  private int calculateMax(MyQueue<Integer> queue, MyStack<Integer> stack) {
    int maxSize = 0;
    if (queue.size() < stack.size()) {
      maxSize = stack.size();
    } else {
      maxSize = queue.size();
    }
    return maxSize;
  }
}
