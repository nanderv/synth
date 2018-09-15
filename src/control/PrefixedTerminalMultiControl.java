package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class PrefixedTerminalMultiControl {
    private List<StringBasedControl> controlList;
    private List<String> prefixStrings;

    public PrefixedTerminalMultiControl(){
        controlList = new LinkedList<>();
        prefixStrings = new LinkedList<>();
    }
    public void addControl(String prefix, StringBasedControl control){
        controlList.add(control);
        prefixStrings.add(prefix);
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = in.readLine();
            String[] t = line.split(" ");
            if(t.length == 2){
            for(int i=0; i< controlList.size(); i++) {
                if(prefixStrings.get(i).equals(t[0])){
                    controlList.get(i).doAction(t[1]);
                }
            }
            }
        }
    }
}
