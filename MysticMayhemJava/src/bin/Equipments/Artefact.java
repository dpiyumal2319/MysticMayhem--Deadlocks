package bin.Equipments;

import bin.Equipment;

public class Artefact extends Equipment{
    
    public Artefact(String name) {
        super(data.artefacts.get(name), "Artefact");
    }
}
