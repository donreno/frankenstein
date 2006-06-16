package com.thoughtworks.frankenstein.script;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.frankenstein.events.*;
import junit.framework.TestCase;


/**
 * Ensures behaviour of script file. This is an integration test for saving and loading
 * tests.
 */
public class ScriptTest extends TestCase {
    private static final String SCRIPT = "CancelTableEdit tableName\n" +
    "ClickButton click button\n" +
    "ClickCheckbox check Box true\n" +
    "ClickRadioButton radio button\n" +
    "DialogShown title\n" +
    "EnterText textFieldName text\n" +
    "EditTableCell tableName 1,1\n" +
    "EnterText textBox text\n" +
    "InternalFrameShown title a\n" +
    "KeyStroke 0,48\n" +
    "Navigate ab>ac>de\n" +
    "SelectDropDown combo text a\n" +
    "SelectList list text a\n" +
    "StopTableEdit table\n" +
    "SwitchTab tab text a\n" +
    "WindowActivated text a";

    public void testCreatesScriptFromEventList() {
        List eventList = new ArrayList();
        eventList.add(new CancelTableEditEvent("tableName"));
        eventList.add(new ClickButtonEvent("click button"));
        eventList.add(new ClickCheckboxEvent("check Box", true));
        eventList.add(new ClickRadioButtonEvent("radio button"));
        eventList.add(new DialogShownEvent("title"));
        eventList.add(new EnterTextEvent("textFieldName", "text"));
        eventList.add(new EditTableCellEvent("tableName", 1, 1));
        eventList.add(new EnterTextEvent("textBox", "text"));
        eventList.add(new InternalFrameShownEvent("title a"));
        eventList.add(new KeyStrokeEvent(0, 48));
        eventList.add(new NavigateEvent("ab>ac>de"));
        eventList.add(new SelectDropDownEvent("combo", "text a"));
        eventList.add(new SelectListEvent("list", "text a"));
        eventList.add(new StopTableEditEvent("table"));
        eventList.add(new SwitchTabEvent("tab", "text a"));
        eventList.add(new WindowActivatedEvent("text a"));
        Script script = new Script(new DefaultEventRegistry());
        assertEquals(SCRIPT, script.scriptText(eventList));
    }

    public void testCreatesEventListFromScript() throws IOException {
        Script script = new Script(new DefaultEventRegistry());
        List eventList = script.parse(new StringReader(SCRIPT));
        assertEquals(16, eventList.size());
        assertEquals(new CancelTableEditEvent("tableName"), eventList.get(0));
        assertEquals(new ClickButtonEvent("click button"), eventList.get(1));
        assertEquals(new ClickCheckboxEvent("check Box", true), eventList.get(2));
        assertEquals(new ClickRadioButtonEvent("radio button"), eventList.get(3));
        assertEquals(new DialogShownEvent("title"), eventList.get(4));
        assertEquals(new EnterTextEvent("textFieldName", "text"), eventList.get(5));
        assertEquals(new EditTableCellEvent("tableName", 1, 1), eventList.get(6));
        assertEquals(new EnterTextEvent("textBox", "text"), eventList.get(7));
        assertEquals(new InternalFrameShownEvent("title a"), eventList.get(8));
        assertEquals(new KeyStrokeEvent(0, 48), eventList.get(9));
        assertEquals(new NavigateEvent("ab>ac>de"), eventList.get(10));
        assertEquals(new SelectDropDownEvent("combo", "text a"), eventList.get(11));
        assertEquals(new SelectListEvent("list", "text a"), eventList.get(12));
        assertEquals(new StopTableEditEvent("table"), eventList.get(13));
        assertEquals(new SwitchTabEvent("tab", "text a"), eventList.get(14));
        assertEquals(new WindowActivatedEvent("text a"), eventList.get(15));
    }
}