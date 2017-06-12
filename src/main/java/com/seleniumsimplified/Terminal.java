package com.seleniumsimplified;

import com.seleniumsimplified.enums.OperativeSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Terminal implements Runnable{
    private static final String OS_PROPERTY = "os.name";
    private static final String WIN_TERMINAL = "cmd";
    private static final String MACOS_TERMINAL = "/bin/bash";
    private static final String WIN_OPTION = "/c";
    private static final String MACOS_OPTION = "-c";
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 4723;

    private List<String> commandList;
    private String OS;

    public String input = "";

    Terminal(){
        commandList = new ArrayList<>();
        OS = System.getProperty(OS_PROPERTY).toLowerCase();
    }

    /* Runs the command list in the terminal */
    BufferedReader runCommand(String command) throws IOException {
        initializeTerminal();
        commandList.add(command);
        Process terminalProcess = new ProcessBuilder(commandList.toArray(new String[0])).start();
        return new BufferedReader(new InputStreamReader(terminalProcess.getInputStream()));
    }

    /* Prints the terminal output */
    private void printOutput(BufferedReader bufferedReader) throws IOException{
        while ((input = bufferedReader.readLine()) != null) {
            System.out.println(input);
        }
    }

    /* Adds to the list the commands needed in order to open the terminal taking into account the OS */
    private void initializeTerminal(){
        commandList.clear();
        if(OS.contains(OperativeSystem.MacOS.toString())){
            commandList.add(MACOS_TERMINAL);
            commandList.add(MACOS_OPTION);
        }
        else if(OS.contains(OperativeSystem.Windows.toString())){
            commandList.add(WIN_TERMINAL);
            commandList.add(WIN_OPTION);
        }
    }

    @Override
    public void run() {
        try {
            printOutput(runCommand("appium -a " + LOCALHOST + " -p " + PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
