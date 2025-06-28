package arrays;


public class designHashMap {
    public static void main(String[] args) {

    }

    private Entity[] entities;
    public designHashMap() {
        entities = new Entity[10000];
    }

    private class Entity {
        int key;
        int value;

        public Entity(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public void put(int key, int value) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        entities[hash] = new Entity(key, value);
    }

    public int get(int key) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        if(entities[hash] != null && entities[hash].key == key){
            return entities[hash].value;
        }
        return -1;
    }

    public void remove(int key) {
        int hash = Math.abs(Integer.hashCode(key) % entities.length);
        if(entities[hash] != null && entities[hash].key == key){
            entities[hash] = null;
        }
    }
}
