package cn.edu.xcu.experiment_2.utils;

import jakarta.validation.GroupSequence;

public class ValidGroup {
    public interface Insert{}
    public interface UPdate{}
    public interface Delete{}
    @GroupSequence({Insert.class, UPdate.class, Delete.class})
    public interface All{}
}
