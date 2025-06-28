package arrays;


public class designHashSet {
    public static void main(String[] args) {
        designHashSet set = new designHashSet();
        set.add(1);
        set.add(2);
        System.out.println(set.contains(2));
        set.remove(2);
        System.out.println(set.contains(2));
    }

    private Entity[] entities;

    private class Entity {
        int key;

        public Entity(int key){
            this.key = key;
        }
    }

    public designHashSet() {
        entities = new Entity[100000];
    }

    public void add(int key) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        if(!contains(key)){
            entities[hash] = new Entity(key);
        }

    }

    public void remove(int key) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        if(entities[hash] != null && contains(key)){
            entities[hash] = null;
        }
    }

    public boolean contains(int key) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        if(entities[hash] != null && entities[hash].key == key){
            return true;
        }
        return false;
    }
}
