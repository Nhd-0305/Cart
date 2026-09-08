package data;

import java.util.ArrayList;
import java.util.List;
import model.CD;

public class CDDB {

    public static List<CD> getCDs() {
        List<CD> cdList = new ArrayList<>();
        cdList.add(new CD("cd1", "86 (the band) - True Life Songs and Pictures", 14.95));
        cdList.add(new CD("cd2", "Paddlefoot - The first CD", 12.95));
        cdList.add(new CD("cd3", "Paddlefoot - The second CD", 14.95));
        cdList.add(new CD("cd4", "Joe Rut - Genuine Wood Grained Finish", 14.95));
        return cdList;
    }

    public static CD getCD(String id) {
        for (CD cd : getCDs()) {
            if (cd.getId().equals(id)) {
                return cd;
            }
        }
        return null;
    }
}
