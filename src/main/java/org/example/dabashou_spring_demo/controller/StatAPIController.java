package org.example.dabashou_spring_demo.controller;

import org.example.dabashou_spring_demo.model.bo.StatsViewItem;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSelectInputBO;
import org.example.dabashou_spring_demo.service.WasteManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("statapi")
public class StatAPIController {


    @Autowired
    private WasteManagerService service;

    @GetMapping("select")
    public StatsViewItem[]  select(int from, int to) throws Exception {

        if(from>to){
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            from = year*12+month-6;
            to = year*12+month;
        }
        Calendar calendar = Calendar.getInstance();
        int smonth = calendar.get(Calendar.MONTH) + 1;
        int syear = calendar.get(Calendar.YEAR);

        int sfrom = syear*12+smonth-6;
        int sto = syear*12+smonth;

        System.out.println("from: "+from +"to: "+ to);
        System.out.println("sfrom: "+sfrom +"sto: "+ sto);

        WasteManagerSelectInputBO input = new WasteManagerSelectInputBO(BigInteger.valueOf(from),BigInteger.valueOf(to));
        List<ArrayList> a = (List<ArrayList>)service.select(input).getReturnObject().get(0);
        StatsViewItem[] items = new StatsViewItem[a.size()];
        System.out.println("============");
        System.out.println(items.length);
        System.out.println("============");
        for(int i =0; i < a.size(); i++){
            ArrayList  item_r = a.get(i);
            int totalmonth =  ((BigInteger)(item_r.get(0))).intValue();
            String date = totalmonth / 12 + "年" + (String.format("%02d",  totalmonth % 12)) + "月";
            System.out.println(date);
            StatsViewItem item_a = new StatsViewItem(date,(BigInteger)item_r.get(1),(BigInteger)item_r.get(2),(BigInteger)item_r.get(3), service.getBlockHashByNumber((BigInteger)item_r.get(4)));
            items[i] = item_a;
        }

        return items;
    }


}
