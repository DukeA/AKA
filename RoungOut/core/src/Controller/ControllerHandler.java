package Controller;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-12.
 */
public class ControllerHandler implements IHandler {

    private ArrayList<IController> controllers = new ArrayList<IController>();

    public void addController(int index, IController controller){
        controllers.add(index,controller);
    }

    @Override
    public void callSetNewInput(int index) {
        controllers.get(index).changeInputProcessor();
    }

    //Constructors
    public ControllerHandler() {
    }

    public ControllerHandler(ArrayList<IController> controllers) {
        this.controllers = controllers;
    }
}
