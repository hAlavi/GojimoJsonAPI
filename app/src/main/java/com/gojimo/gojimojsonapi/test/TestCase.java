package com.gojimo.gojimojsonapi.test;

import android.test.InstrumentationTestCase;

import com.gojimo.gojimojsonapi.model.JsonStorage;
import com.gojimo.gojimojsonapi.model.Qualification;
import com.gojimo.gojimojsonapi.model.Subject;

import junit.framework.Assert;
import junit.framework.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/***
 * Testcase based on InstrumentationTestCase
 * @author RouhAlavi
 * @version 0.1.0
 */
public class TestCase extends InstrumentationTestCase {

    public void test() throws Exception {
        Qualification qua = new Qualification("Q1",null,null,null,null);
        Assert.assertEquals(qua.getCreatedAt(), qua.getUpdatedAt());
        //
        ArrayList<Subject> sal= new ArrayList<Subject>();
        Subject s1 = new Subject("i","j","k");
        Subject s2 = new Subject("k","l","m");
        sal.add(s1);
        sal.add(s2);
        qua = new Qualification("Q2","2014-04-12T10:06:33.000Z","2015-01-01T10:06:33.000Z", sal, null);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2014-04-12 10:06:33.000");
        Assert.assertEquals(qua.getCreatedAt().getTime(), date.getTime());
        Assert.assertEquals(qua.getSubjects().size(),2);
    }

    public void test2(){
        JsonStorage.writeFileInternalStorage("This is a test", getInstrumentation().getContext(), "file.json");
        //
        String s = JsonStorage.readFileInternalStorage("file.json",getInstrumentation().getContext());
        //
        Assert.assertEquals(s,"This is a test");
    }
}