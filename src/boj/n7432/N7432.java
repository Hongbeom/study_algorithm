package boj.n7432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class N7432 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        Directory root = new Directory(null);

        for (int i = 0; i < n; i++) {
            String[] names = br.readLine().split("\\\\");
            root.add(names);
        }

        print(root, "");

    }

    static void print(Directory current, String prefix){
        if(current.children.size() == 0){
            return;
        }
        Collections.sort(current.children);
        for(Directory child : current.children){
            System.out.println(prefix + child.name);
            print(child, prefix + " ");
        }
    }

    static Directory createSub(String[] names, int from){

        Directory subRoot = new Directory(names[from]);
        Directory current = subRoot;

        for(int i = from + 1; i < names.length; i++) {
            current.map.put(names[i], current.children.size());
            Directory child = new Directory(names[i]);
            current.children.add(child);
            current = child;
        }

        return subRoot;
    }



    static class Directory implements Comparable<Directory> {


        String name;
        List<Directory> children;
        HashMap<String, Integer> map;

        public Directory(String name) {
            this.name = name;
            this.children = new ArrayList<>();
            this.map = new HashMap<>();
        }




        void add(String[] names) {
            Directory current = this;

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int id = current.map.getOrDefault(name, -1);
                if (id == -1) {
                    Directory sub = createSub(names, i);
                    current.map.put(names[i], current.children.size());
                    current.children.add(sub);
                    break;
                }
                current = current.children.get(id);
            }

        }

        @Override
        public int compareTo(Directory o) {
            return this.name.compareTo(o.name);
        }
    }
}
