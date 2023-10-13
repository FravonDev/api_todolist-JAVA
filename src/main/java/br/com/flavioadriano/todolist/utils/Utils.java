package br.com.flavioadriano.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        // esta é uma forma de copiar os atributos nulos de um objeto
        BeanUtils.copyProperties(source, target, getNUllPropertyNames(source));
    }

    public static String[] getNUllPropertyNames(Object source) {
        // esta é uma forma de pegar os atributos nulos de um objeto
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
