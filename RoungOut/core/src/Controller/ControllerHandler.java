package Controller;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-12.
 */
public class ControllerHandler implements IHandler {

   ArrayList<EnumIndexes> enums = new ArrayList<EnumIndexes>();

    private ArrayList<IController> controllers = new ArrayList<IController>();


    public void addControllerIndex(int index, IController controller){
        controllers.add(index,controller);
    }

    public void addController (IController controller) {controllers.add(controller);}

    @Override
    public void callSetNewInput(EnumIndexes controllerEnum) {
        for (int i =0; i < controllers.size(); i++){
            //We get each controllers Enum;
            EnumIndexes tmp = controllers.get(i).getTypeOfMenu();

            if(tmp == controllerEnum){
                //If the controller's enum matches index then set it to the new inputProcessor
                controllers.get(i).changeInputProcessor();
                break;//we brake since we found a input processor
            }
        }
    }

    public ArrayList<IController> getcontrollers (){
        return controllers;
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
