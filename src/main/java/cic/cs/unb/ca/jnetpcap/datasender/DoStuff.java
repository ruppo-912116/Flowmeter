package cic.cs.unb.ca.jnetpcap.datasender;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class DoStuff extends CommandObject{
    public DoStuff(){

    }

    @Override
    public void execute() {
        String tmp_string = null;
        while (!buffer.isEmpty()) {
            tmp_string = buffer.remove(0);
            // Do Stuff
        }
    }
}
