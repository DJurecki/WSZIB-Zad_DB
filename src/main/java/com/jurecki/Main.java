package com.jurecki;

import com.jurecki.db.GameRepository;
import com.jurecki.gui.GUI;

public class Main {

    public static void main(String[] args) {

        GameRepository.connect();
        GUI.showGUI();
    }
}