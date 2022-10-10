import entity.Memory;
import tool.MemoryDispatcher;

import java.util.Scanner;

public class main {


    private int[] queue=new int[1010];
    private static MemoryDispatcher memoryDispatcher = new MemoryDispatcher(new Memory[1010]);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        memoryDispatcher.input();
        memoryDispatcher.doDispatch();

    }
}
