// Outline of Key Class
public class Key {
    private Lock myLock;
    public Key (Lock theLock) {  // Constructor, typo in book
          myLock = new Lock();      // Note: new is NOT used here
    }
    public Lock getMyLock() {
        return myLock;
    }
}
