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

    public void setControllers (ArrayList<IController> newControllers){
        controllers = newControllers;
    }

            //enums.get(enums.indexOf(EnumIndexes.MENU_CONTROLLER));

    //Constructors
    public ControllerHandler(){}

    public ControllerHandler(ArrayList<IController> controllers) {
        this.controllers = controllers;
    }
}
