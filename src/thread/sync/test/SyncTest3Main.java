package thread.sync.test;

public class SyncTest3Main {

    public static void main(String[] args) throws InterruptedException {

        Immutable immutable = new Immutable(100);

        // 공유 자원이지만 값 변경이 불가능하므로 안전
        // immutable.value += 1;

    }

    static class Immutable {

        public final int value;

        Immutable(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
