import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * 열거형 타입과 함수형 프로그래밍을 이용하여 플레이어의 공격력을 계산하는 알고리즘을 구현하시오.
 *
 * 플레이어 공격력을 계산하는 과정은 아래와 같다.
 * 1. 플레이어의 무기에 따라 공격력이 변화한다. 무기는 최근에 장착한 무기의 공격력 만으로 계산된다.
 *   1-1. BARE_HANDS - 공격력 5
 *   1-2. DAGGER - 공격력 40
 *   1-3. LONG_SWORD - 공격력 100
 *   1-4. DRAGON_SLAYER -  공격력 250
 * 2. 플레이어의 공격력에 영향을 주는 아이템에 따라 공격력 증가 방식이 다르며, 아이템은 중복 적용된다.
 *   2-1. BLACK_POTION - 공격력 10% 증가
 *   2-2. WHITE_POTION - 모든 공격력 계산이 끝난 후에 공격력 + 200
 *   2-3. MUSHROOM - 무기 공격력 + 20
 *
 */

enum Weapon {
    // 무기 구현
    BARE_HANDS(5), DAGGER(40), LONG_SWORD(100), DRAGON_SLAYER(250);

    private final int damage;

    Weapon(int i) {
        this.damage = i;
    }

    public int getDamage() {
        return damage;
    }
}

enum Item {
    // 소비 아이템 구현
    MUSHROOM((x) -> x + 20, 1),
    BLACK_POTION((x) -> (int)(x * 1.1),2),
    WHITE_POTION((x) -> x + 200,3);


    private final IntUnaryOperator op;
    private final int priority;

    Item(IntUnaryOperator op, int i) {
        this.op = op;
        this.priority = i;
    }

    public Integer getPriority(){
        return priority;
    }

    public IntUnaryOperator getOp(){
        return op;
    }
}

class Player {
    private Weapon currentWeapon;
    private final List<Item> items = new ArrayList<Item>();

    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현

    public void changeWeapon(Weapon weapon){
        this.currentWeapon = weapon;
    }

    public void useItem(Item item){
        if(items.contains(item)){
            System.out.println("이미 해당 아이템을 사용중입니다.");
            return;
        }
        items.add(item);
    }

    public void endItem(Item item){
        if(!items.contains(item)){
            System.out.println("해당 아이템은 사용중이 아닙니다.");
            return;
        }
        items.remove(item);
    }

    public int currentDamage(){
        if(currentWeapon == null){
            System.out.println("캐릭터가 장비를 착용하고 있지 않습니다.");
            return 0;
        }

        items.sort((o1, o2) -> o1.getPriority().compareTo(o2.getPriority()));

        int curDamage = currentWeapon.getDamage();

        for(Item item: items){
            curDamage = item.getOp().applyAsInt(curDamage);
        }

        return curDamage;
    }
}

public class DamageCalculation {
    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력
        Player player1 = new Player();

        System.out.println(player1.currentDamage());

        player1.changeWeapon(Weapon.BARE_HANDS);

        System.out.println(player1.currentDamage());

        player1.useItem(Item.WHITE_POTION);
        player1.useItem(Item.BLACK_POTION);
        player1.useItem(Item.WHITE_POTION);
        player1.useItem(Item.MUSHROOM);

        System.out.println(player1.currentDamage());

    }
}
