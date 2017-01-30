public class SampleKey {
    public static void main(String[] args) {
	Lock bikeLock, bikeLock2;
    	Key bikeKey, bikeKey2;

    	bikeLock = new Lock();    // need a lock instance
    	bikeKey = bikeLock.createKey();  // key to this lock

    	bikeLock2 = new Lock();    // need a lock instance
    	bikeKey2 = bikeLock2.createKey();  // key to this lock

    	if (bikeKey.getMyLock() == bikeLock )
		System.out.println("These are the same Object");

    	if (bikeKey2.getMyLock() == bikeLock )
		System.out.println("This should never print");
    }
}
