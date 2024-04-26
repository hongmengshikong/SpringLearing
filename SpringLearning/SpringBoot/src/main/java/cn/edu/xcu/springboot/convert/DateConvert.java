package cn.edu.xcu.springboot.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = format.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}