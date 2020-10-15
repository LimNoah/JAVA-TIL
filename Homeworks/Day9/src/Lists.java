/**
 * List 인터페이스를 구현하여 IntArrayList 클래스를 완성하시오.
 * 
 * List는 순서가 있는 연속된 값으로, List 인터페이스에 선언되어 있는 메소드의 기능은 아래와 같다.
 *
 * - append(): List의 마지막에 value를 삽입한다.
 * - prepend(): List의 시작점에 value를 삽입한다. 기존의 데이터를 한 칸씩 뒤로 옮긴다.
 * - insert(): index에 value를 삽입한다. 기존 데이터를 한 칸씩 뒤로 옮긴다.
 * - remove(): index의 value를 삭제한다. 기존 데이터를 한 칸씩 앞으로 당긴다.
 * - get(): index의 value를 반환한다.
 * - length(): List의 길이를 출력한다.
 * 
 * IntArrayList는 int []를 이용하여 List를 구현한다.
 * - 생성자에서는 capacity를 입력받으며, 배열의 크기가 부족할 때마다 2배씩 증가시킨다.
 */

interface List {
    void append(int value);
    void prepend(int value);
    void insert(int index, int value);
    void remove(int index);
    int get(int index);
    int length();
}

class IntArrayList implements List {
    private int nCapacity;//용량
    private int nLength;//길이
    private int[] arrIntegers;//배열

    public IntArrayList(int capacity){
        this.nCapacity = capacity;
        nLength = 0;
        arrIntegers = new int[nCapacity];
    }

    private void expandArray(){
        int[] dst = new int[nCapacity * 2];
        System.arraycopy(arrIntegers, 0, dst, 0, nCapacity);
        nCapacity *= 2;
        arrIntegers = dst;
    }

    @Override
    public void append(int value) {
        if(nLength >= nCapacity)
            expandArray();
        arrIntegers[nLength] = value;
        nLength++;
    }

    @Override
    public void prepend(int value) {
        if(nLength >= nCapacity)
            expandArray();
        int[] dst = new int[nCapacity];
        System.arraycopy(arrIntegers, 0, dst, 1, nLength);
        arrIntegers = dst;
        arrIntegers[0] = value;
        nLength++;
    }

    @Override
    public void insert(int index, int value) {
        if(nLength >= nCapacity)
            expandArray();
        int[] dst = new int[nCapacity];
        System.arraycopy(arrIntegers, 0, dst, 0, index);
        dst[index] = value;
        System.arraycopy(arrIntegers, index, dst, index + 1, nLength - index);
        arrIntegers = dst;
        nLength++;
    }

    @Override
    public void remove(int index) {
        if(index < 0)
            System.out.println("Invalid index input.");
        else{
            if(nLength <= index)
                System.out.println("Index is empty.");
            else{
                int[] dst = new int[nCapacity];
                System.arraycopy(arrIntegers, 0, dst, 0, index);
                System.arraycopy(arrIntegers, index + 1, dst, index, nLength - (index + 1));
                arrIntegers = dst;
                nLength--;
            }
        }
    }

    @Override
    public int get(int index) {
        if(index >= 0){
            if(index < nLength)
                return arrIntegers[index];
            else
                return -1;
        }
        else
            return -1;
    }

    @Override
    public int length() {
        return nLength;
    }
}

public class Lists{
    public static void printList(IntArrayList list) {
        for (int i = 0; i < list.length(); i++) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
    }
    public static void main(String[] args){
        IntArrayList list = new IntArrayList(10);
        for (int i = 0; i < 20; i++) {
            list.append(i);
        }
        printList(list);

        list.remove(5);
        printList(list);

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        printList(list);

        list.insert(5, 100);
        printList(list);
    }
}