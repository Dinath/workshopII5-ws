/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Date;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date v) {
        return R.GLOBAL_DATE_FORMAT.format(v);
    }

    @Override
    public Date unmarshal(String v) {
        try {
            return R.GLOBAL_DATE_FORMAT.parse(v);
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
