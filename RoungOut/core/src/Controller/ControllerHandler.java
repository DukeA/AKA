package Controller;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-12.
 */
public class ControllerHandler implements IHandler {

   ArrayList<EnumIndexes> enums = new ArrayList<EnumIndexes>();

    private ArrayList<IController> controllers = new ArrayList<IController>();


    public void addController(int index, IController controller){
        controllers.add(index,controller);
    }

    @Override
    public void callSetNewInput(EnumIndexes index) {
        controllers.get(enums.indexOf(index)).changeInputProcessor();
    }

            //enums.get(enums.indexOf(EnumIndexes.MENU_CONTROLLER));

    //Constructor
    public ControllerHandler(ArrayList<IController> controllers) {
        this.controllers = controllers;
    }
}
