package com.miniProject.shop.Helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Helper {
    private static Locale indonesia = new Locale("id","ID");

    public static class Date{
        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", indonesia);

        public static LocalDate format(String date){
            return LocalDate.parse(date,formatter);
        }
    }

    public static class Money{
        private static NumberFormat formatter = NumberFormat.getCurrencyInstance(indonesia);

        public static String getMoney(BigDecimal money){
            if(money == null){
                return formatter.format(0.0);
            }else{
                return formatter.format(money);
            }
        }
    }
}
