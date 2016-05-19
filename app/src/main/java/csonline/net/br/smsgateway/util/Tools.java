package csonline.net.br.smsgateway.util;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tools implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Date sumSecondToDate(String strDate, int secondToAdd, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strDate));
            cal.add(Calendar.SECOND, secondToAdd);
            date = (Date) cal.getTime();
        } catch (ParseException e) {
            Log.e("Error: ", e.getMessage());
        }
        return date;
    }

    public static Date sumMinuteToDate(String strDate, int minuteToAdd, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(strDate));
            cal.add(Calendar.MINUTE, minuteToAdd);
            date = (Date) cal.getTime();
        } catch (ParseException e) {
            Log.e("Error: ", e.getMessage());
        }
        return date;
    }

    public static boolean validarFormatoData(Date data) {
        int ini = 0;
        int end = 0;
        boolean validar = true;
        if (data!=null) {
            String strData = Tools.converteDateToString(data, "yyyy-MM-dd HH:mm:ss");
            if (strData.indexOf(' ')!=-1) {
                    ini = strData.indexOf(' ');
            }
            end = strData.length();
            ini = ini + 1;
            if (strData.substring(ini, end).equals("00:00:00")) {
                    validar = false;
            }
        }
        return validar;
    }

    public static String converteDateToString(Date dtDate, String format) {  
        boolean tipo_ddMMyy = false;
        String strData = "";
        if (format==null) {
            format = "dd/MM/yyyy";
        }
        if (format.equals("ddMMyy")) {
            format = "dd/MM/yy";
            tipo_ddMMyy = true;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        strData = dateFormat.format(dtDate);
        if (tipo_ddMMyy) {
            strData = strData.replace("/", "");
        }
        return strData;
    }  

    public static String byBlankSpaceInFrontOfFine(String str, int fine) {
        String txt = "";
        if (str!=null) {
            str = str.trim();
            if (str.length()<fine) {
                txt = txt.concat(str);
                for (int i=str.length(); i<fine; i++) {
                    txt = txt.concat(" ");			
                }
            } else {
                txt = str.substring(0, fine);
            }
        }
        return txt;
    }

    public static String byBlankSpaceInFrontOf(String str, int fine) {
        String txt = "";
        if (str!=null) {
            str = str.trim();
            txt = txt.concat(str);
            for (int i=0; i<fine; i++) {
                txt = txt.concat(" ");
            }
        }
        return txt;
    }

    public static Date converteDataStringToDateUtil(String strData, String formato) {
        Date data = null;   
        if (formato==null) {
            formato = "dd/MM/yyyy";
        }
        try {
            DateFormat dataFormatada = new SimpleDateFormat(formato);
            data = dataFormatada.parse(strData);
        } catch (ParseException e) {
            Log.e("Error: ", e.getMessage());
        }
        return data;   
    }  

    public static String converteBigDecimalToString(BigDecimal numero, String formato) {
        String strNum = "";
        if (formato==null) {
            formato = "#,##0.00";
        }
        DecimalFormat df = new DecimalFormat(formato);  
        strNum = df.format(numero); 
        return strNum;
    }

    public static String converteBigDecimalToStringWithPoint(BigDecimal numero) {
        DecimalFormat df = new DecimalFormat("#0.00");  
        String strNum = df.format(numero); 
        strNum = strNum.replaceAll(",", ".");
        return strNum;
    }

    public static boolean validarNumero(String number) {
        boolean valido = true;
        try {  
            Long.parseLong(number);  
        } catch (NumberFormatException e) {
            Log.e("Error: ", e.getMessage());
            valido = false;
        }  catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            valido = false;
        }
        return valido;
    }
}
