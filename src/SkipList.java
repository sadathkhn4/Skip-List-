import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
// implements Iterable<KVPair<K, V>>
{

    /**
     * First element of the SkipList (Sentinel Node)
     */
    private SkipNode head; // First element (Sentinel Node)

    /**
     * Size of the Skiplist (Number of elements)
     */
    private int size; // number of entries in the Skip List

    /**
     * Level of the node. Also known as Depth
     */
    private int level;

    /**
     * This is a random value that determines the level of a newly added node to
     * the SkipList
     */
    static private Random ran = new Random();

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */

    int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) { // ran is
                                                                 // random
                                                                 // generator
            // Do nothing
        }
        return lev;
    }
    
    /**
     * @return the level of the SkipList
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * 
     * @return the size of the SkipList 
     */
    public SkipNode getHead() {
        return head;
    }
    
    


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return an array list containing the search results
     */
// public ArrayList<KVPair<K, V>> search(K key) {
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> arr = new ArrayList<>();
        SkipNode x = head; // Dummy header node
        for (int i = level; i >= 0; i--) { // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) { // go forward
                x = x.forward[i]; // Go one last step
            }
        }
        x = x.forward[0]; // Move to actual record, if it exists

        // Continue until key doesn't match
        while (x != null && x.element().getKey().compareTo(key) == 0) {
            arr.add(x.element());
            x = x.forward[0]; // Move to the next node with the same key
        }
        return arr.isEmpty() ? null : arr;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicographical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        K key = it.getKey();
        V elem = it.getValue();

        int newLevel = randomLevel(); // New node's level
        if (newLevel > head.level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);

        SkipNode x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {
        SkipNode tempHead = head;
        head = new SkipNode(null, newLevel);
        int l = tempHead.level;
        for (int i = 0; i <= level; i++) {
            head.forward[i] = tempHead.forward[i];
        }
        level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the key to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode b = getNodeByName(key);
        SkipNode a = head;

        if (b != null) {
            SkipNode[] substitute = b.forward;
            int levelNow = a.forward.length - 1; // Current Level
            while (a != null) { // If list is non-empty, we will traverse
                int i = levelNow;
                while (i >= 0) { // Looping through the list
// if (a.forward[i] != null) {
                    if (a.forward[i] == b) {
                        a.forward[i] = substitute[i];
                    }
// }
                    i--;
                }
                a = a.forward[0]; // Move to the found node
                if (a != null) // update current level if node is not null
                    levelNow = a.forward.length - 1;
            }
            size--;
            return b.element();
        }
        return null;
    }


    /**
     * Removes a KVPair with the specified value.
     *
     * @param val
     *            the value of the KVPair to be removed
     * @return returns the KVPair if successful, null otherwise
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipNode x = head;
        int baseLevel = 0;
        while (x != null) {
            if (x.forward[baseLevel] != null) {
                KVPair<K, V> currentPair;
                currentPair = x.forward[baseLevel].element();
                if (val.equals(currentPair.getValue())) {
                    break;
                }
            }
            x = x.forward[baseLevel];
        }
        if (x != null) {
            if (x.forward[baseLevel] != null) {
                x = x.forward[baseLevel];
            }

        }
        SkipNode y = head; // dummy head
        if (x == null) { // x is the node we want to remove
            return null;
        }
        SkipNode[] substitute = x.forward;
        int levelNow = y.forward.length - 1;
        while (y != null) {
            int i = levelNow;
            while (i >= 0) { // Loop through the list
                // removed if(y.forward[i] != null &&
// if (y.forward[i] != null && y.forward[i] == x) {
                if (y.forward[i] == x) {
                    y.forward[i] = substitute[i]; // Replace the pointers to
                                                  // discard the node
                }
                i--;
            }
            y = y.forward[0]; // Move to next node
            if (y != null) { // if y is non-null , change level
                levelNow = y.forward.length - 1;
            }
        }
        size--; // reduce the size after removal of node

        return x.element();

    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {

        System.out.println("SkipList dump: ");

        SkipNode tempHead = head;
        @SuppressWarnings("unchecked")
        SkipNode[] list = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
            1);

        for (int l = 0; tempHead != null; tempHead = tempHead.forward[0], l++) {
            if (l == 0) {
                list[0] = new SkipNode(tempHead.element(),
                    tempHead.forward.length);
            }
            else {
                @SuppressWarnings("unchecked")
                SkipNode[] temporaryArray = (SkipNode[])Array.newInstance(
                    SkipList.SkipNode.class, list.length + 1);
                System.arraycopy(list, 0, temporaryArray, 0, list.length);
                temporaryArray[temporaryArray.length - 1] = new SkipNode(
                    tempHead.element(), tempHead.forward.length);
                list = temporaryArray;
            }
        }
        // now we will print the output of the dump
        // creating a for loop if the skiplist is not empty
        // i.e list.length > 1
        if (list.length == 1) {
            System.out.println("Node has depth 1, Value (null)");
        }
        else {
            for (SkipNode node : list) {
                System.out.print("Node has depth " + (node.forward.length)
                    + ", Value (");

                if (node.element() == null) {
                    System.out.println(node.element() + ")");

                }
                else {
                    System.out.println(node.element().getKey() + ", " + node
                        .element().getValue().toString() + ")");
                }
            }

        }
        System.out.println("SkipList size is: " + (list.length - 1));
    }


    /**
     * Finds the node by the finding key. In other words, it retrieves the node
     * with the given name
     * 
     * @param findingKey
     *            Key to be find in the Skiplist
     * @return
     */
    private SkipNode getNodeByName(K findingKey) {
        SkipNode x = head;
        int i = level;
        while (i >= 0) { // Loop through the levels
            while ((x.forward[i] != null) && (findingKey.compareTo(x.forward[i]
                .element().getKey()) > 0)) {
                x = x.forward[i]; // Goes forward
            }
            i--;
        }
        x = x.forward[0]; // Shift to the result record (if found)
// return ((x != null) && (findingKey.compareTo(x.element()
// .getKey()) == 0)) ? x : null;
        try {
            if (findingKey.compareTo(x.element().getKey()) == 0) {
                return x;
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    public class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        /*
         * An array of pointers to subsequent nodes
         */
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }
        
        /**
         * @return the size of the SkipList 
         */
        public SkipNode[] getForward() {
            return forward;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }

// private class SkipListIterator implements Iterator<KVPair<K, V>> {
// private SkipNode current;
//
// public SkipListIterator() {
// current = head;
// }
//
//
// @Override
// public boolean hasNext() {
// // to do Auto-generated method stub
// return current.forward[0] != null;
// }
//
//
// @Override
// public KVPair<K, V> next() {
// // to do Auto-generated method stub
// KVPair<K, V> elem = current.forward[0].element();
// current = current.forward[0];
// return elem;
// }
//
// }
//
// @Override
// public Iterator<KVPair<K, V>> iterator() {
// // to do Auto-generated method stub
// return new SkipListIterator();
// }

}
