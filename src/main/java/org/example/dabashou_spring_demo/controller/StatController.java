package org.example.dabashou_spring_demo.controller;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;


import org.example.dabashou_spring_demo.contracts.StatsData;
import org.example.dabashou_spring_demo.model.bo.StatsItem;
import org.example.dabashou_spring_demo.model.bo.StatsViewItem;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSelectInputBO;
import org.example.dabashou_spring_demo.service.WasteManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@Controller
public class StatController {


    @Autowired
    private WasteManagerService service;

    @GetMapping("/stat")
    public String stat(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)  throws Exception {


        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        BigInteger from = BigInteger.valueOf(year*12+month-6);
        BigInteger to = BigInteger.valueOf(year*12+month);

        WasteManagerSelectInputBO input = new WasteManagerSelectInputBO(from,to);
        List<ArrayList> a = (List<ArrayList>)service.select(input).getReturnObject().get(0);
        StatsViewItem[] items = new StatsViewItem[a.size()];
        for(int i =0; i < a.size(); i++){
            ArrayList  item_r = a.get(i);
            int totalmonth =  ((BigInteger)(item_r.get(0))).intValue();
            String date = convertTotalMonthToDateString(totalmonth, false);
            StatsViewItem item_a = new StatsViewItem(date,(BigInteger)item_r.get(1),(BigInteger)item_r.get(2),(BigInteger)item_r.get(3), service.getBlockHashByNumber((BigInteger)item_r.get(4)));
            items[i] = item_a;
        }
        model.addAttribute("statItems",items);
        model.addAttribute("from", convertTotalMonthToDateString(year*12+month-6,true));
        model.addAttribute("to", convertTotalMonthToDateString(year*12+month,true));
        return "index";
    }

    public String convertTotalMonthToDateString(int totalmonth, boolean simple){
        int years =totalmonth / 12;
        int months = totalmonth % 12;
        if (simple){
            return years + "-" + (months);
        }
        else{
            return years + "年" + (months) + "月";
        }
    }



}
