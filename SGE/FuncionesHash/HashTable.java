package SGE.FuncionesHash;

public class HashTable{
    private static int InitialSize = 13;
    private HashEntry[] Entries = new HashEntry[InitialSize];

    public void put(String key, String value){
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);
        if(Entries[hash] == null){
            Entries[hash] = hashEntry;
        }
        else{
            HashEntry temp = Entries[hash];
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = hashEntry;
        }
    }

    public String get(String key){
        int hash = getHash(key);
        if(Entries[hash] != null){
            HashEntry temp = Entries[hash];
            while(!temp.key.equals(key) && temp.next != null){
                temp = temp.next;
            }
            return temp.value;
        }
        return null;
    }

    private int getHash(String key){
        return key.hashCode() % InitialSize;
    }

    public static class HashEntry{
        String key;
        String value;
        HashEntry next;

        public HashEntry(String key, String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    
        public String toString(){
            return "[" + key + "," + value + "]";
        }
    }

    @Override
    public String toString(){
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for(HashEntry entry: Entries){
            if(entry == null){
                continue;
            }

            hashTableStr.append("\n bucket[").append("] = ").append(entry.toString());
            bucket ++;
            HashEntry temp = entry.next;
            while(temp != null){
                hashTableStr.append("-> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    public static void main(String[] args) {
        HashTable hashtable = new HashTable();
        for(int i = 0; i < 30; i++){
            final String key = String.valueOf(i);
            hashtable.put(key, key);
        }

        log("Hash Table");
        log(hashtable.toString());
    }

    private static void log(String msg){
        System.out.println(msg);
    }
}