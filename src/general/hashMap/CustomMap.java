package general.hashMap;

public class CustomMap {
    public static void main(String[] args) {
        HashMapBetter<String, String> map = new HashMapBetter<>();
        map.put("Adi", "22");
        map.put("Mango","King of fruit");
        map.put("Apple","Red apple");
        System.out.println(map.containsKey("Mango"));
        System.out.println(map.get("Mango"));
        System.out.println(map);
    }
}
